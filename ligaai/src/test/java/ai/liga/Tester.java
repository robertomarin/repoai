package ai.liga;

import org.junit.Test;

public class Tester {

	public static void main(String[] args) throws Exception {
		new Tester().test();
	}

	@Test
	public void test() throws Exception {
		System.out.println("http://liga.ai/" + BaseConverterUtil.toBase62(1));;
	}

}
