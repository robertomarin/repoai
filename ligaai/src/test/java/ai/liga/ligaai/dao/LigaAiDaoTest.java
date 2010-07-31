package ai.liga.ligaai.dao;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ai.liga.dao.HibernateDAOFactory;
import ai.liga.ligaai.model.Contact;
import ai.liga.ligaai.model.ContactType;
import ai.liga.ligaai.model.LigaAi;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springapp-servlet.xml" })
public class LigaAiDaoTest {

	@Autowired
	private HibernateDAOFactory factory;

	private LigaAiDao ligaAiDao;

	@Before
	public void before() {
		ligaAiDao = factory.getLigaAiDao();
		factory.getSessionFactory().getCurrentSession().beginTransaction();
	}

	@After
	public void after() {
		factory.getSessionFactory().getCurrentSession().getTransaction().commit();
		factory.getSessionFactory().getCurrentSession().close();
	}

	@Test
	public void mergeTest() {
		LigaAi ligaAi = new LigaAi();

		ligaAi.setMessage("sample message");
		ligaAi.setContacts(new ArrayList<Contact>());

		Contact contact = new Contact();
		contact.setContent("11-82047957");
		contact.setType(ContactType.PHONE);
		ligaAi.getContacts().add(contact);

		ligaAiDao.merge(ligaAi);
	}
}
