package ai.liga.microurl.controller;

import static junit.framework.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class MicrourlControllerTest {

	private static final Pattern REGEX_URL = Pattern
			.compile("(https?|ftp)://.*");

	@Test
	public void testRegex() {
		String url = "http://pt.wikipedia.org/wiki/Criança";
		assertTrue(url.matches(REGEX_URL.toString()));
		assertTrue(REGEX_URL.matcher(url).matches());

	}

}
