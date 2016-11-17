package ar.edu.utn.dds.poi.connector;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.dto.CGPServDTO;
import ar.edu.utn.dds.poi.utils.JsonFactory;

public class CGPSearch 
{		
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<CGPServDTO> getCGPs(String area) 
	{
		String url = Constant.CGP_SEARCH_URL;
		
		if(area != null) 
		{
			url += String.format(Constant.CGP_SEARCH_PARAM, area);
		}
		
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url),
										new TypeReference<ArrayList<CGPServDTO>>() {});
	}
}
