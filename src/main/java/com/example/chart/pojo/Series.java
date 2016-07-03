package com.example.chart.pojo;

import java.util.ArrayList;
import java.util.List;

public class Series {

	List<DataPoint> data;
	private Labels labels;
	private String name;

	public Series() {
		super();
		this.data = new ArrayList<DataPoint>();
	}

	public List<DataPoint> getData() {
		return data;
	}

	public void setData(List<DataPoint> data) {
		this.data = data;
	}

	public Labels getLabels() {
		return labels;
	}

	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
