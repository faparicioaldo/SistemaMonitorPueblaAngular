package com.stv.quartzdemo.helper;

import java.util.Map;

public interface Ceiba2ArmaURLHelper {

	public String getUrlServiceKey();
	public String getUrlDevices(String key);
//	public String getUrlliveVideoUri(String key, String vehiceid, String channel);
	public String getUrlliveVideoUri(String vehiceid);
	public Map<String,String> getUriliveVideosByDevid(String devid);
	public String getUrlDeviceGpsLast();
	public String getUrlDevicesOnlineNow();
	public String getUrlDevicesAlarmInfo();

}
