package com.mars.library.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Emprunt {

	@Id
private int id; 

private Date dateRenduPrevu;
private Date datelimite;
private Date rendu;

@ManyToOne
private Ouvrage ouvrage;
@ManyToOne
private Utilisateur utilisateur;




private Boolean prolongPret;
	
	private Date dateEmprunt;

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Boolean getProlongPret() {
		return prolongPret;
	}

	public void setProlongPret(Boolean prolongPret) {
		this.prolongPret = prolongPret;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	} 
	
	
	
}