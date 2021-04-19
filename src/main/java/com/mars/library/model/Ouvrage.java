package com.mars.library.model;

import javax.persistence.*;


@Entity
@SequenceGenerator(name = "ouvrage_seq", sequenceName = "ouvrage_seq", allocationSize = 1)
public class Ouvrage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ouvrage_seq")
    private int id;
    private int nombreExemplaire;

    private String titre;

    @ManyToOne
    private Auteur auteur;

    private String maisonEdition;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreExemplaire() {
        return nombreExemplaire;
    }

    public void setNombreExemplaire(int nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition(String maisonEdition) {
        this.maisonEdition = maisonEdition;
    }
}
