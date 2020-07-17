package com.puebla.monitoralertas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puebla.monitoralertas.entity.AlertaCeibaEntity;

@Repository
public interface AlertaCeibaRepository extends JpaRepository<AlertaCeibaEntity, String> {

}
