package ai.liga.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ai.liga.you.entity.You;

public class YouDAOTest {

	private YouDAO youDAO;

	private final String mail = "you@youmovies.com.br";

	private HibernateDAOFactory hibernateDAOFactory;

	@Before
	public void setUp() {
		hibernateDAOFactory = new HibernateDAOFactory();
		hibernateDAOFactory.beginTransaction();
		youDAO = hibernateDAOFactory.getYouDAO();
	}

	@After
	public void tearDown() {
		hibernateDAOFactory.close();
	}

	@Test
	public void testFindByMail() throws Exception {
		You you = new You();
		you.setName("YouMovies");
		you.setMail(mail);
		youDAO.persist(you);
		you = youDAO.findByMail(mail);
		assertNotNull(you.getMail());

	}

}
