package ai.liga.ligaai.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

import ai.liga.ligaai.model.Tag;

@Component
public class LigaAiUtils {

	private final Pattern regexTags = Pattern.compile("#([A-Za-z0-9_]+)");

	public Set<Tag> extractTags(String message) {
		if (GenericValidator.isBlankOrNull(message)) {
			return null;
		}

		Matcher matcher = regexTags.matcher(message);

		Set<Tag> tags = new HashSet<Tag>();
		while (matcher.find()) {
			String name = matcher.group(1);
			if (GenericValidator.isBlankOrNull(name)) {
				new Tag(name);
			}
		}

		return tags;
	}

}
