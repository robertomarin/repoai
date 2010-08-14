package ai.liga.microurl.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.spring.web.servlet.view.JsonView;

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

	private static final Pattern REGEX_URL = Pattern.compile("(https?|ftp)://.*");

	private final MicrourlService microurlService;

	@Autowired
	public MicrourlController(MicrourlService microurlService) {
		this.microurlService = microurlService;
	}

	@RequestMapping("/microurl")
	public ModelAndView get(@RequestParam(required = false) String url, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("microurl");
		return microurl(url, mav, request);
	}

	@RequestMapping("/ajax/microurl")
	public ModelAndView getByAjax(@RequestParam(required = false) String url, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new JsonView());
		return microurl(url, mav, request);
	}

	private ModelAndView microurl(String url, ModelAndView mav, HttpServletRequest request) {
		if (GenericValidator.isBlankOrNull(url) || !REGEX_URL.matcher(url).matches()) {

			mav.addObject("msg", 1);
			return mav;
		}

		Microurl microurl = new Microurl(url, request.getRemoteAddr());
		microurl = microurlService.persist(microurl);
		if (microurl == null || GenericValidator.isBlankOrNull(microurl.getMicro())) {
			mav.addObject("msg", 2);
		}

		return mav.addObject("microurl", microurl).addObject("ok", "true");
	}
}
