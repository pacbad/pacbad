package fr.pacbad.entities.ffbad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.pacbad.entities.Instance;

public class DetailInstance {

	@JsonProperty("Instance")
	private Map<Integer, Instance> instance;

	@JsonProperty("Referees")
	private Map<Integer, Arbitre> arbitres;

	@JsonProperty("Board")
	private Map<Integer, MembreBureau> membresBureau;

	public Instance getInstance() {
		if (instance == null || instance.isEmpty()) {
			return null;
		}
		return instance.values().iterator().next();
	}

	public Collection<Arbitre> getArbitres() {
		if (arbitres == null) {
			return new ArrayList<>();
		}
		return arbitres.values();
	}

	public Collection<MembreBureau> getBureau() {
		if (membresBureau == null) {
			return new ArrayList<>();
		}
		return membresBureau.values();
	}

}
