package fr.pacbad.services.ffbad;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomBooleanDeserializer extends JsonDeserializer<Boolean> {

	final protected Class<?> _valueClass = Boolean.class;

	@Override
	public Boolean deserialize(final JsonParser jp, final DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return _parseBooleanPrimitive2(jp, ctxt);
	}

	protected final boolean _parseBooleanPrimitive2(final JsonParser jp, final DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		final JsonToken t = jp.getCurrentToken();
		if (t == JsonToken.VALUE_TRUE) {
			return true;
		}
		if (t == JsonToken.VALUE_FALSE) {
			return false;
		}
		if (t == JsonToken.VALUE_NULL) {
			return false;
		}
		if (t == JsonToken.VALUE_NUMBER_INT) {
			return (jp.getIntValue() != 0);
		}
		if (t == JsonToken.VALUE_STRING) {
			final String text = jp.getText().trim();

			if ("false".equals(text) || "0".equalsIgnoreCase(text) || "N".equalsIgnoreCase(text)
					|| text.length() == 0) {
				return Boolean.FALSE;
			}

			if ("true".equals(text) || "1".equalsIgnoreCase(text) || "Y".equalsIgnoreCase(text)) {
				return Boolean.TRUE;
			}

			throw ctxt.weirdStringException(text, _valueClass, "only \"true\" or \"false\" recognized");
		}
		// Otherwise, no can do:
		ctxt.handleUnexpectedToken(_valueClass, jp);
		return false;
	}
}