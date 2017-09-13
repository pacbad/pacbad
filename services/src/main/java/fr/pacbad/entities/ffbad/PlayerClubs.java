package fr.pacbad.entities.ffbad;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerClubs {
	// "INS_ID":"615",
	// "INS_NOM":"Issy Les Mx Badminton Club",
	// "INS_NIVEAU":"4",
	// "INS_SITE_WEB":"http://www.imbc92.fr",
	// "INS_IS_ACTIVE":"1",
	// "INS_SIGLE":"IMBC92",
	// "INS_TEL":"",
	// "INS_MOBILE":"0686403623",
	// "INS_FAX":"",
	// "INS_MAIL":"club@imbc92.fr",
	// "INS_LOGO":"logo_IMBC92_53f374130dd24.jpg",
	// "INS_NUMERO_CLUB":"LIFB.92.95.010",
	// "INS_CODE_POSTAL":"92130",
	// "INS_NUMERO_DEPT":"92",
	// "INS_VILLE":"Issy-les-Moulineaux",
	// "INS_INS_ID_CODEP":"66",
	// "INS_INS_ID_LIGUE":"12",
	// "INS_INS_ID":"66"
	@JsonProperty("SAI_NOM")
	private String saison;
	@JsonProperty("INS_ID")
	private long id;
	@JsonProperty("INS_NOM")
	private String nom;
	@JsonProperty("INS_NIVEAU")
	private int niveau;
	@JsonProperty("INS_SITE_WEB")
	private String siteWeb;
	@JsonProperty("INS_IS_ACTIVE")
	private boolean active;
	@JsonProperty("INS_SIGLE")
	private String sigle;
	@JsonProperty("INS_TEL")
	private String tel;
	@JsonProperty("INS_MOBILE")
	private String mobile;
	@JsonProperty("INS_FAX")
	private String fax;
	@JsonProperty("INS_MAIL")
	private String mail;
	@JsonProperty("INS_LOGO")
	private String logo;
	@JsonProperty("INS_NUMERO_CLUB")
	private String numeroClub;
	@JsonProperty("INS_CODE_POSTAL")
	private String codePostal;
	@JsonProperty("INS_NUMERO_DEPT")
	private String departement;
	@JsonProperty("INS_VILLE")
	private String ville;
	@JsonProperty("INS_INS_ID_CODEP")
	private long codepId;
	@JsonProperty("INS_INS_ID_LIGUE")
	private long ligueId;
	@JsonProperty("INS_INS_ID")
	private long instanceParenteId;

	public String getSaison() {
		return saison;
	}

	public boolean isSaisonCourante() {
		try {
			final String[] annees = saison.split(" ")[1].split("-");
			final Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(annees[0]), Calendar.SEPTEMBER, 1);
			final Date calDebutSaison = cal.getTime();
			cal.set(Integer.parseInt(annees[1]), Calendar.AUGUST, 31);
			final Date calFinSaison = cal.getTime();
			return calDebutSaison.before(new Date()) && calFinSaison.after(new Date());

		} catch (final Throwable t) {
			t.printStackTrace();
			return false;
		}
	}

	public String getSigle() {
		return sigle;
	}

	public String getNom() {
		return nom;
	}

	public long getId() {
		return id;
	}

}
