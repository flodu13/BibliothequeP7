package com.mars.library.repository;

import com.mars.library.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
    List<Emprunt> findAllByUtilisateurId(Integer utilisateurId);
}
