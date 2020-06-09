package com.stv.quartzdemo.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stv.quartzdemo.config.CargaCeiba2ServerProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ceiba2ArmaURLHelperTest {

	@Autowired
	private Ceiba2ArmaURLHelper ceiba2ArmaURLHelper;
	
	@Autowired
	private CargaCeiba2ServerProperties ceiba2ServerProperties;

	@Test
	public void testServiceGetUrlServiceKey() {
		String url = ceiba2ArmaURLHelper.getUrlServiceKey();
		System.err.println("URL CEIBA2 SERVICE GET KEY");
		System.err.println("URL: " + url);
		assertEquals("http://3.12.246.173:12056/api/v1/basic/key?username=administrador&password=M4ng3k10Sh4r1ng4n", url);
	}

	@Test
	public void testServiceGetUrlDevices() {		
		String key = "123";
		String url = ceiba2ArmaURLHelper.getUrlDevices(key);
		System.err.println("URL CEIBA2 SERVICE GET DEVICES");
		System.err.println("URL: " + url);
		assertEquals("http://3.12.246.173:12056/api/v1/basic/devices?key=123", url);
	}

	@Test
	public void testServiceGetUrlLiveVideoUri() {		
		String key = "123";
		String vehicleId = "1";
		String channel = "2";
		
		String url = ceiba2ArmaURLHelper.getUrlliveVideoUri(vehicleId);
		System.err.println("URL CEIBA2 SERVICE GET LIVE VIDEO");
		System.err.println("URL: " + url);
//		assertEquals("http://3.12.246.173:12056/api/v1/basic/live/video?key=123&terid=1&chl=2&audio=1&st=0&port=12060", url);
		assertEquals("http://3.14.24.174:8084/video/"+vehicleId, url);

	}

	@Test
	public void testServiceGetListUriVideos() {	
		String devid = "0099003604";
		Map<String, String> videos = ceiba2ArmaURLHelper.getUriliveVideosByDevid(devid);
		assertNotNull(videos);
		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=1&st=0&isaudio=1", videos.get("video1"));
		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=2&st=0&isaudio=1", videos.get("video2"));
		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=3&st=0&isaudio=1", videos.get("video3"));
		assertEquals("http://3.12.246.173:12060/live.flv?devid="+devid+"&chl=4&st=0&isaudio=1", videos.get("video4"));
		
		System.err.println("PRUEBA URI VIDEOS POR DEVICE ID");
		System.err.println(videos.get("video1"));
		System.err.println(videos.get("video2"));
		System.err.println(videos.get("video3"));
		System.err.println(videos.get("video4"));
	}
	
}
