package com.mars.library.controller;

import com.mars.library.dao.OuvrageDao;
import com.mars.library.model.Ouvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/ouvrage")
public class OuvrageController {

    @Autowired
    private OuvrageDao ouvrageDao;

    @GetMapping

    public List<Ouvrage> afficherLivreDispo() {

return ouvrageDao.findAll();
    }

}
