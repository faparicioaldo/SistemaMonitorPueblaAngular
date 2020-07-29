package com.puebla.monitoralertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.puebla.monitoralertas.entity.AlertaSemoviEntity;

@Repository
public interface AlertaSemoviRepository extends JpaRepository<AlertaSemoviEntity, Integer> {

	@Query(value=
			"select " 
				+ "  a.id_alerta "
				+ ", a.ceiba_alarmid "
				+ ", v.ID_DISPOSITIVO "
			    + ", v.plate "
			    + ", v.eco "
			    + ", a.ceiba_gpstime "
			    + ", a.semovi_estatus "
			    + ", v.EMPRESA "
			    + ", v.route "
			    + ", a.semovi_mensaje "
			    + ", CASE a.ceiba_type "
			        + " WHEN 5 THEN \'IO 1\' "
					+ " WHEN 6 THEN \'IO 2\' "
					+ " WHEN 7 THEN \'IO 3\' "
					+ " WHEN 8 THEN \'IO 4\' "
					+ " WHEN 9 THEN \'IO 5\' "
					+ " WHEN 10 THEN \'IO 6\' "
					+ " WHEN 11 THEN \'IO 7\' "
					+ " WHEN 12 THEN \'IO 8\' "
					+ " WHEN 13 THEN \'Panic Alert\' "
			        + " ELSE a.ceiba_type "
		        + " end ceiba_type "
		    + "from "
				+ "quartz_demo.alertas_semovi a " 
				+ "inner join "
				+ "quartz_demo.datos_vehiculo v " 
			+ "on " 
				+ "a.id_dispositivo = v.id_dispositivo "
			+ " order by a.ceiba_gpstime desc"			
			, nativeQuery=true)
	public List<Object[]> consultaAlertasEnviadasSemovi();
	
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
