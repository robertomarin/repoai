package ai.liga.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import ai.liga.dao.HibernateUtil;

public class OpenSessionInViewFilter implements Filter {

	private static final Logger logger = Logger.getLogger(OpenSessionInViewFilter.class);

	private SessionFactory sf;

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		try {
			logger.debug("Starting a database transaction");
			sf.getCurrentSession().beginTransaction();

			request.setAttribute("session", sf.getCurrentSession());
			// Call the next filter (continue request processing)
			chain.doFilter(request, response);

			// Commit and cleanup
			logger.debug("Committing the database transaction");

			sf.getCurrentSession().getTransaction().commit();
			sf.getCurrentSession().close();

		} catch (StaleObjectStateException staleEx) {
			logger.error("This interceptor does not implement optimistic concurrency control!");
			logger.error("Your application will not work until you add compensation actions!");
			// Rollback, close everything, possibly compensate for any permanent changes
			// during the conversation, and finally restart business conversation. Maybe
			// give the user of the application a chance to merge some of his work with
			// fresh data... what you do here depends on your applications design.
			throw staleEx;
		} catch (Throwable ex) {
			// Rollback only
			logger.error(null, ex);
			try {
				if (sf.getCurrentSession().getTransaction().isActive()) {
					logger.debug("Trying to rollback database transaction after exception");
					sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
				logger.error("Could not rollback transaction after exception!", rbEx);
			}

			// Let others handle it... maybe another interceptor for exceptions?
			throw new ServletException(ex);
		}
	}

	public void init(final FilterConfig filterConfig) throws ServletException {
		logger.debug("Initializing OpenSessionInViewFilter...");
		logger.debug("Obtaining SessionFactory from Spring Context");
		sf = HibernateUtil.getSessionFactory();
		if (sf != null) {
			logger.info("OpenSessionInViewFilter configured successfully");
		} else {
			logger.error("Error configuring OpenSessionInViewFilter");
		}
	}

	public void destroy() {
		sf.close();
		logger.info("SessionFactory closed");
	}

}
