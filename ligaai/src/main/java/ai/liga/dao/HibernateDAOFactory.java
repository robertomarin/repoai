package ai.liga.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import ai.liga.ligaai.dao.LigaAiDao;
import ai.liga.ligaai.model.LigaAi;
import ai.liga.microurl.dao.MicrourlDao;
import ai.liga.microurl.model.Microurl;
import ai.liga.user.dao.UserDao;
import ai.liga.user.model.User;

/**
 * @author marin
 */
@Component
public class HibernateDAOFactory {

	private static final Logger logger = Logger.getLogger(HibernateDAOFactory.class);

	private final SessionFactory sessionFactory;

	public HibernateDAOFactory() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void close() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			throw new IllegalStateException("Current Session não�o pode ser null, falhou!");
		}

		if (currentSession.getTransaction() == null) {
			throw new IllegalStateException("Transaction não pode ser null, falhou!");
		}

		currentSession.getTransaction().commit();

	}

	public void beginTransaction() {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	public MicrourlDao getMicrourlDao() {
		return new MicrourlDao(Microurl.class, sessionFactory);
	}

	public LigaAiDao getLigaAiDao() {
		return new LigaAiDao(LigaAi.class, sessionFactory);
	}

	public UserDao getUserDao() {
		return new UserDao(User.class, sessionFactory);
	}

}