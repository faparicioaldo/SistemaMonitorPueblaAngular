package com.stv.quartzdemo.service;

import com.stv.quartzdemo.entity.User;

public interface UserService {
	public void save(User user);

	public User findByUsername(String username);
}