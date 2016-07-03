package com.example.chart.pojo.data;

import java.util.ArrayList;
import java.util.List;

public class ChartData {
	
	List<DataItem> data;

	public ChartData() {
		super();
		this.data=new ArrayList<DataItem>();
	}

	public List<DataItem> getData() {
		return data;
	}

	public void setData(List<DataItem> data) {
		this.data = data;
	}

	
	
}
