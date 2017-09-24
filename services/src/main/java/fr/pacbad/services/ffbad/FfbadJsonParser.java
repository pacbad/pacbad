package fr.pacbad.services.ffbad;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class FfbadJsonParser {

	public static <T> T parse(final String json, final Class<T> clazz) throws IOException {
		System.out.println(
				"Parse un " + clazz.getSimpleName() + " à partir d'un json de " + json.length() + " caractères");
		final JsonParser jp = new JsonFactory().createParser(json);
		return createObjectMapper().readValue(jp, clazz);
	}

	public static <T> T parse(final InputStream is, final Class<T> clazz) throws IOException {
		// System.out.println("Parse un " + clazz.getSimpleName() + " à partir d'un flux
		// json");
		final JsonParser jp = new JsonFactory().createParser(is);
		return createObjectMapper().readValue(jp, clazz);
	}

	private static ObjectMapper createObjectMapper() {
		final Version version = new Version(1, 0, 0, "SNAPSHOT", "org.ffbad", "ffbad-android");
		SimpleModule module = new SimpleModule("MyModuleName", version);
		final CustomBooleanDeserializer customBooleanDeserializer = new CustomBooleanDeserializer();
		module = module.addDeserializer(Boolean.TYPE, customBooleanDeserializer);
		module = module.addDeserializer(Boolean.class, customBooleanDeserializer);
		module = module.addDeserializer(String.class, new HtmlDecoderDeserializer());
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(module);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		return objectMapper;
	}

}
