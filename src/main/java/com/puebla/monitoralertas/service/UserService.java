package com.puebla.monitoralertas.service;

import com.puebla.monitoralertas.entity.User;

public interface UserService {
	public void save(User user);

	public User findByUsername(String username);
}