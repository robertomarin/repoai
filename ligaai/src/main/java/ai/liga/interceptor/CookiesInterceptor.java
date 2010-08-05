package ai.liga.interceptor;

import static ai.liga.util.Constants.USER_COOKIE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.cookie.CookieComponent;
import ai.liga.ligaai.cookie.Cookies;
import ai.liga.ligaai.model.User;
import ai.liga.ligaai.service.UsuarioService;

public class CookiesInterceptor implements HandlerInterceptor {

	private final UsuarioService usuarioService;

	@Autowired
	public CookiesInterceptor(UsuarioService usuarioService, CookieComponent cookieComponent) {
		this.usuarioService = usuarioService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String userCookie = Cookies.getCookieValue(request.getCookies(), USER_COOKIE);

		if (!GenericValidator.isBlankOrNull(userCookie)) {
		}
		System.out.println(userCookie);

		User user = new User("robertomarin+legal@gmail.com");
		request.setAttribute("user", user);
		

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
