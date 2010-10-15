package ai.liga.avatar.controller;

import javax.servlet.http.HttpServletRequest;

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
import ai.liga.user.model.User;
import ai.liga.util.$;

@Controller
public class UploadAvatarController {

	@Autowired
	private ImagesTransformationService imageService;

	private static Logger logger = Logger.getLogger(UploadAvatarController.class);

	@RequestMapping(value = "/uploadAvatar.html", method = RequestMethod.POST)
	public ModelAndView handleFormUpload(@RequestParam("file") MultipartFile file,  final HttpServletRequest request) {
		
		User user = $.getUserFromRequest(request);

		if (user == null) {
			return new ModelAndView(new RedirectView("/"));
		}

		ModelAndView mav = new ModelAndView("avatar");

		if (!file.isEmpty()) {
			String type = file.getContentType();
			if (!type.matches(".*(/gif)|.*(/jpg)|.*(/png)|.*(/jpeg)")) {
				mav.addObject("msg",
						"Opa não entendemos o formato do arquivo enviado, lembrando que os formatos suportados são: gif, jpg e png.");
				return mav;

			}

			imageService.saveImage(file, user.getId());
			mav.addObject("msg", "Legal agora você tem um avatar no Ligaai");
			mav.addObject("user", user);

			return mav;
		}

		mav.addObject("msg", "Ops não conseguimos receber o arquivo, tente novamente.");
		return mav;

	}

	@RequestMapping(value = "/cropAvatar.html", method = RequestMethod.GET)
	public ModelAndView cropAvatar(@RequestParam("x") int x, @RequestParam("y") int y, @RequestParam("w") int w,
			@RequestParam("h") int h, final HttpServletRequest request) {

		User user = $.getUserFromRequest(request);

		if (user == null) {
			return new ModelAndView(new RedirectView("/"));
		}

		ModelAndView mav = new ModelAndView("avatar");

		if (imageService.saveImage(user.getId(), x, y, w, h)) {
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
	public ModelAndView mountViewUpload(final HttpServletRequest request) {

		User user = $.getUserFromRequest(request);

		if (user == null) {
			return new ModelAndView(new RedirectView("/"));
		}

		return new ModelAndView("avatar").addObject("user", user);
	}

}
