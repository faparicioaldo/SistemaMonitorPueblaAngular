package com.stv.quartzdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="alertas_ceiba", schema="quartz_demo")
public class AlertaCeibaEntity {
	
	@Id
	private String alarmid;
	private String terid;
	private String type;

}
