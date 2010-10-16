package ai.liga.avatar.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springapp-servlet.xml" })
public class UploadAvatarControllerTest {

	@Autowired
	private UploadAvatarController fileUpload;

	@Test
	public void testWrongFile() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("test.txt");

		MockMultipartFile mockMultipartFile = new MockMultipartFile("test",
				"test", "rrr/txt", is);

		ModelAndView upload = fileUpload.handleFormUpload(mockMultipartFile, null);
		Map<String, Object> model = upload.getModel();
		String expected ="Opa não entendemos o formato do arquivo enviado, lembrando que os formatos suportados são: gif, jpg e png.";
		Assert.assertEquals(expected , model.get("msg"));

	}

	@Test
	public void testPNGFile() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("mario.png");

		MockMultipartFile mockMultipartFile = new MockMultipartFile("mario",
				"mario.png", "rrr/png", is);

		ModelAndView upload = fileUpload.handleFormUpload(mockMultipartFile, null);
		Map<String, Object> model = upload.getModel();
		System.out.println(model.get("msg"));

	}

}
