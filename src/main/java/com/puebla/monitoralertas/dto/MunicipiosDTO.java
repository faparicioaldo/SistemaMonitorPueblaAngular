package com.puebla.monitoralertas.dto;

import java.util.List;

import com.puebla.monitoralertas.entity.MunicipioSemoviEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipiosDTO extends RespuestaJSON{

	private List<MunicipioSemoviEntity> municipios;
	
}
