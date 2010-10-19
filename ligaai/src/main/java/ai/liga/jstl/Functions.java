package ai.liga.jstl;

import ai.liga.ligaai.model.Contact;
import ai.liga.ligaai.model.ContactType;

public class Functions {

	public String getUrlContact(Contact contact) {
		StringBuilder url = new StringBuilder();
		if (contact != null) {
			ContactType type = contact.getType();
			if(ContactType.SKYPE.equals(type)) {
				url.append("skype:").append(contact.getContent());
			}
			

		}
		
		return url.toString();
	}
}
