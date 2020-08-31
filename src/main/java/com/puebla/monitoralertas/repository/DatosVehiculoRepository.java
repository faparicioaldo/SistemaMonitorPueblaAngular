package com.puebla.monitoralertas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

@Transactional
public interface DatosVehiculoRepository extends JpaRepository<DatosVehiculoEntity, String> {
	
//	@Modifying(clearAutomatically = true)
//	@Query(value="UPDATE quartz_demo.datos_vehiculo SET fecha_captura=:fecha_captura WHERE id_dispositivo=:id_dispositivo" 
//			, nativeQuery=true)
//	public void updateVehicleFechaCapturaByIdDispositivo(@Param("fecha_captura")Date fechaCaptura, @Param("id_dispositivo")String idDispositivo);
	
}
