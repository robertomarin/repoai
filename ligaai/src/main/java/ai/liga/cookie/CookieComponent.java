package ai.liga.cookie;

import javax.servlet.http.Cookie;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import ai.liga.ligaai.util.EncoderUtils;
import ai.liga.user.model.User;
import ai.liga.util.Constants;
import ai.liga.xstream.XStreamWrapper;

@Component
public class CookieComponent {

	private static final Logger logger = Logger.getLogger(CookieComponent.class);

	private XStreamWrapper xstream;

	public CookieComponent() {
		xstream = new XStreamWrapper(true, User.class);
	}

	public Cookie getCookieFromUser(User user) {
		if (user == null) {
			return null;
		}
		Cookie ck = null;
		try {
			String jsonB64 = EncoderUtils.encodeBase64(xstream.toJSON(user));
			String value = EncoderUtils.encodeUrl(jsonB64) + "|" + DigestUtils.md5Hex(jsonB64);

			ck = new Cookie(Constants.USER, value);
			ck.setDomain(".liga.ai");
			ck.setMaxAge(60 * 60 * 24 * 365 * 5);
			ck.setPath("/");
		} catch (Exception e) {
			logger.error("Erro ao criar cookie do usuario", e);
		}

		return ck;
	}

	
	public User getUserFromCookie(Cookie userCookie) throws InvalidUserException {

		if (userCookie == null || GenericValidator.isBlankOrNull(userCookie.getValue())) {
			return null;
		}

		String decoded = EncoderUtils.decodeUrl(userCookie.getValue());
		
		String[] split = decoded.split("[|]");
		if(GenericValidator.isBlankOrNull(decoded) || split.length != 2) {
			return null;
		}
		
		
		String jsonB64 = split[0];
		String hash = split[1];

		if (GenericValidator.isBlankOrNull(jsonB64) || GenericValidator.isBlankOrNull(hash)) {
			return null;
		}

		if (!hash.equals(DigestUtils.md5Hex(jsonB64))) {
			throw new InvalidUserException("Cookie violado!");
		}

		try {
			return (User) xstream.fromXml(EncoderUtils.decodeBase64(jsonB64));
		} catch (Exception e) {
			return null;
		}
	}
	
	public Cookie createExpiredCookie(String cookieName) {
		Cookie ck = new Cookie(cookieName, "");
		ck.setDomain(".liga.ai");
		ck.setMaxAge(0);
		ck.setPath("/");
		return ck;
		
	}
}
