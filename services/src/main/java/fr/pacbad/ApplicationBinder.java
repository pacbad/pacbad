package fr.pacbad;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import fr.pacbad.auth.KeyGenerator;
import fr.pacbad.services.InstanceService;
import fr.pacbad.services.ParametreService;
import fr.pacbad.services.PoonaService;
import fr.pacbad.services.TournoiService;
import fr.pacbad.services.UserService;

public class ApplicationBinder extends AbstractBinder {
	@Override
	protected void configure() {
		bind(KeyGenerator.class).to(KeyGenerator.class);
		bind(UserService.class).to(UserService.class);
		bind(TournoiService.class).to(TournoiService.class);
		bind(PoonaService.class).to(PoonaService.class);
		bind(ParametreService.class).to(ParametreService.class);
		bind(InstanceService.class).to(InstanceService.class);
	}
}