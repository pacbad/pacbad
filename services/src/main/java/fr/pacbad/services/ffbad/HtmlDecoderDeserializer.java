package fr.pacbad.services.ffbad;

import java.io.IOException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class HtmlDecoderDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
		final String text = jp.getText().trim();
		final String escapedText = StringEscapeUtils.unescapeHtml4(text);
		if (escapedText == null) {
			return text;
		}
		return escapedText;
	}

}