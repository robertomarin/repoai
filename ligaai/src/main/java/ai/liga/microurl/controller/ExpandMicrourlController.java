package ai.liga.microurl.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ai.liga.microurl.model.Microurl;
import ai.liga.microurl.service.MicrourlService;

@Controller
public class ExpandMicrourlController {
	
	private static final Logger logger = Logger.getLogger(ExpandMicrourlController.class);

	private final MicrourlService microurlService;

	@Autowired
	public ExpandMicrourlController(MicrourlService microurlService) {
		this.microurlService = microurlService;
	}

	@RequestMapping("/expand/{id}")
	public ModelAndView expand(@PathVariable String id) {
		logger.debug("Expandindo url com id: " + id);
		Microurl microurl = microurlService.getExpandedUrl(id);
		RedirectView view = new RedirectView(microurl.getUrl(), false, false);
		view.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		return new ModelAndView(view);
	}
}
