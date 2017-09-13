package fr.pacbad.entities.ffbad;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WSJoueurDetail {

	@JsonProperty("Retour")
	private Map<Integer, PlayerInformation> information;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Statut")
	private String statut;

	public PlayerInformation getInformation() {
		if (information == null || information.isEmpty()) {
			return null;
		}
		final PlayerInformation player = information.values().iterator().next();
		return player;
	}

	public String getMessage() {
		return message;
	}

	public String getStatut() {
		return statut;
	}

	@Override
	public String toString() {
		final PlayerInformation info = getInformation();
		if (info == null) {
			return null;
		}
		return info.toString();
	}
}
