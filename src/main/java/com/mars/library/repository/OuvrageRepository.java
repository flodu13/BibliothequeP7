package com.mars.library.repository;

import com.mars.library.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OuvrageRepository extends JpaRepository<Ouvrage, Integer> {

    List<Ouvrage> findAllByNombreExemplaireGreaterThan(int nbExemplaireMini);

}
