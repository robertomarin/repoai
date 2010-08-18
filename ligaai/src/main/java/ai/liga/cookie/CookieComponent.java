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
			String jsonB64 = EncoderUtils.encodeBase64(xstream.toXML(user));
			String value = Constants.USER + "=" + jsonB64;
			value += "&" + Constants.HASH + "=" + DigestUtils.md5Hex(jsonB64);

			ck = new Cookie(Constants.USER, value);
			ck.setDomain(".liga.ai");
			ck.setMaxAge(60 * 60 * 24 * 365 * 5);
			ck.setPath("/");

		} catch (Exception e) {
			logger.error("Erro ao criar cookie do usuario", e);
		}

		return ck;
	}

	public User getUserFromCookie(Cookie userCookie) {

		if (userCookie == null || GenericValidator.isBlankOrNull(userCookie.getValue())) {
			return null;
		}

		String jsonB64 = Cookies.getItemValue(userCookie, Constants.USER);
		String hash = Cookies.getItemValue(userCookie, Constants.HASH);

		if (GenericValidator.isBlankOrNull(jsonB64) || GenericValidator.isBlankOrNull(hash)) {
			return null;
		}

		if (!hash.equals(DigestUtils.md5Hex(jsonB64))) {
			throw new IllegalStateException("Cookie violado!");
		}

		try {
			return (User) xstream.fromXml(EncoderUtils.decodeBase64(jsonB64));
		} catch (Exception e) {
			return null;
		}
	}
}
