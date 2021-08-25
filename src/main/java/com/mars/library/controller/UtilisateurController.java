package com.mars.library.controller;

import com.mars.library.business.UtilisateurService;
import com.mars.library.repository.UtilisateurRepository;
import com.mars.library.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
        @RequestMapping("/utilisateur")
public class UtilisateurController {

 @Autowired
 private UtilisateurService utilisateurService;


    @PostMapping("/creation")
    public ResponseEntity<Void> creation(@RequestBody Utilisateur utilisateur) {
        utilisateurService.creation(utilisateur);
return ResponseEntity.ok().build();
    }
}
