package ai.liga.avatar.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ai.liga.avatar.service.ImagesTransformationService;

@Controller
public class UploadAvatarController {

	@Autowired
	private ImagesTransformationService imageService;

	private static Logger logger = Logger.getLogger(UploadAvatarController.class);

	@RequestMapping(value = "/uploadAvatar.html", method = RequestMethod.POST)
	public ModelAndView handleFormUpload(@RequestParam("file") MultipartFile file) {

		ModelAndView mav = new ModelAndView("avatar");

		if (!file.isEmpty()) {
			String type = file.getContentType();
			if (!type.matches(".*(/gif)|.*(/jpg)|.*(/png)|.*(/jpeg)")) {
				mav.addObject("msg",
						"Opa não entendemos o formato do arquivo enviado, lembrando que os formatos suportados são: gif, jpg e png.");
				return mav;

			}

			imageService.saveImage(file, 1222);
			mav.addObject("msg", "Legal agora você tem um avatar no Ligaai");
			mav.addObject("idUser", 1222);

			return mav;
		}

		mav.addObject("msg", "Ops não conseguimos receber o arquivo, tente novamente.");
		return mav;

	}

	@RequestMapping(value = "/cropAvatar.html", method = RequestMethod.GET)
	public ModelAndView cropAvatar(@RequestParam("x") int x, @RequestParam("y") int y, @RequestParam("w") int w,
			@RequestParam("h") int h) {
		ModelAndView mav = new ModelAndView("avatar");

		if (imageService.saveImage(1222, x, y, w, h)) {
			mav.addObject("msg", "Legal agora você tem um avatar no Ligaai");
			return mav;
		}

		mav.addObject("msg", "Ops não conseguimos receber o arquivo, tente novamente.");

		return mav;
	}

	@RequestMapping(value = "/uploadAvatar.html", method = RequestMethod.GET)
	public ModelAndView uploadAvatar() {
		return new ModelAndView(new RedirectView("/uploadView.html"));
	}

	@RequestMapping("/uploadView.html")
	public ModelAndView mountViewUp() {
		return new ModelAndView("avatar");
	}

}
