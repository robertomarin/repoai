package ai.liga.avatar.controller;

import java.util.regex.Pattern;

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
import ai.liga.user.service.UserService;
import ai.liga.util.$;

@Controller
public class UploadAvatarController {

	Pattern regex = Pattern.compile(".*(gif|/p?jpe?g|/png)", Pattern.CASE_INSENSITIVE);
	
	private static final int MAX_FILE_SIZE = 10240000;

	private final ImagesTransformationService imageService;

	private final UserService userService;

	@Autowired
	public UploadAvatarController(final ImagesTransformationService imageService, final UserService userService) {
		this.imageService = imageService;
		this.userService = userService;
	}

	private static Logger logger = Logger.getLogger(UploadAvatarController.class);

	@RequestMapping(value = "/uploadAvatar.html", method = RequestMethod.POST)
	public ModelAndView handleFormUpload(@RequestParam("file") MultipartFile file, final HttpServletRequest request) {

		User user = $.getUserFromRequest(request);

		if (user == null) {
			return new ModelAndView(new RedirectView("/"));
		}

		ModelAndView mav = new ModelAndView("/u/conta");

		if (file.getSize() > MAX_FILE_SIZE) {
			mav.addObject("msg", "Tamanho máxio permitido é de 10 megas");
			return mav;
		}

		if (!file.isEmpty()) {
			String type = file.getContentType();
			if (!regex.matcher(type).matches()) {
				mav.addObject("msg",
						"Opa não entendemos o formato do arquivo enviado, lembrando que os formatos suportados são: gif, jpg e png.");
				return mav;
			}

			mav.addObject("result", imageService.saveImage(file, user.getId()));
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

		ModelAndView mav = new ModelAndView("/u/conta");

		if (imageService.cropAndResizeImage(user.getId(), x, y, w, h)) {
			mav.addObject("msg", "Legal agora você tem um avatar no Ligaai");

			userService.giveAvatar(user);
			return mav.addObject("ok", true);
		}

		mav.addObject("msg", "Ops não conseguimos receber o arquivo, tente novamente.");
		return mav;
	}

}
