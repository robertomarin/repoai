package ai.liga.ligaai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.liga.dao.HibernateDAOFactory;
import ai.liga.ligaai.dao.UserDao;
import ai.liga.ligaai.model.User;

@Service
public class UsuarioService {

	private UserDao userDao;

	@Autowired
	public UsuarioService(HibernateDAOFactory factory) {
		userDao = factory.getUserDao();
	}

	public User merge(User user) {
		user = userDao.merge(user);

		
		
		return null;
	}

}