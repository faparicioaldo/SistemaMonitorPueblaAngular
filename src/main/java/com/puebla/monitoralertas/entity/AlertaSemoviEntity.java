package com.puebla.monitoralertas.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="alertas_semovi", schema="db_monitor")
public class AlertaSemoviEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_alerta")
	private Integer idalerta;
	@Column(name="id_dispositivo")
	private String iddispositivo;
	@Column(name="semovi_estatus")
	private String semoviestatus = "";
	@Column(name="semovi_mensaje")
	private String semovimensaje = "";
	@Column(name="semovi_respuesta")
	private String semovirespuesta = "";
	private String latitud;
	private String longitud;
	private String address;
	private String speed;
	private String course;
	private String ignition;
	private String panicbutton;
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
