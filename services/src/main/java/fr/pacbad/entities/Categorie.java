package fr.pacbad.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.pacbad.entities.ref.CategorieEnum;

@Entity(name = "Categorie")
public class Categorie implements SimpleEntity {

	public Categorie() {
	}

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
	private List<CategorieEnum> types;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public List<CategorieEnum> getTypes() {
		return types;
	}

	public void setTypes(final List<CategorieEnum> types) {
		this.types = types;
	}

}
