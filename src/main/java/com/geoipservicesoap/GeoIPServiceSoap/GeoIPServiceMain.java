package com.geoipservicesoap.GeoIPServiceSoap;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.beans.GeoIPObj;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;

public class GeoIPServiceMain {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("You need to pass in one IP address");
		} else {
			String ipAddress = args[0];
			GeoIPService geoIPService = new GeoIPService();

			GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
			String geoIPXML = geoIPServiceSoap.getIpLocation20(ipAddress);
			// System.out.println(geoIPXML);
			JSONObject jsonObject = null;
			try {
				jsonObject = XML.toJSONObject(geoIPXML);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			String jsonString = jsonObject.toString().toLowerCase();
			ObjectMapper mapper = new ObjectMapper();
			GeoIPObj geoIPObj = null;
			try {
				geoIPObj = mapper.readValue(jsonString, GeoIPObj.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(
					"IP Address " + ipAddress + " belongs to country " + geoIPObj.getGeoip().getCountry().toUpperCase()
					+ " and state " + geoIPObj.getGeoip().getState().toUpperCase());
		}
	}

}
