package ai.liga;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

import ai.liga.microurl.util.BaseConverter;

public class Tester {


	@Test
	public void test() throws Exception {
		Deque<String> deque = new ArrayDeque<String>();
		char c = 'a';
		for (int i = 0; i < 26; i++) {
			deque.add("" + ((char) (c + i)));
		}
		
		deque.remove("o");
		deque.addFirst("o");
		for (String string : deque) {
			System.out.println(string);
		}
		
	}

}
