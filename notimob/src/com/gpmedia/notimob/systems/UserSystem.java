package com.gpmedia.notimob.systems;

import com.gpmedia.notimob.dao.UserDAO;
import com.gpmedia.notimob.model.Builder;
import com.gpmedia.notimob.model.User;

public class UserSystem {

	public static void createUser(String username, String password, String email) {
		User user = new User ();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		if (UserDAO.exists (username)) {
			//probably messasge should be in the command
			throw new RuntimeException ("Извините, но пользователь с таким именем уже существует");
		};
		UserDAO.store (user);
	}

	public static void createAdmin(String name) {
		User user = new Builder<User>(new User()).
			username (name).
			password (name).
			admin (true).
			instance();
		UserDAO.store (user);
	}

}
