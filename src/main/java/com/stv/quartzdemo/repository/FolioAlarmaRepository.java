package com.stv.quartzdemo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stv.quartzdemo.entity.FolioAlarmaEntity;

@Repository
public interface FolioAlarmaRepository extends JpaRepository<FolioAlarmaEntity, Long> {

	public List<FolioAlarmaEntity> findTop15ByOrderByFolioidDesc();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE FolioAlarmaEntity c SET c.estatus = :status WHERE c.folioid = :folioid")
	int updateFolioAlarmaEstatusByFolioid(@Param("folioid") Integer folioid, @Param("status") String status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE FolioAlarmaEntity c SET c.estatus = :status, c.fechacierre = :fechacierre WHERE c.folioid = :folioid")
	int updateFolioAlarmaEstatusFechaCierreByFolioid(@Param("folioid") Integer folioid, @Param("status") String status, @Param("fechacierre") Date fechaCierre);

}
