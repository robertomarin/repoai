package ai.liga.ligaai.cookie;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Component;

import ai.liga.ligaai.model.User;
import ai.liga.ligaai.util.EncoderUtils;
import ai.liga.xstream.XStreamWrapper;

@Component
public class CookieComponent {

	private XStreamWrapper xstream;

	public CookieComponent() {
		xstream = new XStreamWrapper(true, User.class);
	}

	public Cookie createUserCookie(User user) {
		if (user == null) {
			return null;
		}

		String json = xstream.toXML(user);

		Cookie ck = new Cookie("bpv", "");
		ck.setDomain(".liga.ai");
		ck.setMaxAge(60 * 60 * 24 * 365 * 5);
		ck.setPath("/");

		ck.setValue(EncoderUtils.encodeBase64(json));
		return ck;
	}

}
