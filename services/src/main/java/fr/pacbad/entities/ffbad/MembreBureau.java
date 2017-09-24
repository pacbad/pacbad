package fr.pacbad.entities.ffbad;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MembreBureau {

	@JsonProperty("DIF_NOM")
	private String role;

	@JsonProperty("PER_ID")
	private long id;

	@JsonProperty("PER_LICENCE")
	private String licence;

	@JsonProperty("PER_NOM")
	private String nom;

	@JsonProperty("PER_PRENOM")
	private String prenom;

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(final String licence) {
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

}
