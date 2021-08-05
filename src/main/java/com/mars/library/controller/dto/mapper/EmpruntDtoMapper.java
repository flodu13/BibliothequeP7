package com.mars.library.controller.dto.mapper;

import com.mars.library.controller.dto.EmpruntDto;
import com.mars.library.controller.dto.EmpruntOuvrageDto;
import com.mars.library.controller.dto.OuvrageDto;
import com.mars.library.model.Emprunt;
import org.modelmapper.ModelMapper;

public class EmpruntDtoMapper {

    public static EmpruntDto dto (Emprunt emprunt) {
        EmpruntDto dto = new EmpruntDto();
        dto.setDatelimite(emprunt.getDatelimite());
        dto.setId(emprunt.getId());
        dto.setDateRenduPrevu(emprunt.getDateRenduPrevu());
        dto.setRendu(emprunt.getRendu());
        dto.setOuvrageID(emprunt.getOuvrage().getId());
        dto.setUtilisateurId(emprunt.getUtilisateur().getId());
        dto.setProlongPret(emprunt.getProlongPret());
        return  dto;
    }

    public static EmpruntOuvrageDto dtoWithOuvrage (Emprunt emprunt, ModelMapper modelMapper) {
        EmpruntOuvrageDto dto = new EmpruntOuvrageDto ();
        dto.setDatelimite(emprunt.getDatelimite());
        dto.setId(emprunt.getId());
        dto.setDateRenduPrevu(emprunt.getDateRenduPrevu());
        dto.setRendu(emprunt.getRendu());
        dto.setProlongPret(emprunt.getProlongPret());
        dto.setOuvrage(modelMapper.map(emprunt.getOuvrage(), OuvrageDto.class));
        dto.setUtilisateurId(emprunt.getUtilisateur().getId());
        return  dto;
    }

}
