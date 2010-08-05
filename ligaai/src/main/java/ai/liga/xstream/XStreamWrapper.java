package ai.liga.xstream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomReader;
import com.thoughtworks.xstream.io.xml.DomWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;

public class XStreamWrapper {

	private static final XmlFriendlyReplacer xmlFriendlyReplacer = new XmlFriendlyReplacer("S", "_");

	private final XStream stream;

	private XStreamWrapper() {
		this(false);
	}

	private XStreamWrapper(final boolean json) {
		if (json) {
			stream = new XStream(new JettisonMappedXmlDriver());
		} else {
			stream = new XStream(new XppDomDriver(xmlFriendlyReplacer));
		}

		stream.setMode(XStream.NO_REFERENCES);
	}

	public XStreamWrapper(final Class<?>... classes) {
		this();
		stream.processAnnotations(classes);
	}

	public XStreamWrapper(final boolean json, final Class<?>... classes) {
		this(json);
		stream.processAnnotations(classes);
	}

	public XStream getXStream() {
		return stream;
	}

	public String toXML(final Object object) {
		return stream.toXML(object);
	}

	public String toJSON(final Object object) {
		return this.toXML(object);
	}

	public Document toDomDocument(final Object object) {

		if (object == null) {
			throw new IllegalArgumentException("O argumento object não pode ser null!");
		}

		Document document = null;

		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (final ParserConfigurationException e) {
			throw new IllegalStateException("Erro ao criar documento DOM.", e);
		}

		stream.marshal(object, new DomWriter(document, xmlFriendlyReplacer));
		return document;
	}

	public Object fromDomDocument(final Document document) {

		if (document == null) {
			throw new IllegalArgumentException("O argumento object não pode ser null!");
		}

		return stream.unmarshal(new DomReader(document));
	}

	public Object fromXml(final String xml) {
		return stream.fromXML(xml);
	}

}
