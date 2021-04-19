package com.mars.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = "auteur_seq", sequenceName = "auteur_seq", allocationSize = 1)
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "auteur_seq")
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
