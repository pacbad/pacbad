package fr.pacbad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_Instance")
public class LienUserInstance implements SimpleEntity {

	public static final String ROLE_JOUEUR = "Joueur";
	public static final String ROLE_RESPONSABLE_CLUB = "Responsable Club";

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "instance")
	private Instance instance;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Column(name = "role")
	private String role;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Instance getInstance() {
		return instance;
	}

	public void setInstance(final Instance instance) {
		this.instance = instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

}
