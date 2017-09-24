package fr.pacbad.resources;

import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.pacbad.PacbadTest;
import fr.pacbad.entities.User;
import fr.pacbad.exception.ExceptionFonctionnelle;
import fr.pacbad.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
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
	public void testLoginOk() throws ExceptionFonctionnelle {
		Mockito.when(userService.issueToken(ArgumentMatchers.anyString())).thenReturn("abc");
		final Claims claims = new DefaultClaims();
		final User user = new User();
		user.setIdentifiant("benjamin");
		user.setPassword("test");
		Mockito.when(userService.getClaims("abc", user)).thenReturn(new DefaultJws<Claims>(null, claims, null));
		Mockito.when(userService.getByIdentifiant("benjamin")).thenReturn(user);
		final Claims c = authentificationResource.login(user);

		Assert.assertNotNull(c);
		Assert.assertEquals(claims, c);
	}

	@Test
	public void testLoginFail() {
		try {
			Mockito.doThrow(new ExceptionFonctionnelle().setStatus(Status.UNAUTHORIZED)).when(userService)
					.authenticate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
		} catch (final Exception e) {
			Assert.fail("Impossible d'initialiser le bouchon");
		}

		final User user = new User();
		user.setIdentifiant("benjamin");
		user.setPassword("test");
		try {
			authentificationResource.login(user);
			Assert.fail("Exception attendue");
		} catch (final ExceptionFonctionnelle e) {
			Assert.assertEquals(401, e.getStatus().getStatusCode());
		}
	}

}
