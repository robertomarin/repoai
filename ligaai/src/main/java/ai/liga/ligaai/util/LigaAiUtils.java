package ai.liga.ligaai.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

import ai.liga.ligaai.model.LigaAi;
import ai.liga.ligaai.model.Tag;

@Component
public class LigaAiUtils {

	private final Pattern regexTags = Pattern.compile("#([A-Za-z0-9_]+)");

	public void fillTags(LigaAi ligaAi) {
		if (ligaAi == null || GenericValidator.isBlankOrNull(ligaAi.getMessage())) {
			return;
		}

		Matcher matcher = regexTags.matcher(ligaAi.getMessage());
		Set<Tag> tags = new HashSet<Tag>();
		ligaAi.setTags(tags);
		while (matcher.find()) {
			String name = matcher.group(1);
			if (!GenericValidator.isBlankOrNull(name)) {
				tags.add(new Tag(name));
			}
		}

		return;
	}

}
