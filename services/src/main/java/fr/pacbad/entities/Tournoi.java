package fr.pacbad.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Tournoi")
public class Tournoi implements SimpleEntity {

	public Tournoi() {
	}

	public Tournoi(final String nom, final Date dateDebut, final Date dateFin) {
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom")
	private String nom;

	@Column(name = "date_debut")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Column(name = "date_fin")
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Column(name = "lieu")
	private String lieu;

	@Column(name = "departement")
	private String departement;

	@Column(name = "region")
	private String region;

	@Column(name = "nb_joueurs_max")
	private int nbJoueursMax;

	@Column(name = "nb_terrains")
	private int nbTerrains;

	@Column(name = "identifiant_poona")
	private String identifiantPoona;

	@Column(name = "decoupage_cote")
	private Boolean decoupageParCote;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(final Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(final Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(final String lieu) {
		this.lieu = lieu;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(final String region) {
		this.region = region;
	}

	public int getNbJoueursMax() {
		return nbJoueursMax;
	}

	public void setNbJoueursMax(final int nbJoueursMax) {
		this.nbJoueursMax = nbJoueursMax;
	}

	public int getNbTerrains() {
		return nbTerrains;
	}

	public void setNbTerrains(final int nbTerrains) {
		this.nbTerrains = nbTerrains;
	}

	public String getIdentifiantPoona() {
		return identifiantPoona;
	}

	public void setIdentifiantPoona(final String identifiantPoona) {
		this.identifiantPoona = identifiantPoona;
	}

	public Boolean getDecoupageParCote() {
		return decoupageParCote;
	}

	public void setDecoupageParCote(final Boolean decoupageParCote) {
		this.decoupageParCote = decoupageParCote;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(final String departement) {
		this.departement = departement;
	}

}
