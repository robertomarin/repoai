package ai.liga.ligaai.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;

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

	public List<LigaAi> loadAll() {
		Criteria c = super.getCriteria(true);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		c.setFetchMode("ligaai", FetchMode.JOIN);
		c.addOrder(Order.desc("top"));

		@SuppressWarnings("unchecked")
		List<LigaAi> list = (List<LigaAi>) c.list();
		return list;
	}

}
