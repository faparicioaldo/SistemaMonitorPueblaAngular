package com.puebla.monitoralertas.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puebla.monitoralertas.entity.MunicipioSemoviEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MunicipioSemoviRepositoryTest {

	@Autowired
	private MunicipioSemoviRepository municipioSemoviRepository;
	
	@Test
	public void findAllMunicipiosSemoviTest() {
		List<MunicipioSemoviEntity> municipios = municipioSemoviRepository.findAll();
		assertEquals(217, municipios.size());
	}

}
