package fr.pacbad.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.pacbad.PacbadTest;
import fr.pacbad.entities.ffbad.WSJoueurDetail;

@Ignore
public class PoonaServiceTest extends PacbadTest {

	@InjectMocks
	private PoonaService service;

	@Mock
	private ParametreService parametre;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetByLicence() throws IOException {
		Mockito.when(parametre.getString(ArgumentMatchers.anyString())).thenReturn("mock");
		final WSJoueurDetail joueur = service.getByLicence("06638740");
		System.out.println(joueur);
		Assert.assertEquals("DURAND", joueur.getInformation().getNom());
		Assert.assertEquals("Benjamin", joueur.getInformation().getPrenom());
		final Calendar cal = Calendar.getInstance();
		cal.set(1985, Calendar.AUGUST, 2, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		final Date dateNaissance = cal.getTime();
		Assert.assertEquals(dateNaissance, joueur.getInformation().getDateNaissance());
	}

	@Test
	public void testGetByLicenceSans0() throws IOException {
		final WSJoueurDetail joueur = service.getByLicence("6638740");
		System.out.println(joueur);
		Assert.assertEquals("DURAND", joueur.getInformation().getNom());
		Assert.assertEquals("Benjamin", joueur.getInformation().getPrenom());
		final Calendar cal = Calendar.getInstance();
		cal.set(1985, Calendar.AUGUST, 2, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		final Date dateNaissance = cal.getTime();
		Assert.assertEquals(dateNaissance, joueur.getInformation().getDateNaissance());
	}

}
