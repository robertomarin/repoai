package ai.liga.avatar.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.avatar.service.ImagesTransformationService;

@Controller
public class UploadAvatarController {

	@Autowired
	private ImagesTransformationService imageService;
	
	private static Logger logger = Logger
	.getLogger(UploadAvatarController.class);

	@RequestMapping(value = "/uploadAvatar.html", method = RequestMethod.POST)
	public ModelAndView handleFormUpload(
			@RequestParam("file") MultipartFile file) {

		ModelAndView mav = new ModelAndView("avatar");

		if (!file.isEmpty()) {
			String type = file.getContentType();
			if (!type.matches(".*(/img)|.*(/jpg)|.*(/png)")) {
				mav.addObject(
						"msg",
						"Opa não entendemos o formato do arquivo enviado, lembrando que os formatos suportados são: gif, jpg e png.");
				return mav;

			}

			saveImage(imageService.makeTransfomations(200, 200, file));
			mav.addObject("msg", "Legal agora você tem um avatar no Ligaai");

			return mav;
		} else {
			mav.addObject("msg",
					"Ops não conseguimos receber o arquivo, tente novamente.");

			return mav;
		}
	}

	@RequestMapping("/uploadView.html")
	public ModelAndView mountViewUp() {
		return new ModelAndView("avatar");
	}

	private void saveImage(BufferedImage image) {
		// TODO gravar em algum lugar do disco isto dak
		/** var/www/html/ligaai/img-avatar */
		try {
			ImageIO.write(image, "jpg", new File("/teste.jpg"));
		} catch (IOException e) {
			//TODO Alterar aqui com as informacoes do usuario
			logger.error("Erro ao gravar a imagem do user: "+1234, e);
		}

	}

}
