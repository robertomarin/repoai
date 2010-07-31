package ai.liga.ligaai.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import ai.liga.dao.GenericHibernateDAO;
import ai.liga.ligaai.model.LigaAi;

public class LigaAiDao extends GenericHibernateDAO<LigaAi> {

	public LigaAiDao(Class<LigaAi> persistentClass, SessionFactory sessionFactory) {
		super(persistentClass, sessionFactory);
	}

	@Override
	public LigaAi merge(LigaAi ligaAi) {
		Session session = sf.getCurrentSession();

		ligaAi.setCreated(Calendar.getInstance());
		ligaAi = (LigaAi) session.merge(ligaAi);

		return ligaAi;
	}

	@SuppressWarnings("unchecked")
	public List<LigaAi> loadAll() {
		Criteria c = super.getCriteria(true);
		c.setFetchMode("ligaai", FetchMode.JOIN);
		return c.list();
	}

}
