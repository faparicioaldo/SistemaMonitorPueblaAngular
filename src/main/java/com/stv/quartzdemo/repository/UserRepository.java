package com.stv.quartzdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stv.quartzdemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);
}
