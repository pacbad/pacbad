package fr.pacbad.entities.ffbad;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.pacbad.entities.Instance;

public class WSDetailInstance {

	@JsonProperty("Retour")
	private DetailInstance detailInstance;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Statut")
	private String statut;

	public Instance getInstance() {
		if (detailInstance == null || detailInstance.getInstance() == null) {
			return null;
		}
		final Instance instance = detailInstance.getInstance();
		instance.setArbitres(detailInstance.getArbitres());
		instance.setBureau(detailInstance.getBureau());
		return instance;
	}

	public String getMessage() {
		return message;
	}

	public String getStatut() {
		return statut;
	}

	@Override
	public String toString() {
		final Instance instance = getInstance();
		if (instance == null) {
			return null;
		}
		return instance.toString();
	}
}
