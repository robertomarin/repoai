package ai.liga.avatar.controller;

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

			imageService.makeTransfomations(200, 200, file);
			mav.addObject("msg", "Recebemos seu avatar.");

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

}
