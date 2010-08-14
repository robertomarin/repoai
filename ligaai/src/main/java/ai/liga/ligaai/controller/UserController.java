package ai.liga.ligaai.controller;

import javax.validation.Valid;

import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.model.User;
import ai.liga.ligaai.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setMessageCodesResolver(new DefaultMessageCodesResolver());
	}

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/ajax/user/create")
	public ModelAndView register(@Valid User user, BindingResult result) {
		ModelAndView mav = new ModelAndView(new JsonView());
		if (result.hasErrors()) {
			return mav.addObject("mensagens", result.getFieldErrors());
		}

		return mav.addObject("user", user).addObject("ok", "true");
	}

}
