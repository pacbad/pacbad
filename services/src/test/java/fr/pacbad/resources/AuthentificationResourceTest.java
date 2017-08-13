package fr.pacbad.resources;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.pacbad.PacbadTest;
import fr.pacbad.services.UserService;

public class AuthentificationResourceTest extends PacbadTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private AuthentificationResourceImpl authentificationResource;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLoginOk() {
		Mockito.when(userService.issueToken(Mockito.anyString())).thenReturn("abc");
		final Response response = authentificationResource.authenticateUser("benjamin", "test");

		Assert.assertEquals(200, response.getStatus());
		Assert.assertNotNull(response.getHeaderString("Authorization"));
		Assert.assertEquals("Bearer abc", response.getHeaderString("Authorization"));
	}

	@Test
	public void testLoginFail() {
		try {
			Mockito.doThrow(Exception.class).when(userService).authenticate(Mockito.anyString(), Mockito.anyString());
		} catch (final Exception e) {
			Assert.fail("Impossible d'initialiser le bouchon");
		}
		
		final Response response = authentificationResource.authenticateUser("benjamin", "test");

		Assert.assertEquals(401, response.getStatus());
	}

}
