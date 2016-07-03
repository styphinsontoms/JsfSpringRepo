package com.example.core.service.impl;

import org.springframework.stereotype.Component;

import com.example.core.service.JsonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class JsonServiceImpl implements JsonService{
	
	

	public String convertToString(Object object) {
		String jsonString = "";
		ObjectWriter ow = new ObjectMapper().writer();
		try {
			jsonString = ow.writeValueAsString(object);
			jsonString=jsonString.replaceAll("'", "\\\\'");
			System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
}
