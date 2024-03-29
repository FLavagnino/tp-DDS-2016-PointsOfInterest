package ar.edu.utn.dds.poi.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import ar.edu.utn.dds.poi.constant.Constant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class JsonFactory 
{	
	private ObjectMapper objectMapper;

	@SuppressWarnings("deprecation")
	public JsonFactory() 
	{
		objectMapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();
		module.addSerializer(DateTime.class, new DateTimeSerializer(
				new JacksonJodaDateFormat(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"))));
		objectMapper.registerModule(module);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	}

	public ObjectMapper getObjectMapper() 
	{
		return objectMapper;
	}

	public <T> T fromJson(InputStreamReader inputStreamReader, TypeReference<T> typeReference) 
	{
		try 
		{
			return objectMapper.readValue(inputStreamReader, typeReference);
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(Constant.JSONFACTORY_FROMJSON_ERROR_MSG, e);
		}
	}

	public <T> T fromJson(String s, TypeReference<T> typeReference)
	{
		try
		{
			return objectMapper.readValue(s, typeReference);
		}
		catch (IOException e)
		{
			throw new RuntimeException(Constant.JSONFACTORY_FROMJSON_ERROR_MSG, e);
		}
	}

	public String toJson(Object object) {
		try {
			String jsonString = objectMapper.writeValueAsString(object);
			return jsonString;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error creating a json", e);
		}
	}
}