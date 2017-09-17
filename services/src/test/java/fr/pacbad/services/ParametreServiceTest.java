package fr.pacbad.services;

import org.junit.Assert;
import org.junit.Test;

import fr.pacbad.entities.Parametre;
import fr.pacbad.filter.TransactionFilter;

public class ParametreServiceTest extends AbstractServiceTest<ParametreService> {

	@Override
	protected ParametreService createService() {
		return new ParametreService();
	}

	@Test
	public void testCacheAll() throws Exception {
		service.getAll();
		service.getAll();
	}

	@Test
	public void testCacheId() throws Exception {
		System.out.println("get from service");
		final Parametre param1 = service.getById(1L);
		Assert.assertTrue(TransactionFilter.getEntityManager().getEntityManagerFactory().getCache()
				.contains(Parametre.class, 1L));

		final Parametre param2 = service.getById(1L);

		Assert.assertTrue(param1 == param2);
	}

	@Test
	public void testCacheColumn() throws Exception {
		System.out.println("get by clef");
		final String param1 = service.getString("poona.login");
		final String param2 = service.getString("poona.login");

		Assert.assertTrue(param1 == param2);
	}

}
