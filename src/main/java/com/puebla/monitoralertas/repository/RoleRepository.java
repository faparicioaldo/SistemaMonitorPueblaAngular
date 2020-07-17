package com.puebla.monitoralertas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puebla.monitoralertas.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
