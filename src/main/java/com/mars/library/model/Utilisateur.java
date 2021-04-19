package com.mars.library.model;

import javax.persistence.*;
import java.util.List;


@Entity
@SequenceGenerator(name = "utilisateur_seq", sequenceName = "utilisateur_seq", allocationSize = 1)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "utilisateur_seq")
    private int id;

    private String nom;

    private String email;

    private String MotDePasse;

    @OneToMany
    private List<Emprunt> emprunts;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        MotDePasse = motDePasse;
    }


}
