package com.puebla.monitoralertas.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name="municipios_semovi", schema="db_monitor")
public class MunicipioSemoviEntity implements Serializable {
	
	private static final long serialVersionUID = 2992403909568978800L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="municipio_id")
	private Integer municipioId;
	
	@Column(name="clave_semovi")
	private Integer claveSemovi;
	
	@Column(name="municipio")
	private String municipio;

}
