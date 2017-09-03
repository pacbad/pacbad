package fr.pacbad.resources;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.pacbad.PacbadTest;
import fr.pacbad.resources.AuthentificationResourceImpl.UserLogin;
import fr.pacbad.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultJws;

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
		Mockito.when(userService.issueToken(ArgumentMatchers.anyString())).thenReturn("abc");
		Mockito.when(userService.getClaims("abc")).thenReturn(new DefaultJws<Claims>(null, null, null));
		final UserLogin userLogin = new UserLogin();
		userLogin.login = "benjamin";
		userLogin.password = "test";
		final Response response = authentificationResource.login(userLogin);

		Assert.assertEquals(200, response.getStatus());
		Assert.assertNotNull(response.getHeaderString("Authorization"));
		Assert.assertEquals("Bearer abc", response.getHeaderString("Authorization"));
	}

	@Test
	public void testLoginFail() {
		try {
			Mockito.doThrow(Exception.class).when(userService).authenticate(ArgumentMatchers.anyString(),
					ArgumentMatchers.anyString());
		} catch (final Exception e) {
			Assert.fail("Impossible d'initialiser le bouchon");
		}

		final UserLogin userLogin = new UserLogin();
		userLogin.login = "benjamin";
		userLogin.password = "test";
		final Response response = authentificationResource.login(userLogin);

		Assert.assertEquals(401, response.getStatus());
	}

}
