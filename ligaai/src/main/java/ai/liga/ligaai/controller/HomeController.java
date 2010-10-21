package ai.liga.ligaai.controller;

import java.util.Deque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.model.LigaAi;
import ai.liga.ligaai.service.LigaAiService;
import ai.liga.util.Constants;

@Controller
public class HomeController {

	private final LigaAiService ligaAiService;

	@Autowired
	public HomeController(LigaAiService ligaAiService) {
		this.ligaAiService = ligaAiService;
	}

	@RequestMapping("/home")
	public ModelAndView home(@CookieValue(value = Constants.USER, required = false) String userCookie) {

		Deque<LigaAi> ligaais = ligaAiService.getTop();
		return new ModelAndView("home").addObject("ligaais", ligaais);

	}
}
