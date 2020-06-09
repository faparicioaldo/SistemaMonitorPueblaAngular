package com.stv.quartzdemo.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stv.quartzdemo.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DeviceGpsLastPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DeviceTerIdPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesGpsLastResponsePojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2KeyPojo;
import com.stv.quartzdemo.json.pojo.DataDevicePojo;
import com.stv.quartzdemo.json.pojo.DataPojo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APISistemaVideoVigilanciaServiceTest {

	@Autowired
	private APISistemaVideoVigilanciaService apiSistemaVideoVigilancia;
	
	private String key = null;
	private String deviceId = null;
	
	@Test
	@Before
	public void testLoginKey() {
		Ceiba2KeyPojo keyObject = apiSistemaVideoVigilancia.login();
		key = keyObject.getData().getKey();
		System.err.println("TEST SERVICE GET KEY CEIBA2");
		System.err.println("Key: " + key);
		assertNotNull(key);
		assertNotEquals("", key.trim());
	}

	@Test
	public void testGetDevices() {
		Ceiba2DevicesPojo devicesObject = apiSistemaVideoVigilancia.getAllVehicles(key);
		List<DataDevicePojo> devicesList = devicesObject.getData();
		System.err.println("TEST SERVICE GET DEVICES CEIBA2");
		assertNotNull(devicesList);
		assertNotNull(devicesList.get(0));
		deviceId = devicesList.get(0).getDeviceid();
		System.err.println("DEVICE 1: " + deviceId);
	}

	@After
	public void testGetUriLiveVideo() {
		String channel = "1";
		String liveVideoObject = apiSistemaVideoVigilancia.getLiveVideo(deviceId);
		assertNotNull(liveVideoObject);
		System.err.println("TEST SERVICE GET LIVE VIDEO CEIBA2");
		System.err.println("Live Video Uri: " + liveVideoObject);
	}

	@After
	public void testGetUriDevicesGpsLast() {
		List<String> teridList = new ArrayList<>();
		teridList.add("0099003BA9");
		teridList.add("");
		
		Ceiba2DevicesGpsLastResponsePojo devicesGpsList = apiSistemaVideoVigilancia.getGPSVehicle(key, teridList);
		assertNotNull(devicesGpsList);
		assertNotNull(devicesGpsList.getData());
		Ceiba2DeviceGpsLastPojo deviceGps = devicesGpsList.getData().get(0);
		assertNotNull(deviceGps);
		System.err.println("TEST SERVICE GET DEVICES GPS CEIBA 2");
		System.err.println("GPS datos: " + deviceGps.toString());
	}

	@After
	public void testGetUriDevicesOnlineNow() {
		List<String> teridList = new ArrayList<>();
		teridList.add("0099003BA9");
		teridList.add("0099003604");
		
		Ceiba2DeviceTerIdPojo devicesOnlineNow = apiSistemaVideoVigilancia.getEstatusVehicle(key, teridList);
		assertNotNull(devicesOnlineNow);
		assertNotNull(devicesOnlineNow.getData());
		System.err.println("TEST SERVICE GET DEVICES ONLINE NOW CEIBA 2");
		if(devicesOnlineNow.getData().size()>0) {
			DataPojo deviceOnlineNow = devicesOnlineNow.getData().get(0);
			assertNotNull(deviceOnlineNow);
			System.err.println("DEVICE ONLINE: " + deviceOnlineNow);
		}
		System.err.println("DEVICE ONLINE: " + devicesOnlineNow.getData().size());
	}

	@After
	public void testGetUriDevicesAlarm() {
		List<String> terids = new ArrayList<>();
		terids.add("0099003BA9");
		terids.add("0099003604");
		List<String> types = new ArrayList<>();
		types.add("13");
		String starttime = formatFechaOnlyDay(new Date()) + " 00:00:00";
		String endtime = formatFechaOnlyDay(new Date()) + " 23:59:59";
		
		Ceiba2DevicesAlarmResponseDTO devicesAlarm = apiSistemaVideoVigilancia.getDevicesAlarmInfo(key, terids, types, starttime, endtime);
		assertNotNull(devicesAlarm);
		assertNotNull(devicesAlarm.getData());
		System.err.println("TEST SERVICE GET DEVICES ALARM PANICO CEIBA 2");
		if(devicesAlarm.getData().size()>0) {
			Ceiba2DeviceAlarmResponseDTO deviceAlarmPanic = devicesAlarm.getData().get(0);
			assertNotNull(deviceAlarmPanic);
			System.err.println("DEVICE ALARM PANICO: " + deviceAlarmPanic);
		}
		System.err.println("DEVICE ALARMAS PANICO: " + devicesAlarm.getData().size());
	}
	
	private String formatFechaOnlyDay(Date fecha) {
		String fechaFormated = null;
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		fechaFormated = formatter.format(fecha);
		return fechaFormated;
	}


}
