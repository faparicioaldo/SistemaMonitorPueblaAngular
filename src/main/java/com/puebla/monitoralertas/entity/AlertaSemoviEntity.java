package com.puebla.monitoralertas.entity;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name="alertas_semovi", schema="quartz_demo")
public class AlertaSemoviEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_alerta")
	private Integer idalerta;
	@Column(name="id_dispositivo")
	private String iddispositivo;
	private String estatus;
	private String latitud;
	private String longitud;
	@Column(name="fecha_recepcion_alerta")
	private Date fecharecepcionalerta;
	@Column(name="ceiba_time")
	private Date ceibatime;
	@Column(name="ceiba_gpstime")
	private Date ceibagpstime;
	@Column(name="ceiba_type")
	private String ceibatype;
	@Column(name="ceiba_content")
	private String ceibacontent;
	@Column(name="ceiba_cmdtype")
	private String ceibacmdtype;
	@Column(name="ceiba_alarmid")
	private String ceibaalarmid;
	
	@PrePersist
	public void prePersist() {
		fecharecepcionalerta = new Date();
	}

}
