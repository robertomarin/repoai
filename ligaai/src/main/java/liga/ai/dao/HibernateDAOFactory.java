package liga.ai.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;


import ai.liga.filter.OpenSessionInViewFilter;

/**
 * @author marin
 */
//@Repository
public class HibernateDAOFactory {

	private static final Logger logger = Logger.getLogger(HibernateDAOFactory.class);

	private final SessionFactory sessionFactory;

	public HibernateDAOFactory() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public YouDAO getYouDAO() {
		return new YouDAO(sessionFactory);
	}

	public void close() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			throw new IllegalStateException("Current Session n�o pode ser null, " + OpenSessionInViewFilter.class
					+ " falhou!");
		}

		if (currentSession.getTransaction() == null) {
			throw new IllegalStateException("Transaction n�o pode ser null, " + OpenSessionInViewFilter.class
					+ " falhou!");
		}

		currentSession.getTransaction().commit();

	}

	public void beginTransaction() {
		sessionFactory.getCurrentSession().beginTransaction();
	}

}