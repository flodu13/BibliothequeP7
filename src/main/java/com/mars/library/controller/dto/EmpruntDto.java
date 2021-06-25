package com.mars.library.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EmpruntDto {

    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateRenduPrevu;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date datelimite;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date rendu;
    private int ouvrageID;
    private int utilisateurId;

    public int getOuvrageID() {
        return ouvrageID;
    }

    public void setOuvrageID(int ouvrageID) {
        this.ouvrageID = ouvrageID;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateRenduPrevu() {
        return dateRenduPrevu;
    }

    public void setDateRenduPrevu(Date dateRenduPrevu) {
        this.dateRenduPrevu = dateRenduPrevu;
    }

    public Date getDatelimite() {
        return datelimite;
    }

    public void setDatelimite(Date datelimite) {
        this.datelimite = datelimite;
    }

    public Date getRendu() {
        return rendu;
    }

    public void setRendu(Date rendu) {
        this.rendu = rendu;
    }
}
