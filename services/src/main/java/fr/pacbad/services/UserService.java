package fr.pacbad.services;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import fr.pacbad.auth.KeyGenerator;
import fr.pacbad.dao.SimpleDao;
import fr.pacbad.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserService extends SimpleService<User> {

	@Inject
	private KeyGenerator keyGenerator;

	@Override
	protected SimpleDao<User> createDao() {
		return new SimpleDao<User>() {
		};
	}

	public void authenticate(String login, String password) throws Exception {
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
		// TODO hash
		return password;
	}

	public String issueToken(String login) {
		final Key key = keyGenerator.getKey();
		final Calendar cal = Calendar.getInstance();
		// Le jeton sera valide pendant une heure
		cal.add(Calendar.HOUR, 1);
		final Date expirationDate = cal.getTime();
		final String jwtToken = Jwts.builder().setSubject(login).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return jwtToken;
	}

}
