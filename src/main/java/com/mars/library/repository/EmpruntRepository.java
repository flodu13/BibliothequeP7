package com.mars.library.repository;

import com.mars.library.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
    List<Emprunt> findAllByUtilisateurId(Integer utilisateurId);

    @Query(value = "SELECT * from public.emprunt WHERE COALESCE (datelimite, date_rendu_prevu) < CURRENT_DATE AND rendu IS NULL", nativeQuery = true)
    List<Emprunt> findAllByStatusEnRetard();
}
