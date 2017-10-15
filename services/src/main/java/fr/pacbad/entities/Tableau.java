package fr.pacbad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tableau {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nbJoueurMax")
	private int nbJoueurMax;

	@Column(name = "coteMin")
	private float coteMin;

	@Column(name = "coteMax")
	private float coteMax;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getNbJoueurMax() {
		return nbJoueurMax;
	}

	public void setNbJoueurMax(final int nbJoueurMax) {
		this.nbJoueurMax = nbJoueurMax;
	}

	public float getCoteMin() {
		return coteMin;
	}

	public void setCoteMin(final float coteMin) {
		this.coteMin = coteMin;
	}

	public float getCoteMax() {
		return coteMax;
	}

	public void setCoteMax(final float coteMax) {
		this.coteMax = coteMax;
	}

}
