package fr.pacbad.services;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.json.simple.JSONObject;

import fr.pacbad.entities.ffbad.WSJoueurDetail;
import fr.pacbad.filter.CorsFilter;
import fr.pacbad.logger.PacbadLogger;
import fr.pacbad.services.ffbad.FfbadJsonParser;

public class PoonaService {

	@Inject
	private ParametreService parametre;

	private static final String URL = "https://ws.ffbad.com/";
	private static final String CERTIFICATE_NAME = "/Gandi Standard SSL CA 2.crt";

	private static final PacbadLogger LOGGER = PacbadLogger.getLogger(CorsFilter.class);

	public WSJoueurDetail getByLicence(final String licence) throws IOException {
		final Map<String, Object> params = new LinkedHashMap<String, Object>();
		final NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		numberFormat.setMinimumIntegerDigits(8);
		numberFormat.setGroupingUsed(false);
		params.put("Licence", numberFormat.format(Long.parseLong(licence)));
		final WSJoueurDetail result = call("ws_getlicenceinfobylicence", params, WSJoueurDetail.class);
		return result;
	}

	protected <T> T call(final String functionName, final Map<String, Object> params, final Class<T> typeAttendu)
			throws IOException {

		String url = URL;

		final QueryParam query = new QueryParam(params);
		query.function = functionName;

		final AuthParam auth = new AuthParam();
		auth.login = parametre.getString(ParametreService.KEY_POONA_LOGIN);
		auth.password = parametre.getString(ParametreService.KEY_POONA_PASSWORD);

		// url += "rest/?query=" + query.toString();
		// url += "&auth=" + auth.toString();

		url += "rest/?QueryJson=" + URLEncoder.encode(query.toString(), "ISO-8859-1");
		url += "&AuthJson=" + URLEncoder.encode(auth.toString(), "ISO-8859-1");

		// System.out.println(url);
		LOGGER.debug(url);

		HttpsURLConnection urlConnection = null;
		InputStream caInput = null;
		try {
			// Load CAs from an InputStream
			// (could be from a resource or ByteArrayInputStream or ...)
			final CertificateFactory cf = CertificateFactory.getInstance("X.509");
			final InputStream certificate = PoonaService.class.getResourceAsStream(CERTIFICATE_NAME);
			caInput = new BufferedInputStream(certificate);
			final Certificate ca = cf.generateCertificate(caInput);

			// Create a KeyStore containing our trusted CAs
			final String keyStoreType = KeyStore.getDefaultType();
			final KeyStore keyStore = KeyStore.getInstance(keyStoreType);
			keyStore.load(null, null);
			keyStore.setCertificateEntry("ca", ca);

			// Create a TrustManager that trusts the CAs in our KeyStore
			final String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			final TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
			tmf.init(keyStore);

			// Create an SSLContext that uses our TrustManager
			final SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, tmf.getTrustManagers(), null);

			urlConnection = (HttpsURLConnection) new URL(url).openConnection();
			urlConnection.setSSLSocketFactory(context.getSocketFactory());
			final InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			final T result = FfbadJsonParser.parse(in, typeAttendu);
			return result;
		} catch (final CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			throw new IOException(e);
		} finally {
			if (caInput != null) {
				caInput.close();
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
	}

	private static class AuthParam {
		private String login;
		private String password;

		@SuppressWarnings("unchecked")
		@Override
		public String toString() {
			final JSONObject json = new JSONObject();
			json.put("Login", login);
			json.put("Password", password);
			return json.toString();
		}
	}

	private static class QueryParam {
		private final Map<String, Object> params;
		private String function;

		public QueryParam(final Map<String, Object> params) {
			this.params = params;
		}

		@Override
		public String toString() {
			final StringBuilder str = new StringBuilder();
			str.append("{\"Param\":{");
			boolean first = true;
			for (final Map.Entry<String, Object> entry : params.entrySet()) {
				if (first) {
					first = false;
				} else {
					str.append(',');
				}
				str.append('"').append(entry.getKey()).append("\":\"").append(entry.getValue()).append('"');
			}
			str.append("},\"Function\":\"").append(function).append("\"}");
			return str.toString();
		}

		// @SuppressWarnings("unchecked")
		// private Object getJsonValue(final Object value) {
		// if (value instanceof String[]) {
		// final JSONArray jsonArray = new JSONArray();
		// for (final String s : (String[]) value) {
		// jsonArray.add(s);
		// }
		// return jsonArray;
		// }
		// return value.toString();
		// }
	}
}
