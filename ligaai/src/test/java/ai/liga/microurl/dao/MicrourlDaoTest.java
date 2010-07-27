package ai.liga.microurl.dao;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ai.liga.dao.HibernateUtil;
import ai.liga.microurl.model.Microurl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springapp-servlet.xml" })
public class MicrourlDaoTest {
	
	private Session session;

	@Before
	public void before() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
	}
	
	@Test
	public void loadAllTest() {
		
		Criteria c = session.createCriteria(Microurl.class);
		c.setFetchMode("microurl", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<Microurl> list =  c.list();
		
		for (Microurl microurl : list) {
			System.out.println(microurl.getCreated() + " " + microurl.getUrl());
		}
		
	}
}
