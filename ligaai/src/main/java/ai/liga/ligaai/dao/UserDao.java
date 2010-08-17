package ai.liga.ligaai.dao;

import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import ai.liga.dao.GenericHibernateDAO;
import ai.liga.ligaai.model.User;

public class UserDao extends GenericHibernateDAO<User> {

	public UserDao(Class<User> persistentClass, SessionFactory sessionFactory) {
		super(persistentClass, sessionFactory);
	}

	public User findByEmail(User user) {
		if (user == null) {
			return null;
		}
		return findByEmail(user.getEmail());
	}

	public User findByEmail(String email) {
		if (GenericValidator.isBlankOrNull(email)) {
			return null;
		}

		Criteria c = super.getCriteria(true);
		c.add(Restrictions.eq("email", email));

		@SuppressWarnings("unchecked")
		List<User> list = c.list();

		return !list.isEmpty() ? list.get(0) : null;
	}
}
