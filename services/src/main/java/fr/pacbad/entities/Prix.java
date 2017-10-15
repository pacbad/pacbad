package fr.pacbad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Prix")
public class Prix implements SimpleEntity {

	public Prix() {
	}

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombreTableaux")
	private int nombreTableaux;

	@Column(name = "montant")
	private int montant;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getNombreTableaux() {
		return nombreTableaux;
	}

	public void setNombreTableaux(final int nombreTableaux) {
		this.nombreTableaux = nombreTableaux;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(final int montant) {
		this.montant = montant;
	}

}
