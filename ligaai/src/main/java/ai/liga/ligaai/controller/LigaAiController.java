package ai.liga.ligaai.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.spring.web.servlet.view.JsonView;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.model.Contact;
import ai.liga.ligaai.model.ContactType;
import ai.liga.ligaai.model.LigaAi;
import ai.liga.ligaai.service.LigaAiService;
import ai.liga.ligaai.util.LigaAiUtils;
import ai.liga.user.model.User;

@Controller
public class LigaAiController {

	private final LigaAiService ligaAiService;

	private final LigaAiUtils ligaAiUtils;

	@Autowired
	public LigaAiController(LigaAiService ligaAiService, LigaAiUtils ligaAiUtils) {
		this.ligaAiService = ligaAiService;
		this.ligaAiUtils = ligaAiUtils;
	}

	@RequestMapping("/ligaai/criar")
	public ModelAndView post(LigaAi ligaAi) {
		ModelAndView mav = new ModelAndView(new JsonView());
		
		System.out.println(ligaAi.getMessage());
		System.out.println(ligaAi.getContacts().size());
		
		return mav;
	}
	
	@RequestMapping("/ligaai/criar")
	public ModelAndView post(@RequestParam(required = false) String message,
			@RequestParam(required = false) String contact, @RequestParam(required = false) String contactType,
			String email, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new JsonView());

		if (GenericValidator.isBlankOrNull(message)) {
			return mav.addObject("msg", "Mensagem não pode vir em branco.");
		}

		if (GenericValidator.isBlankOrNull(contactType)) {
			return mav.addObject("msg", "Tipo de contato deve ser preenchido.");
		}

		if (GenericValidator.isBlankOrNull(contact)) {
			return mav.addObject("msg", "Contato deve ser preenchido.");
		}

		if (!GenericValidator.isEmail(email)) {
			return mav.addObject("msg", "Email inválido.");
		}

		LigaAi ligaAi = new LigaAi();
		ligaAi.setMessage(message);
		ligaAi.setTags(ligaAiUtils.extractTags(message));
		ligaAi.setRemoteAddress(request.getRemoteAddr());
		ligaAi.setContacts(new ArrayList<Contact>());
		ligaAi.getContacts().add(new Contact(contact, ContactType.valueOf(contactType)));
		ligaAi.setUser(new User(email));

		ligaAi = ligaAiService.merge(ligaAi);
		return mav.addObject("ligaai", ligaAi).addObject("ok", "true");
	}
}
