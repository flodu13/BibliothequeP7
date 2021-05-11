package com.mars.library.business;

import com.mars.library.controller.dto.OuvrageUtilisateurDto;
import com.mars.library.model.Emprunt;
import com.mars.library.repository.EmpruntRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    public List<Emprunt> empruntParUtilisateur(Integer utilisateurId) {
        return empruntRepository.findAllByUtilisateurId(utilisateurId);
    }
    public Emprunt creation(OuvrageUtilisateurDto ouvrageUtilisateurDto) {
        Emprunt emprunt= new Emprunt();
        emprunt.setOuvragePk(ouvrageUtilisateurDto.getOuvrageId());
        emprunt.setUtilisateurPk(ouvrageUtilisateurDto.getUtilisateurId());
        emprunt.setDateRenduPrevu(Date.from(LocalDate.now().plusWeeks(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        return empruntRepository.save(emprunt);
    }
    public Emprunt miseAJourDateRenduPrevu (Integer empruntId) throws NotFoundException {
        Emprunt emprunt =  empruntRepository.findById(empruntId).orElse(null);
        if (emprunt==null) {
            throw new NotFoundException("Emprunt not found");
        }

        if (emprunt.getDatelimite()!=null) {
            throw new IllegalArgumentException();
        }
        emprunt.setDatelimite(Date.from(emprunt.getDateRenduPrevu().toInstant().atZone(ZoneId.systemDefault()).plusWeeks(4).toInstant()));
        empruntRepository.save(emprunt);
        return emprunt;
    }
}
