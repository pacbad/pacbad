package fr.pacbad.services;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import fr.pacbad.auth.KeyGenerator;
import fr.pacbad.dao.SimpleDao;
import fr.pacbad.dao.UserDao;
import fr.pacbad.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserService extends SimpleService<User> {

	private static final String SALT = "blablablatextquisertdesaliere";

	@Inject
	private KeyGenerator keyGenerator;

	@Override
	protected SimpleDao<User> createDao() {
		return new UserDao();
	}

	public void authenticate(final String login, final String password) throws Exception {
		final User u = getByIdentifiant(login);
		final String hash = hash(password);
		if (u != null && hash.equals(u.getHash())) {
			return;
		}
		throw new Exception("Impossible de se connecter : utilisateur ou mot de passe incorrect");
	}

	public void register(final User user) throws Exception {
		// Règles de gestion :
		// Vérifier que le login n'est pas déjà utilisé
		final User userAvecMemeLogin = getByIdentifiant(user.getIdentifiant());
		if (userAvecMemeLogin != null) {
			throw new Exception("Identifiant déjà utilisé : " + userAvecMemeLogin.getIdentifiant());
		}

		// TODO Vérifier auprès de la fédération que la licence est valide avec la date
		// de naissance saisie

		// Enregistrement de l'utilisateur en base
		user.setHash(hash(user.getPassword()));
		create(user);
	}

	public User getByIdentifiant(final String identifiant) {
		return getDao().getByColumn("identifiant", identifiant);
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

	public Jws<Claims> getClaims(final String token) {
		final Key key = keyGenerator.getKey();
		final Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		claims.getBody().put("token", token);
		final User u = getByIdentifiant(claims.getBody().getSubject());

		claims.getBody().put("prenom", u.getPrenom());
		claims.getBody().put("nom", u.getNom());
		claims.getBody().put("licence", u.getLicence());
		claims.getBody().put("mail", u.getMail());

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

}
