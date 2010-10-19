package ai.liga.ligaai.util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ai.liga.ligaai.model.LigaAi;
import ai.liga.ligaai.model.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springapp-servlet.xml" })
public class LigaAiUtilsTest {

	@Autowired
	private LigaAiUtils ligaAiUtils;

	private String message = "Essa #mensagem é muito importante para transmitir a verdade e a mentira de #tudo #CaseInsensitive";

	@Test
	public void extractTagsFrom() {
		LigaAi ligaAi = new LigaAi();
		ligaAi.setMessage(message);
		ligaAiUtils.fillTags(ligaAi);

		Assert.assertTrue(ligaAi.getTags().contains(new Tag("mensagem")));
		Assert.assertTrue(ligaAi.getTags().contains(new Tag("tudo")));
		Assert.assertTrue(ligaAi.getTags().contains(new Tag("caseinsensitive")));
	}

}