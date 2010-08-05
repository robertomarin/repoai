package ai.liga.ligaai.util;

import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

public class EncoderUtilsTest {

	
	private EncoderUtils encoderUtils;

	@Before
	public void before() {
		encoderUtils = new EncoderUtils();
	}
	
	@Test
	public void testEncodeBase64() {
		String s = "esta é uma string muito legal, &[`]@$)@(#)";
		assertEquals(s, encoderUtils.decodeBase64(encoderUtils.encodeBase64(s)));
	}

}
