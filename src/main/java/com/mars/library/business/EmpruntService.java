package com.mars.library.business;

import com.mars.library.controller.dto.LivreEnRetardDto;
import com.mars.library.controller.dto.OuvrageUtilisateurDto;
import com.mars.library.controller.dto.UtilisateurEnRetardDto;
import com.mars.library.model.Emprunt;
import com.mars.library.model.Ouvrage;
import com.mars.library.model.Utilisateur;
import com.mars.library.repository.EmpruntRepository;
import com.mars.library.repository.OuvrageRepository;
import com.mars.library.repository.UtilisateurRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpruntService {

    @Value("${spring.mail.username}")
    private String from;


    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private OuvrageRepository ouvrageRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostConstruct
    public void init() {
        threadPoolTaskScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                envoidListEmailretard();
            }
            //calcul pour envoi 1 fois toute les 24 heures
        }, 1000*60*60*24);
    }

    public List<Emprunt> empruntParUtilisateur(Integer utilisateurId) {
        return empruntRepository.findAllByUtilisateurId(utilisateurId);
    }


    public Emprunt creation(OuvrageUtilisateurDto ouvrageUtilisateurDto, Utilisateur utilisateurConnecte) {
        Ouvrage ouvrage = ouvrageRepository.getOne(ouvrageUtilisateurDto.getOuvrageId());
        Utilisateur utilisateur = utilisateurRepository.findByEmail(utilisateurConnecte.getEmail());
        if (ouvrage.getNombreExemplaire() == 0) {
            throw new IllegalArgumentException("Aucun exemplaire disponible");
        }
        ouvrage.setNombreExemplaire(ouvrage.getNombreExemplaire() - 1);
        Emprunt emprunt = new Emprunt();
        emprunt.setOuvrage(ouvrage);
        emprunt.setUtilisateur(utilisateur);
        emprunt.setProlongPret(false);
        emprunt.setDateEmprunt(new Date());
        emprunt.setDateRenduPrevu(Date.from(LocalDate.now().plusWeeks(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        return empruntRepository.save(emprunt);
    }

    public Emprunt miseAJourDateRenduPrevu(Integer empruntId, Utilisateur utilisateurConnecte) throws NotFoundException, AccessDeniedException {
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        if (emprunt == null) {
            throw new NotFoundException("Emprunt not found");
        }
        if (utilisateurConnecte != null && utilisateurConnecte.getId() != emprunt.getUtilisateur().getId()) {
            throw new AccessDeniedException("Bad user");
        }
        if (emprunt.getProlongPret()) {
            throw new IllegalArgumentException();
        }
        emprunt.setDatelimite(Date.from(emprunt.getDateRenduPrevu().toInstant().atZone(ZoneId.systemDefault()).plusWeeks(4).toInstant()));
        emprunt.setProlongPret(true);
        empruntRepository.save(emprunt);
        return emprunt;
    }

    public void rendre(Integer empruntId, Utilisateur utilisateurConnecte) throws NotFoundException, AccessDeniedException {
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        if (emprunt == null) {
            throw new NotFoundException("Emprunt not found");
        }
        if (utilisateurConnecte != null && utilisateurConnecte.getId() != emprunt.getUtilisateur().getId()) {
            throw new AccessDeniedException("Bad user");
        }
        if (emprunt.getRendu() != null) {
            throw new IllegalArgumentException();
        }
        emprunt.setRendu(new Date());
        emprunt.getOuvrage().setNombreExemplaire(emprunt.getOuvrage().getNombreExemplaire() + 1);

        empruntRepository.save(emprunt);
    }

    public List<UtilisateurEnRetardDto> utilisateurEnRetard() {

        return empruntRepository.findAllByStatusEnRetard().stream()
                .collect(Collectors.groupingBy(emprunt -> emprunt.getUtilisateur()))
                .entrySet().stream()
                .map(e -> {
                    UtilisateurEnRetardDto utilisateur = new UtilisateurEnRetardDto();
                    utilisateur.setEmail(e.getKey().getEmail());
                    utilisateur.setNom(e.getKey().getNom());
                    List<LivreEnRetardDto> livres = e.getValue().stream().map(l -> new LivreEnRetardDto().setTitre(l.getOuvrage().getTitre())
                            .setMaisonEdition(l.getOuvrage().getMaisonEdition())
                            .setDateRenduPrevu(l.getDateRenduPrevu())
                            .setAuteur(l.getOuvrage().getAuteur().getPremon() + " " + l.getOuvrage().getAuteur().getNom()))
                            .collect(Collectors.toList());
                    utilisateur.setLivresEnRetards(livres);
                    return utilisateur;
                }).collect(Collectors.toList());
    }

    public void envoidListEmailretard() {
        utilisateurEnRetard().forEach(this::envoiMailEnRetard);
    }


    public void envoiMailEnRetard(UtilisateurEnRetardDto utilisateurEnRetardDto) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariable("name", utilisateurEnRetardDto.getNom());
            context.setVariable("livres", utilisateurEnRetardDto.getLivresEnRetards());

            String html = springTemplateEngine.process("retard", context);
            helper.setTo(utilisateurEnRetardDto.getEmail());
            helper.setText(html, true);
            helper.setSubject("Vous avez du retard");
            helper.setFrom(from);
            emailSender.send(message);
        } catch (MessagingException e) {

        }
    }
}
