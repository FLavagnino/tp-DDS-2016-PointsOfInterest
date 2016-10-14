package ar.edu.utn.dds.poi.utils;

import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import ar.edu.utn.dds.poi.constant.Constant;

public class JsonFactory 
{	
	private ObjectMapper objectMapper;

	@SuppressWarnings("deprecation")
	public JsonFactory() 
	{
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	}

	public ObjectMapper getObjectMapper() 
	{
		return objectMapper;
	}

	public <T> T fromJson(InputStreamReader inputStreamReader, TypeReference<T> typeReference) 
	{
		try 
		{
			return this.objectMapper.readValue(inputStreamReader, typeReference);
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(Constant.JSONFACTORY_FROMJSON_ERROR_MSG, e);
		}
	}

	public String toJson(Object object) {
		try {
			String jsonString = this.objectMapper.writeValueAsString(object);
			return jsonString;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error creating a json", e);
		}
	}
}