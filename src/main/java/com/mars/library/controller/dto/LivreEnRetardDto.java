package com.mars.library.controller.dto;

import java.util.Date;

public class LivreEnRetardDto {

    private String titre;
    private String auteur;
    private String maisonEdition;
    private Date dateRenduPrevu;

    public String getTitre() {
        return titre;
    }

    public LivreEnRetardDto setTitre(String titre) {
        this.titre = titre;
        return this;
    }

    public Date getDateRenduPrevu() {
        return dateRenduPrevu;
    }

    public LivreEnRetardDto setDateRenduPrevu(Date dateRenduPrevu) {
        this.dateRenduPrevu = dateRenduPrevu;
        return this;
    }

    public String getAuteur() {
        return auteur;
    }

    public LivreEnRetardDto setAuteur(String auteur) {
        this.auteur = auteur;
        return this;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public LivreEnRetardDto setMaisonEdition(String maisonEdition) {
        this.maisonEdition = maisonEdition;
        return this;
    }
}
