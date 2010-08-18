package ai.liga.xstream;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.Calendar;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;

import ai.liga.user.model.User;

public class XStreamWrapperTest {

	@Test
	public void testaWrapper() throws Exception {

		final XStreamWrapper streamWrapper = new XStreamWrapper(Simples.class);
		final Object fromDom = streamWrapper.fromDomDocument(streamWrapper.toDomDocument(new Simples()));
		final Object fromXml = streamWrapper.fromXml(streamWrapper.toXML(new Simples()));

		System.out.println(streamWrapper.toXML(new Simples()));

		final Document document = streamWrapper.toDomDocument(new Simples());

		final StringWriter writer = new StringWriter();
		final Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
		newTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		newTransformer.transform(new DOMSource(document), new StreamResult(writer));

		System.out.println(writer);

		assertTrue(fromDom.equals(fromXml));

	}

	@Test
	public void testUser() throws Exception {
		final XStreamWrapper x = new XStreamWrapper(User.class);
		User u = new User();
		u.setCreated(Calendar.getInstance());
		u.setId(12345l);
		u.setEmail("myemail@email.com");

		String xml = x.toXML(u);
		assertEquals(xml, x.toXML(x.fromXml(xml)));
	}

}
