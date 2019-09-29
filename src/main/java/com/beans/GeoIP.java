package com.beans;

import java.io.Serializable;

public class GeoIP implements Serializable {

	private static final long serialVersionUID = 1L;
	private String country;
	private String state;

	public GeoIP() {
		super();
	}

	public GeoIP(String country, String state) {
		super();
		this.country = country;
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "GeoIP [country=" + country + ", state=" + state + "]";
	}

}
