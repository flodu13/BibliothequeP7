package com.mars.library.controller.dto;


import javax.persistence.*;
import java.util.List;


public class LivresEnRetardDto {

    private int id;
    private String nom;
    private String prenom;
    private String email;

    private List<LivresEnRetardDto> livresEnRetards;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LivresEnRetardDto> getLivresEnRetards() {
        return livresEnRetards;
    }

    public void setLivresEnRetards(List<LivresEnRetardDto> livresEnRetards) {
        this.livresEnRetards = livresEnRetards;
    }
}
