package ai.liga.microurl.util;

import static junit.framework.Assert.*;

import org.junit.Test;

public class BaseConverterTest {

	@Test
	public void testFromBase64() {
		int i = BaseConverter.fromBase62(BaseConverter.toBase62(Integer.MAX_VALUE));
		System.out.println(i);
		System.out.println(BaseConverter.toBase62(i));
		assertEquals(BaseConverter.toBase62(Integer.MAX_VALUE), BaseConverter.toBase62(i));
	}
}
