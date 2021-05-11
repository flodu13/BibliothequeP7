package com.mars.library.controller.dto;

import com.mars.library.model.Auteur;

import javax.persistence.ManyToOne;

public class OuvrageDto {

    private int id;
    private int nombreExemplaire;
    private String titre;
    private AuteurDto auteur;
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

    public AuteurDto getAuteur() {
        return auteur;
    }

    public void setAuteur(AuteurDto auteur) {
        this.auteur = auteur;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition(String maisonEdition) {
        this.maisonEdition = maisonEdition;
    }
}
