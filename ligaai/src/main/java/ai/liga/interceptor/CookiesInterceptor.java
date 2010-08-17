package ai.liga.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ai.liga.ligaai.cookie.CookieComponent;
import ai.liga.ligaai.cookie.Cookies;
import ai.liga.ligaai.model.User;
import ai.liga.ligaai.service.UserService;
import ai.liga.util.Constants;

public class CookiesInterceptor implements HandlerInterceptor {

	private final UserService usuarioService;

	private final CookieComponent cookieComponent;

	@Autowired
	public CookiesInterceptor(UserService usuarioService, CookieComponent cookieComponent) {
		this.usuarioService = usuarioService;
		this.cookieComponent = cookieComponent;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = cookieComponent.getUserFromCookie(Cookies.getCookie(request.getCookies(), Constants.USER));
		if (user != null) {
			request.setAttribute(Constants.USER, user);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		User user = User.class.cast(request.getAttribute(Constants.USER));
		Cookie userCookie = cookieComponent.getCookieFromUser(user);
		if (userCookie != null) {
			response.addCookie(userCookie);
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
