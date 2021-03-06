package com.mars.library.business;

import com.mars.library.model.Ouvrage;
import com.mars.library.repository.OuvrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OuvrageService {

    @Autowired
    private OuvrageRepository ouvrageRepository;

    public List<Ouvrage> afficherLivreDispo() {

        return ouvrageRepository.findAllByNombreExemplaireGreaterThan(0);
    }

    public List<Ouvrage> findAllBySearch(String search) {
        String search2="%"+search+"%";
        return ouvrageRepository.findAllByTitreLikeOrMaisonEditionLikeOrAuteur_NomLike(search2, search2, search2);
    }
}
