package com.ms.bugzilla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ms.bugzilla.entity.User;
import com.ms.bugzilla.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import com.ms.bugzilla.repository.LoginRepository;
@Service
public class LoginServiceImpl implements LoginService {
	private final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		String pwd = user.getPassword();
		LOG.debug("pwd to be saved '{}'", pwd);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String encodedString = encoder.encode(user.getPassword());
		LOG.debug("encoded pwd to be saved '{}'", encodedString);
		user.setPassword(encodedString);
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub

		return userRepository.findAll();
	}

	/*
	 * @Autowired LoginRepository loginRepository;
	 */

	/*
	 * @Override public LoginRequest getUserByUserName(String name) { // TODO
	 * Auto-generated method stub
	 * 
	 * User user=loginRepository.findbyUserName(name); if(user==null) { throw new
	 * UserNameNotfoundException(name);
	 * 
	 * } return
	 * 
	 * }
	 */

}
