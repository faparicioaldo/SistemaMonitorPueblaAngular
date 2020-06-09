package com.stv.quartzdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stv.quartzdemo.entity.DatosVehiculoEntity;

@Repository
public interface DatosVehiculoRepository extends JpaRepository<DatosVehiculoEntity, String> {

//	public List<AlarmaEntity> findTop15ByOrderByAlarmaidDesc();

//	public List<DatosVehiculoEntity> findTop15ByOrderByAlarmaidDesc();

//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE AlarmaEntity c SET c.estatus = :status WHERE c.alarmaid = :alarmaid")
//	int updateAlarmaEstatusByAlarmaid(@Param("alarmaid") Integer alarmaid, @Param("status") String status);
}
