package com.mars.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Auteur {

    @Id
    private int id;
    private String nom;
    private String premon;
@OneToMany
    private List<Ouvrage> ouvrages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPremon() {
        return premon;
    }

    public void setPremon(String premon) {
        this.premon = premon;
    }
}
