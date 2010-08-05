package ai.liga.ligaai.dao;

import org.hibernate.SessionFactory;

import ai.liga.dao.GenericHibernateDAO;
import ai.liga.ligaai.model.User;

public class UserDao extends GenericHibernateDAO<User> {

	public UserDao(Class<User> persistentClass, SessionFactory sessionFactory) {
		super(persistentClass, sessionFactory);
	}

	public User findByEmail() {
		return null;
	}

}
