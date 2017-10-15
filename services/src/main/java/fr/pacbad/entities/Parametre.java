package fr.pacbad.entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Parametre implements SimpleEntity {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "clef")
	private String clef;

	@Column(name = "valeur")
	private String valeur;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getClef() {
		return clef;
	}

	public void setClef(final String clef) {
		this.clef = clef;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(final String valeur) {
		this.valeur = valeur;
	}

}
