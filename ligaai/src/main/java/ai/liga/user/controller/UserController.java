package ai.liga.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.view.RedirectView;

import ai.liga.cookie.CookieComponent;
import ai.liga.user.model.User;
import ai.liga.user.service.UserService;
import ai.liga.util.$;
import ai.liga.util.Constants;

@Controller
public class UserController {

	private final UserService userService;
	
	private final CookieComponent cookieComponent;

	@Autowired
	public UserController(UserService userService, CookieComponent cookieComponent) {
		this.userService = userService;
		this.cookieComponent = cookieComponent;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setMessageCodesResolver(new DefaultMessageCodesResolver());
	}

	@RequestMapping("/u/conta")
	public ModelAndView mountViewUpload(final HttpServletRequest request) {

		User user = $.getUserFromRequest(request);

		if (user == null) {
			return new ModelAndView(new RedirectView("/"));
		}

		return new ModelAndView("/u/conta").addObject("user", user);
	}
	

	@RequestMapping("/u/registrar")
	public String registrar(HttpServletRequest request) {
		$.getUserFromRequest(request);
		User user = (User) request.getAttribute(Constants.USER);
		return "/u/registrar";
	}

	@RequestMapping("/u/entrar")
	public ModelAndView entrar(@Valid User user, BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new JsonView());
		if (result.hasErrors()) {
			return mav.addObject("errors", result.getFieldErrors());
		}

		user = userService.login(user);
		if (user == null) {
			result.addError(new FieldError("user", "email", "E-mail ou senha inválidos."));
			return mav.addObject("errors", result.getFieldErrors());
		}

		$.setUserOnRequest(request, user);
		return mav.addObject(Constants.USER, user).addObject("ok", "true");
	}
	
	@RequestMapping("/u/sair")
	public String sair(HttpServletRequest request, HttpServletResponse response) {
		$.setUserOnRequest(request, null);
		response.addCookie(cookieComponent.createExpiredCookie(Constants.USER));
		
		return "redirect:/";
	}

	@RequestMapping("/u/criar")
	public ModelAndView criar(@Valid User user, BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new JsonView());
		if (result.hasErrors()) {
			return mav.addObject("errors", result.getFieldErrors());
		}

		if (userService.exists(user)) {
			result.addError(new FieldError("user", "email", "E-mail já cadastrado."));
			return mav.addObject("errors", result.getFieldErrors());
		}
		user = userService.save(user);
		$.setUserOnRequest(request, user);
		return mav.addObject(Constants.USER, user).addObject("ok", "true");
	}

}
