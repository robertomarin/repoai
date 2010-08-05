package ai.liga.ligaai.cookie;

import static ai.liga.util.Constants.ENCODE;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import ai.liga.ligaai.util.EncoderUtils;

public class Cookies {

	private static Logger log = Logger.getLogger(Cookies.class);
	
	

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
			try {
				if (cookieName.equalsIgnoreCase(URLEncoder.encode(cookie.getName(), ENCODE))
						|| cookieName.equalsIgnoreCase(cookie.getName())) {
					return cookie;
				}
			} catch (UnsupportedEncodingException e) {
				log.error("Erro ao efetuar codifica��o no nome do cookie.", e);
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
	 * @param altValue
	 *            valor default a ser retornado, caso o cookie e/ou o par�metro n�o sejam encontrados
	 * @return O valor do par�metro encontrado, ou <b>null</b> se o cookie e/ou o par�metro n�o forem encontrados
	 * @throws UnsupportedEncodingException
	 */
	public static String getItemValue(final Cookie[] cookies, final String cookieName, final String paramName,
			final String altValue) {
		String valor = getItemValue(cookies, cookieName, paramName);
		if (valor == null) {
			return altValue;
		}
		return valor;
	}

	public static String getDecodedItemValue(final Cookie[] cookies, final String cookieName, final String paramName) {
		String valor = getItemValue(cookies, cookieName, paramName);
		if (valor != null) {
			try {
				valor = URLDecoder.decode(valor, ENCODE);
			} catch (UnsupportedEncodingException e) {
				// N�o precisa logar a exception
				log.error("Encode n�o suportado " + ENCODE);
			} catch (IllegalArgumentException e) {
				// N�o precisa logar a exception
				log.error("Erro ao efetuar decode do valor: " + valor);
			}
		}

		if (valor == null) {
			valor = "";
		}
		return valor;
	}

	public static String getDecodedItemValue(final Cookie cookie, final String paramName) {
		String valor = getItemValue(cookie, paramName);
		if (valor != null) {
			try {
				valor = URLDecoder.decode(valor, ENCODE);
			} catch (UnsupportedEncodingException e) {
				// N�o precisa logar a exception
				log.error("Encode n�o suportado " + ENCODE);
			} catch (IllegalArgumentException e) {
				// N�o precisa logar a exception
				log.error("Erro ao efetuar decode do valor: " + valor);
			}
		}

		if (valor == null) {
			valor = "";
		}
		return valor;
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
			log.error("Erro ao tentar dar encode. cookieName=" + cookieName + ",paramName=" + paramName + ". ", e);
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

	public static String getItemValue(final Cookie cookie, final String paramName) {
		if (cookie == null) {
			return null;
		}

		String sValue = cookie.getValue();
		String sParamNameEncoded = paramName;
		try {
			sParamNameEncoded = URLEncoder.encode(paramName, ENCODE);
		} catch (UnsupportedEncodingException e) {
			log.error("Erro ao tentar dar encode. cookieName=" + cookie.getName() + ",paramName=" + paramName + ". ", e);
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

	public static Integer getIntegerItemValue(final Cookie[] cookies, final String cookieName, final String paramName) {
		String value = getItemValue(cookies, cookieName, paramName);
		return !GenericValidator.isBlankOrNull(value) && GenericValidator.isInt(value) ? Integer.parseInt(value) : 0;
	}

	public static Integer getIntegerItemValue(final Cookie cookie, final String paramName) {
		String value = getItemValue(cookie, paramName);
		return !GenericValidator.isBlankOrNull(value) && GenericValidator.isInt(value) ? Integer.parseInt(value) : 0;
	}

	public static Long getLongItemValue(final Cookie cookie, final String paramName) {
		String value = getItemValue(cookie, paramName);
		return !GenericValidator.isBlankOrNull(value) && GenericValidator.isLong(value) ? Long.parseLong(value) : 0;
	}

	/**
	 * Gera um cookie com base nos par�metros.
	 * 
	 * @param name
	 *            nome do cookie. N�o pode ser <b>null</b> nem String vazia.
	 * @param value
	 *            valor do cookie. N�o pode ser <b>null</b>.
	 * @param maxAge
	 *            dura��o do cookie, em segundos (0 p/ deletar o cookie na pr�xima intera��o, n�mero negativo para
	 *            manter o cookie enquanto o browser estiver aberto)
	 * @param domain
	 *            dom�nio onde o cookie ser� v�lido. Se <b>null</b> ser� usado o valor default
	 * @param path
	 *            caminho relativo do cookie. Deve come�ar com "/". Use "/" para dizer que vale para todo o dom�nio. Se
	 *            <b>null</b> ser� usado o valor default
	 * @param res
	 *            Resposta HTTP onde o cookie ser� inserido. Se <b>null</b> o cookie n�o ser� inserido automaticamente
	 * @return ret Um objeto correspondente ao Cookie gerado
	 */
	public static Cookie geraCookie(final String name, final String value, final int maxAge, final String domain,
			String path, final HttpServletResponse res) {
		if (name == null || value == null || name.length() <= 0) {
			return null;
		}
		Cookie cookie = new Cookie(name, value);
		try {
			cookie.setMaxAge(maxAge);
			if (domain != null) {
				cookie.setDomain(domain);
			}
			if (path != null) {
				if (!path.startsWith("/")) {
					path = "/" + path;
				}
				cookie.setPath(path);
			}
			if (res != null) {
				res.addCookie(cookie);
			}
			return cookie;
		} catch (IllegalArgumentException e) {
			String cookInfo = cookie.getName() + "=" + cookie.getValue();
			log.error("Erro ao gravar cookie. cookieInfo: " + cookInfo, e);
			return null;
		}
	}

	public static String updateValueItem(final String param, final String new_value, String values) {

		Pattern pattern = Pattern.compile("(^|&)(" + param + "=)([^&]*)");
		Matcher mt = pattern.matcher(values);

		if (mt.find()) {
			values = mt.replaceAll("$1$2" + new_value);
		}

		return values;
	}

}
