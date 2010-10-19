package ai.liga.jstl;

import ai.liga.ligaai.model.Contact;
import ai.liga.ligaai.model.ContactType;

public class Functions {

	public static String getUrlContact(Contact contact) {
		StringBuilder url = new StringBuilder();
		if (contact != null) {
			ContactType type = contact.getType();
			if (type != null) {
				url.append(type.buildUrl(contact.getContent()));
			}
		}

		return url.toString();
	}

	public static boolean isLinkable(ContactType type) {
		if (type != null) {
			return type.isLinkable();
		}
		return false;
	}
}
