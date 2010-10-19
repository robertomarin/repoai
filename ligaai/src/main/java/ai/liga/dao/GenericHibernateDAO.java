package ai.liga.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class GenericHibernateDAO<T> {

	protected final SessionFactory sf;

	private final Class<T> persistentClass;

	public GenericHibernateDAO(final Class<T> persistentClass, final SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		sf = sessionFactory;
	}

	public void persist(final T t) {
		sf.getCurrentSession().persist(t);
	}

	@SuppressWarnings("unchecked")
	public T load(final Long id) {
		Session currentSession = sf.getCurrentSession();
		T t = (T) currentSession.load(persistentClass, id);
		return t;
	}

	public void delete(final T t) {
		Session currentSession = sf.getCurrentSession();
		currentSession.delete(t);
	}

	@SuppressWarnings("unchecked")
	public T merge(T t) {
		Session currentSession = sf.getCurrentSession();
		t = (T) currentSession.merge(t);
		return t;
	}

	/**
	 * Retorna um Criteria configurado para cachear ou nao os resultados retornados
	 * 
	 * @param cacheable
	 *            especifica se os resultados retornados por esse Criteria devem ser cacheados (depende de configuracao
	 *            de cache)
	 * @return objeto Criteria baseado na session corrente do Hibernate
	 */
	protected Criteria getCriteria(final boolean cacheable) {
		return sf.getCurrentSession().createCriteria(this.persistentClass).setCacheable(cacheable);
	}

}
