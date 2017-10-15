package fr.pacbad.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import fr.pacbad.entities.Instance;
import fr.pacbad.entities.LienUserInstance;
import fr.pacbad.entities.User;

public class UserDao extends SimpleDao<User> {

	public Map<Instance, List<String>> getRolesParClub(final User utilisateur) {
		final Map<Instance, List<String>> map = new HashMap<>();
		try {
			final Query query = getEntityManager()
					.createQuery("Select e from " + LienUserInstance.class.getSimpleName() + " e where e.user = :value")
					.setParameter("value", utilisateur);
			cacheQueryIfNeeded(query);
			@SuppressWarnings("unchecked")
			final List<LienUserInstance> result = query.getResultList();
			for (final LienUserInstance lien : result) {
				final Instance instance = lien.getInstance();
				final String role = lien.getRole();

				Instance foundInstance = null;
				for (final Instance i : map.keySet()) {
					if (i.getId().equals(instance.getId())) {
						foundInstance = i;
						break;
					}
				}

				if (foundInstance == null) {
					final List<String> roles = new ArrayList<>();
					roles.add(role);
					map.put(instance, roles);
				} else {
					final List<String> rolesExistant = map.get(foundInstance);
					rolesExistant.add(role);
					map.put(foundInstance, rolesExistant);
				}
			}
			return map;
		} catch (final NoResultException e) {
			return map;
		}
	}

}
