package fr.pacbad.services;

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
	
}
