package com.stv.quartzdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stv.quartzdemo.entity.AlertaCeibaEntity;

@Repository
public interface AlertaCeibaRepository extends JpaRepository<AlertaCeibaEntity, String> {

}
