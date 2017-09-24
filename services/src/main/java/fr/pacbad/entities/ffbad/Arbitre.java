package fr.pacbad.entities.ffbad;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Arbitre {

	@JsonProperty("PER_LICENCE")
	private String licence;

	@JsonProperty("PER_NOM")
	private String nom;

	@JsonProperty("PER_PRENOM")
	private String prenom;

	@JsonProperty("SOC")
	private String soc;

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

	public String getSoc() {
		return soc;
	}

	public void setSoc(final String soc) {
		this.soc = soc;
	}

}
