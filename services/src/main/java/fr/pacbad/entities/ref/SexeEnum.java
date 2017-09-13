package fr.pacbad.entities.ref;

public enum SexeEnum {

	HOMME(1, "H"),

	FEMME(2, "F");

	private int id;

	private String nomCourt;

	SexeEnum(final int id, final String nomCourt) {
		this.id = id;
		this.nomCourt = nomCourt;
	}

	public static SexeEnum get(final int sexe) {
		for (final SexeEnum s : values()) {
			if (s.getId() == sexe) {
				return s;
			}
		}
		return HOMME;
	}

	public int getId() {
		return id;
	}

	public String getNomCourt() {
		return nomCourt;
	}

}
