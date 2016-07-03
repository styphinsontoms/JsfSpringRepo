package com.example.chart.pojo;

import java.util.ArrayList;
import java.util.List;

public class ChartDataSource {
	List<Object[]> srcData = new ArrayList<Object[]>();

	public List<Object[]> getSrcData() {
		return srcData;
	}

	public void setSrcData(List<Object[]> srcData) {
		this.srcData = srcData;
	}

	@Override
	public String toString() {
		return "ChartDataSource [srcData=" + srcData + "]";
	}

	
	
	
}
