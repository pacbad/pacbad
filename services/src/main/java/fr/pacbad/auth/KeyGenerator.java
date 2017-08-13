package fr.pacbad.auth;

import java.security.Key;

import io.jsonwebtoken.impl.crypto.MacProvider;

public class KeyGenerator {

	private static Key key = MacProvider.generateKey();
	
	public Key getKey() {
		return key;
	}
	
}
