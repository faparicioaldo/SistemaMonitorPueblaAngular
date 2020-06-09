package com.stv.quartzdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stv.quartzdemo.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
