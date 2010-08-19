package ai.liga.util;

import javax.servlet.http.HttpServletRequest;

import ai.liga.user.model.User;

public class $ {

	public static User getUserFromRequest(HttpServletRequest request) {
		return (User) request.getAttribute(Constants.USER);
	}

	public static void setUserOnRequest(HttpServletRequest request, User user) {
		request.setAttribute(Constants.USER, user);
	}

}
