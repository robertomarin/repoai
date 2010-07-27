package ai.liga.microurl.service;

import static junit.framework.Assert.assertTrue;

import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MicrourlServiceTest {

	@DataPoint
	public static String http = "http://liga.ai/";

	@DataPoint
	public static String https = "https://liga.ai/";

	@DataPoint
	public static String ftp = "ftp://liga.ai/";

	public final Pattern regexProtocol = Pattern.compile("^(https?|ftp)://");
	
	@Theory
	public void test(String url) throws Exception {
		assertTrue(regexProtocol.matcher(url).find());
		assertTrue(GenericValidator.isUrl(url));
	}
}
