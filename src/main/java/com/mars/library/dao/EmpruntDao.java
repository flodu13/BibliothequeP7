package com.mars.library.dao;

import com.mars.library.model.Emprunt;
import com.mars.library.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntDao extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findAllByUtilisateurId(Integer utilisateurId);
}
