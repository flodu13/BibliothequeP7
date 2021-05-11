package com.mars.library.business;

import com.mars.library.model.Utilisateur;
import com.mars.library.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utilisateur afficherUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    public void creation(Utilisateur utilisateur) {

        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurRepository.save(utilisateur);
    }
}
