package com.stv.quartzdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stv.quartzdemo.entity.AlarmaEntity;

@Repository
public interface AlarmaRepository extends JpaRepository<AlarmaEntity, Integer> {

	public List<AlarmaEntity> findTop15ByOrderByAlarmaidDesc();

	public List<AlarmaEntity> findByAlarmaidAndDeviceid(Integer alarmaid, String deviceid);

	public List<AlarmaEntity> findByDeviceid(String deviceid);

	@Transactional
	@Modifying(clearAutomatically = true)
//	@Query("UPDATE Alarma c SET c.estatus = :status WHERE c.id = :alarmaid")
	@Query("UPDATE AlarmaEntity c SET c.estatus = :status WHERE c.alarmaid = :alarmaid")
	int updateAlarmaEstatusByAlarmaid(@Param("alarmaid") Integer alarmaid, @Param("status") String status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE AlarmaEntity c SET c.placa = :placa, c.imei = :imei, c.ipdispositivo = :ipdispositivo, c.ruta=:ruta, c.empresa=:empresa, c.economico=:economico WHERE c.alarmaid = :alarmaid")
	int updateAlarmaDatos(@Param("alarmaid") Integer alarmaid, @Param("placa") String placa, @Param("imei") String imei, @Param("ipdispositivo") String ipdispositivo, @Param("ruta") String ruta, @Param("empresa") String empresa, @Param("economico") String economico);

}
