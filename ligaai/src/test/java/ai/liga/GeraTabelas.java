package ai.liga;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springapp-servlet.xml" })
public class GeraTabelas {

	@Test
	public void geraTabelas() {
		AnnotationConfiguration ac = new AnnotationConfiguration();
		ac.configure();
		// new SchemaUpdate(ac).execute(true, true);
		// new SchemaExport(ac).create(true, true);
	}
}