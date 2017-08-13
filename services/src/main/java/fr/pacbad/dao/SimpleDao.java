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
	
	public T create(T e) {
		getEntityManager().persist(e);
		return e;
	}

	public T update(T e) {
		getEntityManager().merge(e);
		return e;
	}

	public void delete(T e) {
		getEntityManager().remove(e);
	}

	public T getById(final Long id) {
		return getEntityManager().find(getEntityType(), id);
	}

	@SuppressWarnings("unchecked")
	public T getByColumn(String columnName, Object value) {
		try {
			return (T) getEntityManager()
					.createQuery(
							"Select e from " + getEntityType().getSimpleName() + " e where e." + columnName + " = :value")
					.setParameter("value", value).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getEntityManager().createQuery("Select e from " + getEntityType().getSimpleName() + " e").getResultList();
	}

	protected Class<T> getEntityType() {
		@SuppressWarnings("unchecked")
		Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return persistentClass;
	}

}
