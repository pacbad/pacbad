package fr.pacbad.entities.ref;

public enum CategorieEnum {
	INCONNUE(0, "?", "?"), POUSSIN(1, "Pou", "Poussin"), BENJAMIN(2, "Ben1", "Benjamin 1"), MINIME(3, "Min1",
			"Minime 1"), CADET(4, "Cad1", "Cadet 1"), JUNIOR(5, "Jun1", "Junior 1"), SENIOR(6, "Sen",
					"Senior"), VETERAN(7, "Vet2", "Vétéran 2"), MINIBAD(11, "MBad", "Minibad"), BENJAMIN1(12, "Ben2",
							"Benjamin 2"), MINIME1(13, "Min2", "Minime 2"), CADET1(14, "Cad2", "Cadet 2"), JUNIOR1(15,
									"Jun2", "Junior 2"), VETERAN1(17, "Vet3", "Vétéran 3"), VETERAN2(18, "Vet4",
											"Vétéran 4"), VETERAN3(19, "Vet5", "Vétéran 5"), SENIOR1(20, "Vet1",
													"Vétéran 1"), VETERAN4(21, "Vet6",
															"Vétéran 6"), VETERAN5(22, "Vet7", "Vétéran 7");

	private int id;

	private String nomCourt;

	private String nomLong;

	CategorieEnum(final int id, final String nomCourt, final String nomLong) {
		this.id = id;
		this.nomCourt = nomCourt;
		this.nomLong = nomLong;
	}

	public static CategorieEnum get(final Object value) {
		if (value == null) {
			return null;
		}
		for (final CategorieEnum cat : values()) {
			if (value.equals(cat.id) || value.equals(cat.nomCourt) || value.equals(cat.nomLong)) {
				return cat;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public String getNomLong() {
		return nomLong;
	}

	public String getNomCourt() {
		return nomCourt;
	}
}
