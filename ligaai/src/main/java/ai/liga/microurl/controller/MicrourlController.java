package ai.liga.microurl.controller;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.microurl.model.Microurl;
import ai.liga.microurl.service.MicrourlService;

@Controller
public class MicrourlController {

	private final MicrourlService microurlService;

	@Autowired
	public MicrourlController(MicrourlService microurlService) {
		this.microurlService = microurlService;
	}

	@RequestMapping("/microurl")
	public ModelAndView get(@RequestParam(required = false) String url) {
		ModelAndView mav = new ModelAndView("microurl");

		if (GenericValidator.isBlankOrNull(url)) {
			mav.addObject("msg", 1);
			return mav;
		}

		Microurl microurl = microurlService.getMicrourl(url);
		if (microurl == null) {
			mav.addObject("msg", 2);
		}

		mav.addObject("microurl", microurl);
		mav.addObject("urlo", url);
		return mav;
	}
}
