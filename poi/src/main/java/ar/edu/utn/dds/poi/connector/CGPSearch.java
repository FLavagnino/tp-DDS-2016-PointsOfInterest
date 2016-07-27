package ar.edu.utn.dds.poi.connector;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.model.cgpSearchServiceDTO.CGPServDTO;
import ar.edu.utn.dds.poi.utils.JsonFactory;

public class CGPSearch {
	
	private final static String URL = "http://trimatek.org/Consultas/centro";
	private final static String PARAM = "?zona=%s";
	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<CGPServDTO> getCGPs(String area) {
		String url = URL;
		if(area != null) {
			url += String.format(PARAM, area);
		}
		System.out.println(url);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<ArrayList<CGPServDTO>>() {});
	}
}
