package ai.liga.ligaai.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.model.Contact;
import ai.liga.ligaai.model.ContactType;
import ai.liga.ligaai.model.LigaAi;
import ai.liga.ligaai.service.LigaAiService;

@Controller
public class LigaAiController {

	private final LigaAiService ligaAiService;

	@Autowired
	public LigaAiController(LigaAiService ligaAiService) {
		this.ligaAiService = ligaAiService;
	}

	@RequestMapping("/ajax/ligaai")
	public ModelAndView post(@RequestParam String message, @RequestParam String contact,
			@RequestParam String contactType, HttpServletRequest request) {

		LigaAi ligaAi = new LigaAi();
		ligaAi.setMessage(message);
		ligaAi.setRemoteAddress(request.getRemoteAddr());
		ligaAi.setContacts(new ArrayList<Contact>());
		ligaAi.getContacts().add(new Contact(contact, ContactType.valueOf(contactType)));

		ligaAi = ligaAiService.merge(ligaAi);
		return new ModelAndView(new JsonView()).addObject("ligaai", ligaAi);
	}
}
