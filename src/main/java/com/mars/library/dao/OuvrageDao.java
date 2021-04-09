package com.mars.library.dao;

import com.mars.library.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuvrageDao extends JpaRepository<Ouvrage, Long> {


}
