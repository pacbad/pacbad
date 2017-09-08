package fr.pacbad.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import fr.pacbad.entities.SimpleEntity;
import fr.pacbad.filter.TransactionFilter;

public abstract class SimpleDao<T extends SimpleEntity> {

	protected EntityManager getEntityManager() {
		return TransactionFilter.getEntityManager();
	}

	public T create(final T e) {
		getEntityManager().persist(e);
		return detach(e);
	}

	public T update(final T e) {
		getEntityManager().merge(e);
		return detach(e);
	}

	public void delete(final T e) {
		getEntityManager().remove(e);
	}

	public T getById(final Long id) {
		return detach(getEntityManager().find(getEntityType(), id));
	}

	@SuppressWarnings("unchecked")
	public T getByColumn(final String columnName, final Object value) {
		try {
			return detach((T) getEntityManager().createQuery(
					"Select e from " + getEntityType().getSimpleName() + " e where e." + columnName + " = :value")
					.setParameter("value", value).getSingleResult());
		} catch (final NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return detach(getEntityManager().createQuery("Select e from " + getEntityType().getSimpleName() + " e")
				.getResultList());
	}

	protected Class<T> getEntityType() {
		@SuppressWarnings("unchecked")
		final Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return persistentClass;
	}

	protected T detach(final T e) {
		getEntityManager().detach(e);
		return e;
	}

	protected List<T> detach(final List<T> list) {
		for (final T e : list) {
			detach(e);
		}
		return list;
	}

}
