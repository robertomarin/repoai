package ai.liga.ligaai.util;

import static ai.liga.util.Constants.ENCODE;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class EncoderUtils {

	private static final Logger logger = Logger.getLogger(EncoderUtils.class);

	public static String encodeBase64(String decodedString) {
		if (decodedString == null) {
			return null;
		}

		String encodeBase64URLSafeString = Base64.encodeBase64String(decodedString.getBytes());
		return encodeBase64URLSafeString.replaceAll("[^=&\\w]", "");
	}
	
	public static String decodeBase64(String base64String) {
		if (base64String == null) {
			return null;
		}

		try {
			return new String(Base64.decodeBase64(base64String.getBytes()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String encodeUrl(String string) {
		if (string != null) {
			try {
				string = URLEncoder.encode(string, ENCODE);
			} catch (UnsupportedEncodingException e) {
				logger.fatal("Encode não suportado?!", e);
			}
		}
		return string;
	}

	public static String decodeUrl(String string) {
		if (string != null) {
			try {
				string = URLDecoder.decode(string, ENCODE);
			} catch (UnsupportedEncodingException e) {
				logger.fatal("Encode não suportado?!", e);
			}
		}
		return string;
	}

}
