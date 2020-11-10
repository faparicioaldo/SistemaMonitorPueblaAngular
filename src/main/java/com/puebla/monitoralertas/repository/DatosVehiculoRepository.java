package com.puebla.monitoralertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

@Transactional
public interface DatosVehiculoRepository extends JpaRepository<DatosVehiculoEntity, String> {

	@Query(value=
			"SELECT id_dispositivo FROM datos_vehiculo dv WHERE dv.estatus =:estatus"
			, nativeQuery=true)
	public List<String> consultaListaVehiculosCompletosByStatus(@Param("estatus") String estatus);
	
	public List<DatosVehiculoEntity> findByEstatusIsNotOrderByEmpresaAscFechacapturaDesc(String estatus);
}
