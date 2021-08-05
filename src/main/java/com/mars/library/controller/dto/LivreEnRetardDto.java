package com.mars.library.controller.dto;

public class LivreEnRetardDto {

    private String titre;
    private String auteur;
    private String maisonEdition;

    public String getTitre() {
        return titre;
    }

    public LivreEnRetardDto setTitre(String titre) {
        this.titre = titre;
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
