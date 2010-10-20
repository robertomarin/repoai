package ai.liga.ligaai.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.model.LigaAi;
import ai.liga.ligaai.service.LigaAiService;
import ai.liga.ligaai.util.LigaAiUtils;
import ai.liga.user.model.User;
import ai.liga.util.$;

@Controller
public class LigaAiController {

	private final LigaAiService ligaAiService;

	private final LigaAiUtils ligaAiUtils;

	@Autowired
	public LigaAiController(LigaAiService ligaAiService, LigaAiUtils ligaAiUtils) {
		this.ligaAiService = ligaAiService;
		this.ligaAiUtils = ligaAiUtils;
	}

	@RequestMapping("/l/")
	public String view() {
		return "ligaai";
	}

	@RequestMapping("/l/novo")
	public ModelAndView criar(@Valid LigaAi ligaAi, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new JsonView());
		ligaAi.setUser($.getUserFromRequest(request));
		ligaAi.setRemoteAddress(request.getRemoteAddr());
		ligaAiUtils.fillTags(ligaAi);
		ligaAi = ligaAiService.merge(ligaAi);
		return mav.addObject("ligaai", ligaAi).addObject("ok", "true");
	}

	@RequestMapping("/l/topo/{id}")
	public ModelAndView topo(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new JsonView());
		User user = $.getUserFromRequest(request);
		return mav.addObject("ok", user != null && ligaAiService.topo(id));
	}

}
