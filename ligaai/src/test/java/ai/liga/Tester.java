package ai.liga;

import org.junit.Test;

import ai.liga.microurl.util.BaseConverter;

public class Tester {

	public static void main(String[] args) throws Exception {
		new Tester().test();
	}

	@Test
	public void test() throws Exception {
		System.out.println("http://liga.ai/" + BaseConverter.toBase62(23423423));;
	}

}
