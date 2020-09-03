package com.puebla.monitoralertas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="folio_alarma", schema="db_monitor")
public class FolioAlarmaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer folioid;
	private String folio;
	private String ipdispositivo;
	private Date fechagenerado;
	private Date fechacierre;
	private String estatus;
}
