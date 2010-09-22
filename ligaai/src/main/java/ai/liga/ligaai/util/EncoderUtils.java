package ai.liga.ligaai.util;

import static ai.liga.util.Constants.ENCODE;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import sun.misc.BASE64Decoder;

public class EncoderUtils {

	private static final Logger logger = Logger.getLogger(EncoderUtils.class);

	public static String encodeBase64(String decodedString) {
		if (decodedString == null) {
			return null;
		}
		System.out.println(decodedString);

		String encodeBase64URLSafeString = Base64.encodeBase64String(decodedString.getBytes());
		String x = encodeBase64URLSafeString.replaceAll("[^=&\\w]", "");
		
		System.out.println("igual" + x);
		x = encodeUrl(x);
		System.out.println(x);
		return x;
	}
	public static void main(String[] args) {
		System.out.println("eyJ1Ijp7ImlkIjoyNywiZW1haWwiOiJqdWwuZHNhbnRvc0BnbWFpbC5jb20iLCJwYXNzd29yZCI6MTIzNDU2fX0=\n\n\b\r%dfeifj@(*$*@(%)%(*0=23923(@*)*@($*".replaceAll("[^=&\\w]*", ""));
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
