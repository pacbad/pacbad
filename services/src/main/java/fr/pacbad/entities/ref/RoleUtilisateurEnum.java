package fr.pacbad.entities.ref;

public enum RoleUtilisateurEnum {

	JOUEUR("joueur"),

	RESPONSABLE_CLUB("responsable"),

	ADMIN("administrateur");

	private String nom;

	RoleUtilisateurEnum(final String nom) {
		this.nom = nom;
	}

	public static RoleUtilisateurEnum get(final String nom) {
		for (final RoleUtilisateurEnum role : values()) {
			if (role.getNom().equals(nom)) {
				return role;
			}
		}
		throw new IllegalArgumentException("Role inconnu : " + nom);
	}

	public String getNom() {
		return nom;
	}

}
