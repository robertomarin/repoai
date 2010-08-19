package ai.liga.ligaai.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EncoderUtilsTest {

	@Test
	public void testEncodeBase64() {
		String s = "esta é uma string muito legal, &[`]@$)@(#)";
		assertEquals(s, EncoderUtils.decodeBase64(EncoderUtils.encodeBase64(s)));
	}

}
