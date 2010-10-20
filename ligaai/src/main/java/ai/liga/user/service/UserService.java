package ai.liga.user.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.liga.dao.HibernateDAOFactory;
import ai.liga.user.dao.UserDao;
import ai.liga.user.model.User;

@Service
public class UserService {

	private UserDao userDao;

	@Autowired
	public UserService(HibernateDAOFactory factory) {
		userDao = factory.getUserDao();
	}

	public boolean exists(User user) {
		return userDao.findByEmail(user) != null;
	}
	
	public User load(Long id) {
		return userDao.load(id);
	}

	public User save(User user) {
		user.setCreated(Calendar.getInstance());
		user = userDao.merge(user);
		return user;
	}
	
	public User merge(User user) {
		return userDao.merge(user);
	}

	public User login(User user) {
		return userDao.login(user);
	}

	public boolean giveAvatar(User user) {
		user = userDao.load(user.getId());
		user.setAvatar(true);
		return user != null;
	}

}
