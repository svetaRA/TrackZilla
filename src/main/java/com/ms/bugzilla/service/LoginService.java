package com.ms.bugzilla.service;

import java.util.List;

import com.ms.bugzilla.entity.User;


public interface LoginService {

	public User save(User user);

	public List<User> getUsers();

}
