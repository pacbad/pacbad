package fr.pacbad.services;

import fr.pacbad.dao.ParametreDao;
import fr.pacbad.dao.SimpleDao;
import fr.pacbad.entities.Parametre;

public class ParametreService extends SimpleService<Parametre> {

	public static final String KEY_LOGGER_LEVEL = "system.logger.level";
	public static final String KEY_POONA_LOGIN = "poona.login";
	public static final String KEY_POONA_PASSWORD = "poona.password";

	@Override
	protected SimpleDao<Parametre> createDao() {
		return new ParametreDao();
	}

	public String getString(final String clef) {
		final Parametre parametre = getDao().getByColumn("clef", clef);
		if (parametre == null) {
			throw new IllegalArgumentException("Paramètre inconnu : " + clef);
		}
		return parametre.getValeur();
	}

	public int getInt(final String clef) {
		return Integer.parseInt(getString(clef));
	}

}
