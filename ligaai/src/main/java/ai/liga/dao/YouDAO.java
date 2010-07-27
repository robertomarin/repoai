package ai.liga.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import ai.liga.you.entity.You;

public class YouDAO extends GenericHibernateDAO<You> {

	private static final Class<You> persistentClass = You.class;

	public YouDAO(final SessionFactory sessionFactory) {
		super(persistentClass, sessionFactory);
	}

	public You findByMail(final String mail) {
		Criteria c = sf.getCurrentSession().createCriteria(persistentClass);
		c.add(Restrictions.eq("mail", mail));
		return persistentClass.cast(c.uniqueResult());
	}

}
