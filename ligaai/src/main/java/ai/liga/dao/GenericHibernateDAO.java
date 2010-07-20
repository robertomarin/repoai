package ai.liga.dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class GenericHibernateDAO<T> {

	protected final SessionFactory factory;

	private final Class<T> persistentClass;

	public GenericHibernateDAO(final Class<T> persistentClass, final SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		factory = sessionFactory;
	}

	public void persist(final T t) {
		Session currentSession = factory.getCurrentSession();
		factory.getCurrentSession().persist(t);
	}

	@SuppressWarnings("unchecked")
	public T load(final Long id) {
		Session currentSession = factory.getCurrentSession();
		T t = (T) currentSession.load(persistentClass, id);
		return t;
	}

	public void delete(final T t) {
		Session currentSession = factory.getCurrentSession();
		currentSession.delete(t);
	}

	@SuppressWarnings("unchecked")
	public T merge(T t) {
		Session currentSession = factory.getCurrentSession();
		t = (T) currentSession.merge(t);
		return t;
	}

}
