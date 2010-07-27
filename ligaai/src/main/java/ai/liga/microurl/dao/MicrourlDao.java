package ai.liga.microurl.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import ai.liga.dao.GenericHibernateDAO;
import ai.liga.microurl.model.Microurl;

public class MicrourlDao extends GenericHibernateDAO<Microurl> {

	public MicrourlDao(Class<Microurl> persistentClass,
			SessionFactory sessionFactory) {
		super(persistentClass, sessionFactory);
	}

	@Override
	public Microurl merge(Microurl microurl) {
		Session session = sf.getCurrentSession();

		microurl.setCreated(Calendar.getInstance());
		microurl = (Microurl) session.merge(microurl);

		
		return microurl;
	}
	
	@SuppressWarnings("unchecked")
	public List<Microurl> loadAll() {
		Criteria c = super.getCriteria(true);
		c.setFetchMode("microurl", FetchMode.JOIN);
		return c.list();
	}

	
}
