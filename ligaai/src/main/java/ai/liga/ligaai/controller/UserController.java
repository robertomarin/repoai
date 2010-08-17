package ai.liga.ligaai.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.model.User;
import ai.liga.ligaai.service.UserService;
import ai.liga.util.Constants;

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

	@RequestMapping("/user.html")
	public String view(HttpServletRequest request) {
		User user = (User) request.getAttribute(Constants.USER);
		System.out.println(user);
		return "user";
	}

	@RequestMapping("/ajax/user/create")
	public ModelAndView register(@Valid User user, BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new JsonView());
		if (result.hasErrors()) {
			return mav.addObject("errors", result.getFieldErrors());
		}

		if (userService.exists(user)) {
			result.addError(new FieldError("user", "email", "E-mail já cadastrado."));
			return mav.addObject("errors", result.getFieldErrors());
		}

		User merged = userService.save(user);
		request.setAttribute(Constants.USER, merged);

		return mav.addObject(Constants.USER, merged).addObject("ok", "true");

	}

}
