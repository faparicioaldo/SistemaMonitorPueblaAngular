package com.puebla.monitoralertas.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puebla.monitoralertas.entity.AlertaSemoviEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlertaSemoviRepositoryTest {

	@Autowired
	private AlertaSemoviRepository alertaSemoviRepository;
	
	@Test
	public void test() {
		AlertaSemoviEntity entity = new AlertaSemoviEntity();
		List<AlertaSemoviEntity> entitySave = null;
		
		String idDispositivo = "2";
		entity.setIddispositivo(idDispositivo);
		
		alertaSemoviRepository.save(entity);
				
		entitySave = alertaSemoviRepository.findByIddispositivo(idDispositivo);
		
		assertEquals(entitySave.get(0).getIddispositivo(), entity.getIddispositivo());
	}

}
