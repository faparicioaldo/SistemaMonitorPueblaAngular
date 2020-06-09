package com.stv.quartzdemo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="folio_alarma", schema="quartz_demo")
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
