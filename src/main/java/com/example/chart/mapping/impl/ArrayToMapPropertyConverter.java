package com.example.chart.mapping.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

import com.example.chart.pojo.DataPoint;
import com.example.chart.pojo.Series;

public class ArrayToMapPropertyConverter extends DozerConverter<List, Map>
		implements MapperAware {

	private Mapper mapper;

	public ArrayToMapPropertyConverter() {
		super(List.class, Map.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;

	}

	@Override
	public List convertFrom(Map dest, List src) {
		return null;
	}

	@Override
	public Map<String, Object> convertTo(List src, Map dest) {
		Map<String, Object> seriesMap = new HashMap<String, Object>();
		Object[] seriesArray;
		Series convertedSeries = new Series();
		List<DataPoint> dpList = new ArrayList<DataPoint>();
		for (Object object : src) {
			seriesArray = (Object[]) object;
			DataPoint dp = new DataPoint();
			dp.setX(String.valueOf(seriesArray[0]));
			dp.setValue(String.valueOf(seriesArray[1]));
			dpList.add(dp);

		}
		
		convertedSeries.setData(dpList);
		seriesMap.put("Series", convertedSeries);
		return seriesMap;
	}

}
