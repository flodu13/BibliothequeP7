package com.mars.library.controller;


import com.mars.library.dao.EmpruntDao;
import com.mars.library.model.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {


    @Autowired
    private EmpruntDao empruntDao;

    @GetMapping ("/utilisateur/{utilisateurId}")
    public List<Emprunt> empruntParUtilisateur (@PathVariable Integer utilisateurId) {
        return empruntDao.findAllByUtilisateurId(utilisateurId);

    }
}
