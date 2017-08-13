package fr.pacbad.services;

import java.util.List;

import fr.pacbad.dao.SimpleDao;
import fr.pacbad.entities.SimpleEntity;

public abstract class SimpleService<T extends SimpleEntity> {

	private SimpleDao<T> dao = createDao();

	protected abstract SimpleDao<T> createDao();
	
	protected SimpleDao<T> getDao() {
		return dao;
	}

	public T create(T e) {
		return getDao().create(e);
	}

	public T update(T e) {
		return getDao().update(e);
	}

	public T createOrUpdate(T e) {
		if (e.getId() == null) {
			return create(e);
		} else {
			return update(e);
		}
	}

	public void delete(T e) {
		getDao().delete(e);
	}

	public T getById(final Long id) {
		return getDao().getById(id);
	}

	public List<T> getAll() {
		return getDao().getAll();
	}

}