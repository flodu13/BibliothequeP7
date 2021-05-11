package com.mars.library.repository;

import com.mars.library.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {


    Utilisateur findByEmail(String email);

   Optional<Utilisateur> findByNom(String utilisateur);
}
