package fr.pacbad.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "User")
public class User implements SimpleEntity {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "identifiant")
	private String identifiant;

	@Column(name = "hash")
	private String hash;

	@Column(name = "mail")
	private String mail;

	@Column(name = "licence")
	private Long licence;

	/* Champs non renseignés mais issus de la base fédérale */

	@Column(name = "nom")
	private String nom;

	@Column(name = "prenom")
	private String prenom;

	@Column(name = "role")
	private String role;

	/* Champs non stockés en base, mais nécessaire pour dialoguer avec le client */

	private String ancienPassword;
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "fr_FR", timezone = "Europe/Paris")
	private Date dateNaissance;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(final String identifiant) {
		this.identifiant = identifiant;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(final String hash) {
		this.hash = hash;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public Long getLicence() {
		return licence;
	}

	public void setLicence(final Long licence) {
		this.licence = licence;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(final Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAncienPassword() {
		return ancienPassword;
	}

	public void setAncienPassword(final String ancienPassword) {
		this.ancienPassword = ancienPassword;
	}

}
