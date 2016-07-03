package com.example.dao.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 458961548607745960L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDR_ID")
	private long addressId;
	
	@Column(name = "STREET_NAME")
	private String streetName;

	@Column(name = "CITY_NAME")
	private String cityName;

	@Column(name = "COUNTRY_NAME")
	private String countryName;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	

	@PostConstruct
	public void initBean()
	{
		System.out.println("Initializing Bean"+this.getClass().toString());
	}
	
	@PreDestroy
	public void destroyBean()
	{
		System.out.println("Destroying Bean"+this.getClass().toString());
	}
}
