package ai.liga.microurl.util;

import static junit.framework.Assert.*;

import org.junit.Test;

public class BaseConverterTest {

	@Test
	public void testFromBase64() {
		long l = BaseConverter.fromBase62(BaseConverter.toBase62(Integer.MAX_VALUE));
		System.out.println(l);
		System.out.println(BaseConverter.toBase62(l));
		assertEquals(BaseConverter.toBase62(Integer.MAX_VALUE), BaseConverter.toBase62(l));
	}
}
