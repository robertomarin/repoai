package ai.liga.ligaai.model;

import org.apache.commons.validator.GenericValidator;

public enum ContactType {
	PHONE(null),
	SKYPE("skype:"),
	GTALK("mailto:"),
	MSN("msnim:add?contact="),
	TWITTER("http://www.twitter.com/"),
	FACEBOOK("skype:"),
	ORKUT("skype:"),
	EMAIL("mailto:"),
	SITE("http://");

	private final String urlPrefix;

	private ContactType(String prefix) {
		this.urlPrefix = prefix;
	}

	public String buildUrl(String content) {
		if (!GenericValidator.isBlankOrNull(urlPrefix)) {
			return urlPrefix + content;
		}
		return content;
	}

	public boolean isLinkable() {
		return urlPrefix != null;
	}
}
