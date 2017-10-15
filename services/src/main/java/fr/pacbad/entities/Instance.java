package fr.pacbad.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.pacbad.entities.ffbad.Arbitre;
import fr.pacbad.entities.ffbad.MembreBureau;

@Entity
public class Instance implements SimpleEntity {

	@Column(name = "id")
	@Id
	@JsonProperty("INS_ID")
	private Long id;

	@Column(name = "niveau")
	@JsonProperty("INS_NIVEAU")
	private Integer niveau;

	@Column(name = "nom")
	@JsonProperty("INS_NOM")
	private String nom;

	@Column(name = "sigle")
	@JsonProperty("INS_SIGLE")
	private String sigle;

	@Column(name = "departement")
	@JsonProperty("INS_NUMERO_DEPT")
	private String departement;

	@Column(name = "numeroClub")
	@JsonProperty("INS_NUMERO_CLUB")
	private String numeroClub;

	@Column(name = "siteWeb")
	@JsonProperty("INS_SITE_WEB")
	private String siteWeb;

	@Column(name = "telephone")
	@JsonProperty("INS_TEL")
	private String telephone;

	@Column(name = "mobile")
	@JsonProperty("INS_MOBILE")
	private String mobile;

	@Column(name = "mail")
	@JsonProperty("INS_MAIL")
	private String mail;

	@Column(name = "urlLogo")
	@JsonProperty("INS_LOGO")
	private String urlLogo;

	@Column(name = "adresse")
	@JsonProperty("ADR_ADRESSE")
	private String adresse;

	@Column(name = "codePostal")
	@JsonProperty("ADR_CODE_POSTAL")
	private String codePostal;

	@Column(name = "ville")
	@JsonProperty("ADR_VILLE")
	private String ville;

	@JsonProperty("LAT_NB_ETOILE")
	private Integer nbEtoiles;

	@OneToMany
	private List<LienUserInstance> utilisateurs;

	private transient Instance codep;

	private transient Instance ligue;

	private transient Collection<Arbitre> arbitres;

	private transient Collection<MembreBureau> bureau;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Integer getNiveau() {
		return niveau;
	}

	public void setNiveau(final Integer niveau) {
		this.niveau = niveau;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(final String departement) {
		this.departement = departement;
	}

	public String getNumeroClub() {
		return numeroClub;
	}

	public void setNumeroClub(final String numeroClub) {
		this.numeroClub = numeroClub;
	}

	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(final String siteWeb) {
		this.siteWeb = siteWeb;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(final String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(final String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(final String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(final String ville) {
		this.ville = ville;
	}

	public Instance getCodep() {
		return codep;
	}

	public void setCodep(final Instance codep) {
		this.codep = codep;
	}

	public Instance getLigue() {
		return ligue;
	}

	public void setLigue(final Instance ligue) {
		this.ligue = ligue;
	}

	public Integer getNbEtoiles() {
		return nbEtoiles;
	}

	public void setNbEtoiles(final Integer nbEtoiles) {
		this.nbEtoiles = nbEtoiles;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(final String sigle) {
		this.sigle = sigle;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public Collection<Arbitre> getArbitres() {
		return arbitres;
	}

	public void setArbitres(final Collection<Arbitre> arbitres) {
		this.arbitres = arbitres;
	}

	public Collection<MembreBureau> getBureau() {
		return bureau;
	}

	public void setBureau(final Collection<MembreBureau> bureau) {
		this.bureau = bureau;
	}

	public List<LienUserInstance> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(final List<LienUserInstance> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
