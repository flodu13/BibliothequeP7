package com.mars.library.controller;


import com.mars.library.business.EmpruntService;
import com.mars.library.controller.dto.OuvrageUtilisateurDto;
import com.mars.library.model.Utilisateur;
import com.mars.library.repository.EmpruntRepository;
import com.mars.library.model.Emprunt;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {


    @Autowired
    private EmpruntService empruntService;

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Emprunt> empruntParUtilisateur(@PathVariable Integer utilisateurId) {
        return empruntService.empruntParUtilisateur(utilisateurId);

    }

    @GetMapping("/utilisateur")
    public List<Emprunt> empruntUtilisateurConnecte(Principal principal) {
        Utilisateur utilisateurConnecte = (Utilisateur) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
      return empruntService.empruntParUtilisateur(utilisateurConnecte.getId());
    }

    @PostMapping()
    public Emprunt creation(@RequestBody OuvrageUtilisateurDto ouvrageUtilisateurDto) {

        return empruntService.creation(ouvrageUtilisateurDto);
    }

    @PostMapping ("/{empruntId}/prolongation")
    public ResponseEntity<Emprunt> miseAJourDateRenduPrevu(@PathVariable Integer empruntId) {

        try {
            return ResponseEntity.ok(empruntService.miseAJourDateRenduPrevu(empruntId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }


}