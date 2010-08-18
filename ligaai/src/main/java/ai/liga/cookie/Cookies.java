package ai.liga.cookie;

import static ai.liga.util.Constants.ENCODE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

import ai.liga.ligaai.util.EncoderUtils;

public class Cookies {

	private static Logger logger = Logger.getLogger(Cookies.class);

	/**
	 * Procura o valor do cookie definido pelo nome <b>cookieName</b>
	 * 
	 * @param cookies
	 *            lista de cookies onde se procurar� o cookie desejado
	 * @param cookieName
	 *            nome do cookie procurado
	 * @return O valor do cookie encontrado, ou <b>null</b> se o cookie n�o for encontrado
	 * @throws UnsupportedEncodingException
	 */
	public static String getCookieValue(final Cookie[] cookies, final String cookieName) {
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equalsIgnoreCase(EncoderUtils.encodeUrl(cookie.getName()))
					|| cookieName.equalsIgnoreCase(cookie.getName())) {
				return (cookie.getValue());
			}
		}
		return null;

	}

	/**
	 * Procura por determinado Cookie, pelo nome
	 * 
	 * @param cookies
	 *            lista de cookies onde se procurar� o cookie desejado
	 * @param cookieName
	 *            nome do cookie procurado
	 * @return O cookie encontrado, ou <b>null</b> se o cookie n�o for encontrado
	 * @throws UnsupportedEncodingException
	 */
	public static Cookie getCookie(final Cookie[] cookies, final String cookieName) {
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equalsIgnoreCase(EncoderUtils.encodeUrl(cookie.getName()))
					|| cookieName.equalsIgnoreCase(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * Procura o valor do par�metro de <b>paramName</b> do cookie definido pelo nome <b>cookieName</b>
	 * 
	 * @param cookies
	 *            lista de cookies onde se procurar� o cookie desejado
	 * @param cookieName
	 *            nome do cookie onse se procura o par�metro
	 * @param paramName
	 *            nome do par�metro procurado
	 * @return O valor do par�metro encontrado, ou <b>null</b> se o cookie e/ou o par�metro n�o forem encontrados
	 * @throws UnsupportedEncodingException
	 */
	public static String getItemValue(final Cookie[] cookies, final String cookieName, final String paramName) {
		if (cookies == null) {
			return null;
		}

		String sValue = getCookieValue(cookies, cookieName);
		String sParamNameEncoded = paramName;
		try {
			sParamNameEncoded = URLEncoder.encode(paramName, ENCODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("Erro ao tentar dar encode. cookieName=" + cookieName + ",paramName=" + paramName + ". ", e);
		}
		if (sValue != null) {
			String sElemen[] = sValue.split("&");
			for (String element : sElemen) {
				if ((element.indexOf(paramName) >= 0) || (element.indexOf(sParamNameEncoded) >= 0)) {
					String sA[] = element.split("=");
					if (sA.length == 2 && (sA[0].equals(paramName) || sA[0].equals(sParamNameEncoded))) {
						return sA[1];
					}
				}
			}
		}

		return null;
	}

	public static String getItemValue(final Cookie cookie, final String param) {
		if (cookie == null) {
			return null;
		}

		String value = EncoderUtils.decodeUrl(cookie.getValue());
		if (value != null) {
			String tokens[] = value.split("[\",&]");
			for (String token : tokens) {
				if (token.startsWith(param)) {
					String sA[] = token.split("=");
					if (sA.length == 2 && (sA[0].equals(param) || sA[0].equals(param))) {
						return sA[1];
					}
				}
			}
		}

		return null;
	}

}
