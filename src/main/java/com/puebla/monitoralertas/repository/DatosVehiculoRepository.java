package com.puebla.monitoralertas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

@Repository
public interface DatosVehiculoRepository extends JpaRepository<DatosVehiculoEntity, String> {

}
