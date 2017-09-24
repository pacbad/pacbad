package fr.pacbad.entities.ffbad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.pacbad.entities.Instance;

public class WSListeInstance {

	@JsonProperty("Retour")
	private Map<Integer, Instance> instances;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Statut")
	private String statut;

	public List<Instance> getInstances() {
		if (instances == null || instances.isEmpty()) {
			return new ArrayList<>();
		}
		return new ArrayList<>(instances.values());
	}

	public String getMessage() {
		return message;
	}

	public String getStatut() {
		return statut;
	}

	@Override
	public String toString() {
		final List<Instance> instances = getInstances();
		if (instances == null) {
			return null;
		}
		return instances.toString();
	}
}
