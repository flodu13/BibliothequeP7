package com.mars.library.controller;


import com.mars.library.business.EmpruntService;
import com.mars.library.controller.dto.EmpruntDto;
import com.mars.library.controller.dto.EmpruntOuvrageDto;
import com.mars.library.controller.dto.OuvrageUtilisateurDto;
import com.mars.library.controller.dto.mapper.EmpruntDtoMapper;
import com.mars.library.model.Utilisateur;
import com.mars.library.model.Emprunt;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmpruntService empruntService;

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Emprunt> empruntParUtilisateur(@PathVariable Integer utilisateurId) {
        return empruntService.empruntParUtilisateur(utilisateurId);

    }

    @GetMapping("/utilisateur")
    public List<EmpruntOuvrageDto> empruntUtilisateurConnecte(Principal principal) {
        Utilisateur utilisateurConnecte = (Utilisateur) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        List<EmpruntOuvrageDto> empruntDtos = new ArrayList<>();
        for (Emprunt emprunt : empruntService.empruntParUtilisateur(utilisateurConnecte.getId())) {
            empruntDtos.add(EmpruntDtoMapper.dtoWithOuvrage(emprunt, modelMapper));
        }
        // TODO a supprimer
        try {
            empruntService.livreEnretard();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return empruntDtos;
    }

    @PostMapping()
    public EmpruntDto creation(Principal principal, @RequestBody OuvrageUtilisateurDto ouvrageUtilisateurDto) {
        Utilisateur utilisateurConnecte = (Utilisateur) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Emprunt emprunt = empruntService.creation(ouvrageUtilisateurDto, utilisateurConnecte);
        return EmpruntDtoMapper.dto(emprunt);
    }

    @PostMapping ("/prolongation")
    public ResponseEntity<Void> miseAJourDateRenduPrevu(@RequestBody EmpruntDto emprunt, Principal principal) {
        Utilisateur utilisateurConnecte = (Utilisateur) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        if (utilisateurConnecte.getAdmin()) {
            utilisateurConnecte=null;
        }
        try {
            empruntService.miseAJourDateRenduPrevu(emprunt.getId(), utilisateurConnecte);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }
    @PostMapping("/{empruntId}/rendre")
public ResponseEntity<Void> rendre (@PathVariable Integer empruntId, Principal principal) {
        Utilisateur utilisateurConnecte = (Utilisateur) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        if (utilisateurConnecte.getAdmin()) {
            utilisateurConnecte=null;
        }
        try {
            empruntService.rendre (empruntId, utilisateurConnecte);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();

        }
        }
    }