package com.stv.quartzdemo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DATOS_VEHICULO", schema = "QUARTZ_DEMO")
public class DatosVehiculoEntity {

	@Id
	private String idDispositivo;
	private String empresa;
	private String socio;
	private String ip;
	private Date fechaModificacion;
	private Date fechaCaptura;
	
	private String imei;
	private String longitude;
	private String latitude;
	private String address;
	private String speed;
	private String course;
	private String date;
	private String ignition;
	private String fix;
	private String sat;
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
	private String url_camera;
	private String panic_button;

}