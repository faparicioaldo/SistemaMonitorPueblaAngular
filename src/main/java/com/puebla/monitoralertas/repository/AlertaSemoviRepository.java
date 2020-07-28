package com.puebla.monitoralertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puebla.monitoralertas.entity.AlarmaEntity;
import com.puebla.monitoralertas.entity.AlertaSemoviEntity;

@Repository
public interface AlertaSemoviRepository extends JpaRepository<AlertaSemoviEntity, Integer> {

	public List<AlertaSemoviEntity> findTop15ByOrderByIdalertaDesc();

	public List<AlertaSemoviEntity> findByIdalertaAndIddispositivo(Integer alarmaid, String deviceid);

	public List<AlertaSemoviEntity> findByIddispositivo(String deviceid);

//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE AlertaSemoviEntity c SET c.estatus = :status WHERE c.alarmaid = :alarmaid")
//	int updateAlarmaEstatusByAlarmaid(@Param("alarmaid") Integer alarmaid, @Param("status") String status);

//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE AlertaSemoviEntity c SET c.placa = :placa, c.imei = :imei, c.ipdispositivo = :ipdispositivo, c.ruta=:ruta, c.empresa=:empresa, c.economico=:economico WHERE c.alarmaid = :alarmaid")
//	int updateAlarmaDatos(@Param("alarmaid") Integer alarmaid, @Param("placa") String placa, @Param("imei") String imei, @Param("ipdispositivo") String ipdispositivo, @Param("ruta") String ruta, @Param("empresa") String empresa, @Param("economico") String economico);

}
