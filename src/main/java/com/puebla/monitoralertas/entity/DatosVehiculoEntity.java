package com.puebla.monitoralertas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DATOS_VEHICULO", schema = "db_monitor")
@DynamicUpdate(true)
@Getter
@Setter
public class DatosVehiculoEntity implements Serializable {

	private static final long serialVersionUID = -1119260550162574380L;

	@Id
	@Column(name="ID_DISPOSITIVO")
	private String iddispositivo;
	private String empresa;
	@Column(name="FECHA_CAPTURA")
	private Date fechacaptura;
	@Column(name="FECHA_MODIFICACION")
	private Date fechamodificacion;	
	private String plate;
	private String vin;
	private String engine;
	private String year;
	private String color;
	private String route;
	private String rs;
	private String eco;
	private String branch;
	private String subbranch;
	private String municipio;
	private String concesion;	
	@Column(name="URL_CAMERA")
	private String urlcamera;
	private String estatus;

//	@OneToMany( targetEntity=AlertaSemoviEntity.class, fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//	private List<AlertaSemoviEntity> alertas;
	
}