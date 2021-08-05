package com.mars.library.controller.dto;


import java.util.List;


public class UtilisateurEnRetardDto {


    private String nom;
    private String prenom;
    private String email;

    private List<LivreEnRetardDto> livresEnRetards;

    public String getNom() {
        return nom;
    }

    public UtilisateurEnRetardDto setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public UtilisateurEnRetardDto setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UtilisateurEnRetardDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<LivreEnRetardDto> getLivresEnRetards() {
        return livresEnRetards;
    }

    public UtilisateurEnRetardDto setLivresEnRetards(List<LivreEnRetardDto> livresEnRetards) {
        this.livresEnRetards = livresEnRetards;
        return this;
    }
}
