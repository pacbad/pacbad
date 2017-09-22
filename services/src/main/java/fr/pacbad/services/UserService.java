package fr.pacbad.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;

import fr.pacbad.auth.KeyGenerator;
import fr.pacbad.dao.SimpleDao;
import fr.pacbad.dao.UserDao;
import fr.pacbad.entities.User;
import fr.pacbad.entities.ffbad.WSJoueurDetail;
import fr.pacbad.entities.ref.RoleUtilisateurEnum;
import fr.pacbad.exception.ExceptionFonctionnelle;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserService extends SimpleService<User> {

	private static final String SALT = "blablablatextquisertdesaliere";

	@Inject
	private KeyGenerator keyGenerator;

	@Inject
	private PoonaService poonaService;

	@Override
	protected SimpleDao<User> createDao() {
		return new UserDao();
	}

	public void authenticate(final String login, final String password) throws ExceptionFonctionnelle {
		final User u = getByIdentifiant(login);
		final String hash = hash(password);
		if (u != null && hash.equals(u.getHash())) {
			return;
		}
		throw new ExceptionFonctionnelle("Impossible de se connecter : utilisateur ou mot de passe incorrect")
				.setStatus(Status.UNAUTHORIZED);
	}

	public void register(final User user) throws ExceptionFonctionnelle, IOException {
		// Règles de gestion :
		// Vérifier que le login n'est pas déjà utilisé
		if (user.getIdentifiant() == null || user.getIdentifiant().isEmpty()) {
			throw new ExceptionFonctionnelle("Identifiant inconnu");
		}
		if (user.getDateNaissance() == null) {
			throw new ExceptionFonctionnelle("Date de naissance inconnue");
		}

		final User userAvecMemeLogin = getByIdentifiant(user.getIdentifiant());
		if (userAvecMemeLogin != null) {
			throw new ExceptionFonctionnelle("Identifiant déjà utilisé : " + userAvecMemeLogin.getIdentifiant());
		}

		final User userAvecMemeLicence = getByLicence(user.getLicence());
		if (userAvecMemeLicence != null) {
			throw new ExceptionFonctionnelle(
					"Un utilisateur avec cette licence existe déjà : " + userAvecMemeLicence.getIdentifiant());
		}

		final Long licence = user.getLicence();
		if (licence == null || licence <= 0) {
			throw new ExceptionFonctionnelle("Licence invalide : " + licence);
		}
		// Récupération du joueur dans Poona (à partir de sa licence)
		try {
			final WSJoueurDetail joueurPoona = poonaService.getByLicence(String.valueOf(user.getLicence()));
			if (joueurPoona == null || joueurPoona.getInformation() == null) {
				throw new ExceptionFonctionnelle(
						"Impossible de récupérer les informations depuis Poona pour la licence " + user.getLicence()
								+ ". Merci de réessayer plus tard.");
			}
			// Vérifier que la licence est valide avec la date de naissance saisie
			if (joueurPoona.getInformation().getDateNaissance() == null) {
				throw new ExceptionFonctionnelle("Date de naissance inconnue dans Poona");
			}
			if (!joueurPoona.getInformation().getDateNaissance().equals(user.getDateNaissance())) {
				throw new ExceptionFonctionnelle("Date de naissance incorrecte : " + user.getDateNaissance());
			}

			user.setNom(joueurPoona.getInformation().getNom());
			user.setPrenom(joueurPoona.getInformation().getPrenom());
		} catch (final IOException e) {
			throw new IOException("Connexion à Poona impossible", e);
		}

		// Hash du mot de passe
		user.setHash(hash(user.getPassword()));

		user.setDateCreation(new Date());

		// TODO Mettre à jour le rôle de l'utilisateur en fonction des informations du
		// club récupérées dans Poona (pour savoir s'il est responsable ou joueur
		// simple)
		user.setRole(RoleUtilisateurEnum.JOUEUR.getNom());

		// Enregistrement de l'utilisateur en base
		create(user);
	}

	public User getByIdentifiant(final String identifiant) {
		User user = getDao().getByColumn("identifiant", identifiant);
		if (user != null) {
			user = detach(user);
		}
		return user;
	}

	public User getByLicence(final Long licence) {
		User user = getDao().getByColumn("licence", licence);
		if (user != null) {
			user = detach(user);
		}
		return user;
	}

	public String hash(final String password) {
		final String hash;
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(SALT.getBytes("UTF-8"));
			final byte[] bytes = md.digest(password.getBytes("UTF-8"));
			final StringBuilder sb = new StringBuilder();
			for (final byte b : bytes) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			hash = sb.toString();
		} catch (final NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException("Impossible de créer un hash pour le mot de passe", e);
		}
		return hash;
	}

	public String issueToken(final String login) {
		final Key key = keyGenerator.getKey();
		final Calendar cal = Calendar.getInstance();
		// Le jeton sera valide pendant une heure
		cal.add(Calendar.HOUR, 1);
		final Date expirationDate = cal.getTime();
		final String jwtToken = Jwts.builder().setSubject(login).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return jwtToken;
	}

	public Jws<Claims> getClaims(final String token, final User u) {
		final Key key = keyGenerator.getKey();
		final Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

		claims.getBody().put("token", token);

		if (u != null) {
			claims.getBody().put("prenom", u.getPrenom());
			claims.getBody().put("nom", u.getNom());
			claims.getBody().put("licence", u.getLicence());
			claims.getBody().put("mail", u.getMail());
			claims.getBody().put("role", u.getRole());
			claims.getBody().put("dateCreation", u.getDateCreation());
		}

		return claims;
	}

	public User updateCompte(final User user) {
		final User u = getByIdentifiant(user.getIdentifiant());
		// mise à jour du mot de passe
		if (user.getPassword() != null) {
			u.setHash(hash(user.getPassword()));
		}
		// mise à jour du mail
		u.setMail(user.getMail());
		return update(u);
	}

	public List<User> getAdministrateurs() {
		return getDao().getListByColumn("role", RoleUtilisateurEnum.ADMIN.getNom());
	}

	public void deleteAdministrateur(final String identifiantAdmin) throws ExceptionFonctionnelle {
		final User u = getByIdentifiant(identifiantAdmin);
		if (u == null) {
			throw new ExceptionFonctionnelle("Utilisateur inconnu : " + identifiantAdmin);
		}
		if (RoleUtilisateurEnum.get(u.getRole()) != RoleUtilisateurEnum.ADMIN) {
			throw new ExceptionFonctionnelle("L'utilisateur n'est pas un administrateur");
		}

		// TODO vérifier si l'utilisateur a un rôle de responsable de club

		u.setRole(RoleUtilisateurEnum.JOUEUR.getNom());
		update(u);
	}

	public void addAdministrateur(final String identifiantAdmin) throws ExceptionFonctionnelle {
		final User u = getByIdentifiant(identifiantAdmin);
		if (u == null) {
			throw new ExceptionFonctionnelle("Utilisateur inconnu : " + identifiantAdmin);
		}
		if (RoleUtilisateurEnum.get(u.getRole()) == RoleUtilisateurEnum.ADMIN) {
			throw new ExceptionFonctionnelle("L'utilisateur est déjà un administrateur");
		}

		u.setRole(RoleUtilisateurEnum.ADMIN.getNom());
		update(u);
	}

}
