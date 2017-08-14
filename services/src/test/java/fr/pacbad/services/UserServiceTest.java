package fr.pacbad.services;

import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest extends AbstractServiceTest<UserService> {

	@Override
	protected UserService createService() {
		return new UserService();
	}

	@Test
	public void testAuthenticate() throws Exception {
		service.authenticate("test", "test");
	}

	@Test
	public void testHash() {
		final String hash = service.hash("test");
		Assert.assertNotEquals("test", hash);
	}

}
