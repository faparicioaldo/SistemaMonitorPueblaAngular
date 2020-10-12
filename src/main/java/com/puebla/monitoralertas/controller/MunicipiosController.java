package com.puebla.monitoralertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.puebla.monitoralertas.dto.MunicipiosDTO;
import com.puebla.monitoralertas.entity.MunicipioSemoviEntity;
import com.puebla.monitoralertas.repository.MunicipioSemoviRepository;

@RestController
public class MunicipiosController {

	@Autowired
	private MunicipioSemoviRepository municipioSemoviRepository;	
	
	@PostMapping("/getMunicipios")
	public @ResponseBody MunicipiosDTO getMunicipios() {

		MunicipiosDTO municipios = new MunicipiosDTO();
		List<MunicipioSemoviEntity> municipiosSemovi = municipioSemoviRepository.findAll();
		
		municipios.setMunicipios(municipiosSemovi);
		
		return municipios;
	}
}
