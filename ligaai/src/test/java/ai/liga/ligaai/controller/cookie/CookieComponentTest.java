package ai.liga.ligaai.controller.cookie;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class CookieComponentTest {

	@Test
	public void base64EncodeTest() {

		String s = "esta é uma string muito legal";

		String sEncoded = Base64.encodeBase64String(s.getBytes());
		
		assertEquals(s , new String(Base64.decodeBase64(sEncoded)));

	}
}
