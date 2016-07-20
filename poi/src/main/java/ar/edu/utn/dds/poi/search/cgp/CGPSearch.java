package ar.edu.utn.dds.poi.search.cgp;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.search.cgp.domain.CGP;
import ar.edu.utn.dds.poi.web.json.JsonFactory;
import ar.edu.utn.dds.poi.web.service.HttpService;

public class CGPSearch {
	
	private final static String URL = "http://trimatek.org/Consultas/centro"; 
	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<CGP> getCGPs(String area) {
		String url = URL;
		if(area != null) {
			url += "?zona=" + area;
		}
		System.out.println(url);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<ArrayList<CGP>>() {});
	}
}
