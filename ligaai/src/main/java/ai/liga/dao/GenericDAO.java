package ai.liga.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author marin
 */
@SuppressWarnings("unchecked")
public class GenericDAO<T> {

	private final Logger logger = Logger.getLogger(this.getClass());

	protected Class<T> persistentClass;

	private final SessionFactory sessionFactory = null;

	public GenericDAO(final Class persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Nao esquecer ao fim do uso de uma session recuperada por esse m�todo de
	 * fecha-la usando o metodo {@link #closeSession()}
	 * 
	 * @return uma Session para uso geral
	 */
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * Carrega o objeto do tipo T com o identificador especificado, assumindo
	 * que o objeto existe
	 * 
	 * @param id
	 * @return objeto carregado
	 */
	public T load(final Long id) {
		return (T) getSession().load(this.persistentClass, id);
	}

	/**
	 * Grava o novo objeto t enviado como parametro
	 * 
	 * @param t
	 *            objeto a ser gravado
	 */
	public void save(final T t) {
		this.sessionFactory.getCurrentSession().persist(t);
	}

	/**
	 * Atualiza o objeto t enviado como parametro
	 * 
	 * @param t
	 *            objeto a ser atualizado
	 */
	protected void update(final T t) {
		this.sessionFactory.getCurrentSession().update(t);
	}

	/**
	 * Grava ou atualiza o objeto t enviado como parametro
	 * 
	 * @param t
	 *            objeto a ser gravado ou atualizado
	 */
	protected void saveOrUpdate(final T t) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	protected void merge(final T t) {
		this.sessionFactory.getCurrentSession().merge(t);
	}

	/**
	 * Remove o objeto t enviado como parametro
	 * 
	 * @param t
	 *            objeto a ser removido
	 */
	protected void delete(final T t) {
		logger.info("Apagando " + t.getClass());
		this.sessionFactory.getCurrentSession().delete(t);
	}

	/**
	 * Remove um objeto de mesmo tipo da classe usada como parametro no
	 * construtor da classe com o id do parametro enviado
	 * 
	 * @param id
	 *            identificador �nico do objeto a ser removido
	 */
	protected void delete(final Serializable id) {
		logger.info("Apagando " + persistentClass + " com id " + id);
		final Object apagar = this.sessionFactory.getCurrentSession().load(
				persistentClass, id);
		this.sessionFactory.getCurrentSession().delete(apagar);
	}

	/**
	 * Carrega um objeto existente do tipo T buscado pelo id do parametro
	 * 
	 * @param id
	 *            identificador �nico do objeto a ser carregado
	 * @return instancia do tipo buscado pelo id indicado
	 */
	protected T get(final Serializable id) {
		logger.info("Carregando " + persistentClass + " com id " + id);
		final T retorno = (T) this.sessionFactory.getCurrentSession().get(
				persistentClass, id);
		return retorno;
	}

	/**
	 * Retorna um Criteria configurado para cachear ou nao os resultados
	 * retornados
	 * 
	 * @param cacheable
	 *            especifica se os resultados retornados por esse Criteria devem
	 *            ser cacheados (depende de configuracao de cache)
	 * @return objeto Criteria baseado na session corrente do Hibernate
	 */
	protected Criteria getCriteria(final boolean cacheable) {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(this.persistentClass).setCacheable(cacheable);
	}
}