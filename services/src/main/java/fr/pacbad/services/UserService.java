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
		final User u = getByLogin(login);
		final String hash = hash(password);
		if (u != null && hash.equals(u.getHash())) {
			return;
		}
		throw new Exception("Impossible de se connecter : utilisateur ou mot de passe incorrect");
	}

	public User getByLogin(final String login) {
		return getDao().getByColumn("login", login);
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
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
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
		final User u = getByLogin(claims.getBody().getSubject());
		// TODO Renseigner dans le username le vrai nom de la personne, plutôt que son
		// login ?
		claims.getBody().put("username", u.getLogin());
		return claims;
	}

}
