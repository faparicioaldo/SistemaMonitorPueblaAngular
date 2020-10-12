package com.puebla.monitoralertas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puebla.monitoralertas.entity.MunicipioSemoviEntity;

@Repository
public interface MunicipioSemoviRepository extends JpaRepository<MunicipioSemoviEntity, Integer> {

}
