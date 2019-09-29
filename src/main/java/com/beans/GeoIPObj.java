package com.beans;

import java.io.Serializable;

public class GeoIPObj implements Serializable {

	private static final long serialVersionUID = 1L;
	private GeoIP geoip;

	public GeoIPObj() {
		super();
	}

	public GeoIP getGeoip() {
		return geoip;
	}

	public void setGeoip(GeoIP geoip) {
		this.geoip = geoip;
	}

	@Override
	public String toString() {
		return "GeoIPObj [geoip=" + geoip + "]";
	}

}
