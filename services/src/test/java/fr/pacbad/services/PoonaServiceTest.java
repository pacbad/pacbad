package fr.pacbad.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import fr.pacbad.entities.ffbad.WSJoueurDetail;

public class PoonaServiceTest {

	private final PoonaService service = new PoonaService();

	@Test
	public void testGetByLicence() throws IOException {
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
