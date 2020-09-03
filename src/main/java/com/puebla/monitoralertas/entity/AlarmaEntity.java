package com.puebla.monitoralertas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="alarma", schema="db_monitor")
public class AlarmaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer alarmaid;
	private String placa;
	private String economico;
	private String estatus;
	private String empresa;
	private String socio;
	private Date fecha;

	private String imei;
	private String ipdispositivo;
	private String hora_utc;
	private String deviceid;
	private String latitud;
	private String longitud;
	private String velocidad;
	private String direccion;
	private String alarma;
	private String ruta;
	
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

}
