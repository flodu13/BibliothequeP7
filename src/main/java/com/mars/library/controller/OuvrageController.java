package com.mars.library.controller;

import com.mars.library.business.OuvrageService;
import com.mars.library.controller.dto.OuvrageDto;
import com.mars.library.repository.OuvrageRepository;
import com.mars.library.model.Ouvrage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
        @RequestMapping("/ouvrage")
public class OuvrageController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OuvrageService ouvrageService;

    @GetMapping

    public List<OuvrageDto> afficherLivreDispo(@RequestParam(required = false) String search) {

        List<Ouvrage> ouvrages;
        if (search==null)
        {
            ouvrages= ouvrageService.afficherLivreDispo();
        } else {
            ouvrages=ouvrageService.findAllBySearch(search);
        }

        List<OuvrageDto> ouvrageDto = new ArrayList<OuvrageDto>();
        for (Ouvrage ouvrage: ouvrages) {
            ouvrageDto.add(modelMapper.map(ouvrage, OuvrageDto.class));
        }
        return ouvrageDto;
    }

}
