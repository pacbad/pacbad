package fr.pacbad.services;

import fr.pacbad.dao.InstanceDao;
import fr.pacbad.dao.SimpleDao;
import fr.pacbad.entities.Instance;

public class InstanceService extends SimpleService<Instance> {

	@Override
	protected SimpleDao<Instance> createDao() {
		return new InstanceDao();
	}

}
