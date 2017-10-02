package fr.pacbad.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.jpa.QueryHints;

import fr.pacbad.entities.SimpleEntity;
import fr.pacbad.filter.TransactionFilter;

public abstract class SimpleDao<T extends SimpleEntity> {

	protected EntityManager getEntityManager() {
		return TransactionFilter.getEntityManager();
	}

	public T create(final T e) {
		getEntityManager().persist(e);
		return e;
	}

	public T update(final T e) {
		getEntityManager().merge(e);
		return e;
	}

	public void delete(final T e) {
		getEntityManager().remove(e);
	}

	public T getById(final Long id) {
		return getEntityManager().find(getEntityType(), id);
	}

	@SuppressWarnings("unchecked")
	public T getByColumn(final String columnName, final Object value) {
		try {
			final Query query = getEntityManager().createQuery(
					"Select e from " + getEntityType().getSimpleName() + " e where e." + columnName + " = :value")
					.setParameter("value", value);
			cacheQueryIfNeeded(query);
			return (T) query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getListByColumn(final String columnName, final Object value) {
		try {
			final Query query = getEntityManager().createQuery(
					"Select e from " + getEntityType().getSimpleName() + " e where e." + columnName + " = :value")
					.setParameter("value", value);
			cacheQueryIfNeeded(query);
			return query.getResultList();
		} catch (final NoResultException e) {
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		final Query query = getEntityManager().createQuery("Select e from " + getEntityType().getSimpleName() + " e");
		cacheQueryIfNeeded(query);
		return query.getResultList();
	}

	public long count() {
		final Query query = getEntityManager()
				.createQuery("Select count(e.id) from " + getEntityType().getSimpleName() + " e");
		cacheQueryIfNeeded(query);
		return (long) query.getSingleResult();
	}

	protected Class<T> getEntityType() {
		@SuppressWarnings("unchecked")
		final Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return persistentClass;
	}

	protected void cacheQueryIfNeeded(final Query query) {
		final Cacheable cacheableAnnotation = getEntityType().getAnnotation(Cacheable.class);
		if (cacheableAnnotation != null && cacheableAnnotation.value()) {
			query.setHint(QueryHints.HINT_CACHEABLE, true);
		}
	}

	public T detach(final T e) {
		getEntityManager().detach(e);
		return e;
	}

	public List<T> detach(final List<T> list) {
		for (final T e : list) {
			detach(e);
		}
		return list;
	}

}
