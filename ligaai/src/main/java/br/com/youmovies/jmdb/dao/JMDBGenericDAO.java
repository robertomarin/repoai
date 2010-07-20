package br.com.youmovies.jmdb.dao;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;

public class JMDBGenericDAO<T> {

	private final SessionFactory factory;

	public JMDBGenericDAO(final SessionFactory factory) {
		this.factory = factory;
	}

	public void persist(final T t) {
		factory.getCurrentSession().setFlushMode(FlushMode.COMMIT);
		factory.getCurrentSession().persist(t);
	}

	@SuppressWarnings("unchecked")
	public T load(final Class<T> persistentClass, final Long id) {
		return (T) factory.getCurrentSession().load(persistentClass, id);
	}

}
