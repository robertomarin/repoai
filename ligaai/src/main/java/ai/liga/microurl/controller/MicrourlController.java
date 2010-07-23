package ai.liga.microurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.microurl.util.BaseConverter;

@Controller
public class MicrourlController {

	private static int i = 100000;

	@RequestMapping("/microurl")
	public ModelAndView get(@RequestParam(required = false) String url) {

		ModelAndView modelAndView = new ModelAndView("microurl");
		modelAndView.addObject("url", "http://liga.ai/"
				+ BaseConverter.toBase62(i++));
		modelAndView.addObject("urlo", url);
		return modelAndView;
	}
}
