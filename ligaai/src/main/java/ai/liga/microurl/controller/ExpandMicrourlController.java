package ai.liga.microurl.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ai.liga.microurl.model.Microurl;
import ai.liga.microurl.service.MicrourlService;

@Controller
public class ExpandMicrourlController {

	private final MicrourlService microurlService;

	@Autowired
	public ExpandMicrourlController(MicrourlService microurlService) {
		this.microurlService = microurlService;
	}

	@RequestMapping("/*")
	public ModelAndView expand(HttpServletRequest request) {
		String path = request.getServletPath();
		if (!"/".equals(path) && !GenericValidator.isBlankOrNull(path)) {
			Microurl microurl = microurlService.getExpandedUrl(path);

			RedirectView view = new RedirectView(microurl.getUrl(), false, false);
			view.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			return new ModelAndView(view);
		}

		return new ModelAndView("home");
	}
}
