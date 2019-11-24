package com.geoipservicesoap.GeoIPServiceSoap;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.beans.GeoIPObj;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;

public class GeoIPServiceMain {

	static Logger logger = Logger.getLogger(GeoIPServiceMain.class);

	public static void main(String[] args) {
		logger.info("main() start");
		logger.debug("input arguement length - " + args.length);
		if (args.length != 1) {
			logger.error("You need to pass in one IP address");
			System.out.println("You need to pass in one IP address");
		} else {
			String ipAddress = args[0];
			GeoIPService geoIPService = new GeoIPService();

			GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
			String geoIPXML = geoIPServiceSoap.getIpLocation20(ipAddress);
			// System.out.println(geoIPXML);
			JSONObject jsonObject = null;
			try {
				logger.debug("XML to JSON conversion");
				jsonObject = XML.toJSONObject(geoIPXML);
			} catch (JSONException e) {
				logger.fatal(e.getMessage(), e);
				e.printStackTrace();
			}
			String jsonString = jsonObject.toString().toLowerCase();
			logger.info("JSON - " + jsonString);
			ObjectMapper mapper = new ObjectMapper();
			GeoIPObj geoIPObj = null;
			try {
				logger.debug("JSON to Object mapping");
				geoIPObj = mapper.readValue(jsonString, GeoIPObj.class);
			} catch (IOException e) {
				logger.fatal(e.getMessage(), e);
				e.printStackTrace();
			}
			System.out.println(
					"IP Address " + ipAddress + " belongs to country " + geoIPObj.getGeoip().getCountry().toUpperCase()
					+ " and state " + geoIPObj.getGeoip().getState().toUpperCase());
			logger.info(
					"IP Address " + ipAddress + " belongs to country " + geoIPObj.getGeoip().getCountry().toUpperCase()
					+ " and state " + geoIPObj.getGeoip().getState().toUpperCase());
			logger.info("main() end");

		}
	}

}
