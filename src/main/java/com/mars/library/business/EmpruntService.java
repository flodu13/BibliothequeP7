package com.mars.library.business;

import com.mars.library.controller.dto.OuvrageUtilisateurDto;
import com.mars.library.model.Emprunt;
import com.mars.library.model.Ouvrage;
import com.mars.library.model.Utilisateur;
import com.mars.library.repository.EmpruntRepository;
import com.mars.library.repository.OuvrageRepository;
import com.mars.library.repository.UtilisateurRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class EmpruntService {

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

    public void livreEnretard() throws MessagingException {
        List<Emprunt> empruntEnRetard = empruntRepository.findAllByStatusEnRetard();
        Map<Integer, List<Emprunt>> empruntsParUtilisateur = new HashMap<>();
        for (Emprunt emprunt : empruntEnRetard) {
            if (!empruntsParUtilisateur.containsKey(emprunt.getUtilisateur().getId())) {
                empruntsParUtilisateur.put(emprunt.getUtilisateur().getId(), new ArrayList<>());

            }
            empruntsParUtilisateur.get(emprunt.getUtilisateur().getId()).add(emprunt);
        }

        for (List<Emprunt> empruntsUnUtilisateur : empruntsParUtilisateur.values()) {
Utilisateur utilisateur = empruntsUnUtilisateur.get(0).getUtilisateur();
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariable("name", utilisateur.getNom());
            context.setVariable("emprunts", empruntsUnUtilisateur);

            String html = springTemplateEngine.process("retard", context);
            helper.setTo(utilisateur.getEmail());
            helper.setText(html, true);
            helper.setSubject("Vous avez du retard");
            helper.setFrom("noreply@library.com");
            emailSender.send(message);
        }

    }
}
