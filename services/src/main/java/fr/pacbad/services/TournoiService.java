package fr.pacbad.services;

import fr.pacbad.dao.SimpleDao;
import fr.pacbad.dao.TournoiDao;
import fr.pacbad.entities.Tournoi;

public class TournoiService extends SimpleService<Tournoi> {

	@Override
	protected SimpleDao<Tournoi> createDao() {
		return new TournoiDao();
	}

}
