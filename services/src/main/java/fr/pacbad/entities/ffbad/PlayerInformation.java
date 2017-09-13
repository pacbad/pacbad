package fr.pacbad.entities.ffbad;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.pacbad.entities.ref.CategorieEnum;
import fr.pacbad.entities.ref.SexeEnum;

public class PlayerInformation {

	@JsonProperty("PER_ID")
	private long id;

	@JsonProperty("PER_NOM")
	private String nom;

	@JsonProperty("PER_NOM_JEUNE_FILLE")
	private String nomJeuneFille;
	@JsonProperty("PER_PRENOM")
	private String prenom;
	@JsonProperty("PER_NAISSANCE")
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "fr_FR", timezone = "Europe/Paris")
	private Date dateNaissance;
	@JsonProperty("PER_LICENCE")
	private String licence;
	@JsonProperty("PER_IS_DATA_PUBLIC")
	private Boolean dataPublic;
	@JsonProperty("JOU_IS_MUTE")
	private Boolean mute;
	// @JsonProperty("PER_PEC_ID")
	// private int civilite;
	@JsonProperty("PER_PES_ID")
	private Integer sexe;
	@JsonProperty("INS_ID")
	private Long clubId;
	@JsonProperty("INS_SIGLE")
	private String sigleClub;
	@JsonProperty("INS_NOM")
	private String nomClub;
	private String commentaire;
	private Date derniereMiseAJour;

	@JsonProperty("PER_PEC_ID")
	private Integer civiliteId;
	@JsonProperty("JOC_NOM_COURT")
	private String categorieNomCourt;
	@JsonProperty("JOC_NOM_LONG")
	private String categorieNomLong;

	@JsonProperty("JOU_CREATION")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "fr_FR", timezone = "Europe/Paris")
	private Date dateCreation;

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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(final String licence) {
		this.licence = licence;
	}

	public boolean isDataPublic() {
		if (dataPublic == null) {
			return false;
		}
		return dataPublic;
	}

	public SexeEnum getSexe() {
		if (sexe == null) {
			sexe = SexeEnum.HOMME.getId();
		}
		return SexeEnum.get(sexe);
	}

	public void setSexe(final int sexe) {
		this.sexe = sexe;
	}

	public String getSigleClub() {
		return sigleClub;
	}

	public void setSigleClub(final String sigleClub) {
		this.sigleClub = sigleClub;
	}

	public String getNomClub() {
		return nomClub;
	}

	public void setNomClub(final String nomClub) {
		this.nomClub = nomClub;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(final String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDerniereMiseAJour() {
		return derniereMiseAJour;
	}

	public void setDerniereMiseAJour(final Date derniereMiseAJour) {
		this.derniereMiseAJour = derniereMiseAJour;
	}

	public CategorieEnum getCategorie() {
		CategorieEnum cat = CategorieEnum.get(categorieNomLong);
		if (cat == null) {
			cat = CategorieEnum.get(categorieNomCourt);
		}
		if (cat == null) {
			cat = CategorieEnum.INCONNUE;
		}
		return cat;
	}

	public long getClubId() {
		if (clubId == null) {
			clubId = 0L;
		}
		return clubId;
	}

	public boolean isMute() {
		if (mute == null) {
			return false;
		}
		return mute;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(final Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return nom + ' ' + prenom;
	}

}
