package com.example.chart.pojo;

public class DataPoint {

	public DataPoint() {
		super();
		this.x = "";
		this.value = "";
	}

	public DataPoint(String x, String value) {
		super();
		this.x = x;
		this.value = value;
	}

	private String x;
	private String value;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
