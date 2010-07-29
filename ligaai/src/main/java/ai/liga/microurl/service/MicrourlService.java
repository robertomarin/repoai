package ai.liga.microurl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.liga.dao.HibernateDAOFactory;
import ai.liga.microurl.dao.MicrourlDao;
import ai.liga.microurl.model.Microurl;
import ai.liga.microurl.util.BaseConverter;

@Service
public class MicrourlService {

	private Map<String, Microurl> cache = new HashMap<String, Microurl>();

	private MicrourlDao microurlDao;

	public final Pattern regexProtocol = Pattern.compile("^(https?|ftp)://");

	@Autowired
	public MicrourlService(HibernateDAOFactory hibernateDAOFactory) {
		microurlDao = hibernateDAOFactory.getMicrourlDao();
	}

	public Microurl getMicrourl(String url) {
		cache();

		Microurl microurl = cache.get(url);
		if (microurl != null) {
			return microurl;
		}

		microurl = save(url);
		put(microurl);

		return microurl;
	}

	private void cache() {
		if (cache.isEmpty()) {
			List<Microurl> all = microurlDao.loadAll();
			for (Microurl microurl : all) {
				put(microurl);
			}
		}
	}

	private void put(Microurl microurl) {
		if (isValid(microurl)) {
			cache.put(microurl.getUrl(), microurl);
		}
	}

	private boolean isValid(Microurl microurl) {
		return microurl != null && !GenericValidator.isBlankOrNull(microurl.getUrl());
	}

	private Microurl save(String url) {
		if (!isUrl(url)) {
			return null;
		}

		Microurl microurl = new Microurl();
		microurl.setUrl(url);
		microurl = microurlDao.merge(microurl);
		return microurl;
	}

	private boolean isUrl(String url) {
		return regexProtocol.matcher(url).find() && GenericValidator.isUrl(url);
	}

	public Microurl getExpandedUrl(String path) {
		path = path.replace("/", "");
		Microurl microurl = cache.get(path);
		if (microurl == null) {
			int id = BaseConverter.fromBase62(path);
			if (id > 0) {
				microurl = microurlDao.load(id);
				put(microurl);
			}
		}

		return microurl;
	}

}
