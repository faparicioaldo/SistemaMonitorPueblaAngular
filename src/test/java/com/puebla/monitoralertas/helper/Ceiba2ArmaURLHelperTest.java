package com.puebla.monitoralertas.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puebla.monitoralertas.service.VideoFrameService;

/**
 * Pruebas unitarias para validar el funcionamiento del cliente http para consumir el API de CEIBA2
 * 
 * @author Aldo Flores Aparicio
 * 
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Ceiba2ArmaURLHelperTest {

	@Autowired
	private Ceiba2ArmaURLHelper ceiba2ArmaURLHelper;
	
	/**
	 * Valida armado de URL para solicitar token requerido para la mayoria de peticones al API
	 * 
	 * */
	@Test
	public void serviceGetUrlServiceKeyTest() {
		String url = ceiba2ArmaURLHelper.getUrlServiceKey();
		System.out.println("TEST > URL CEIBA2 SERVICE GET KEY");
		System.out.println("TEST > URL: " + url);
		assertEquals("http://3.12.246.173:12056/api/v1/basic/key?username=administrador&password=M4ng3k10Sh4r1ng4n", url);
	}

	/**
	 * Valida armado de URL para solicitar lista de dispositivos registrados en CEIBA2
	 * */
	@Test
	public void serviceGetUrlDevicesTest() {		
		String key = "123";
		String url = ceiba2ArmaURLHelper.getUrlDevices(key);
		System.out.println("TEST > URL CEIBA2 SERVICE GET DEVICES");
		System.out.println("TEST > URL: " + url);
		assertEquals("http://3.12.246.173:12056/api/v1/basic/devices?key=123", url);
	}

	/**
	 * Valida armado de 4 URL's de los videos de cada camara conectada en cada uno de los DVR 
	 * registrados en CEIBA2
	 * */
	@Test
	public void serviceGetListUriVideosTest() {	
		String devid = "0099003604";
		Map<String, String> videos = ceiba2ArmaURLHelper.getUriliveVideosByDevid(devid);
		assertNotNull(videos);
//		assertEquals("http://3.12.246.173:12056/api/v1/basic/live/video?key=123&terid=1&chl=2&audio=1&st=0&port=12060", url);

		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=1&st=0&isaudio=1", videos.get("video1"));
		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=2&st=0&isaudio=1", videos.get("video2"));
		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=3&st=0&isaudio=1", videos.get("video3"));
		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=4&st=0&isaudio=1", videos.get("video4"));
		
		System.out.println("TEST > PRUEBA URI VIDEOS POR DEVICE ID");
		System.out.println("TEST > "+videos.get("video1"));
		System.out.println("TEST > "+videos.get("video2"));
		System.out.println("TEST > "+videos.get("video3"));
		System.out.println("TEST > "+videos.get("video4"));
	}

	/**
	 * Valida armado de URL para consultar ultima ubicacion (coordenadas) de dispositivos
	 * */
	@Test	
	public void getUrlDeviceGpsLastTest() {
		String url = ceiba2ArmaURLHelper.getUrlDeviceGpsLast();
		
		System.out.println("TEST > URL CEIBA GET URL DEVICE GPS LAST");
		System.out.println("TEST > URL: "+ url);
		
		assertEquals("http://3.12.246.173:12056/api/v1/basic/gps/last", url);
	}
	
	/**
	 * Valida armado de URL para consultar lista de dispositivos en linea al momento de esta consulta 
	 * */
	@Test
	public void getUrlDevicesOnlineNowTest() {
		String url = ceiba2ArmaURLHelper.getUrlDevicesOnlineNow();
		
		System.out.println("TEST > URL CEIBA GET URL DEVICES ONLINE NOW");
		System.out.println("TEST > URL: "+ url);
		
		assertEquals("http://3.12.246.173:12056/api/v1/basic/state/now", url);
	}
	
	/**
	 * Valida armado de URL para consultar informacion de la alertas 
	 * */
	@Test
	public void getUrlDevicesAlarmInfoTest() {
		String url = ceiba2ArmaURLHelper.getUrlDevicesAlarmInfo();
		
		System.out.println("TEST > URL CEIBA GET URL DEVICES ALARM INFO");
		System.out.println("TEST > URL: "+ url);
		
		assertEquals("http://3.12.246.173:12056/api/v1/basic/alarm/detail", url);
	}
}