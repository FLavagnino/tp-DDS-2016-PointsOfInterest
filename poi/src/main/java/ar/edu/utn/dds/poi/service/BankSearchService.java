package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.dto.BankServDTO;
import ar.edu.utn.dds.poi.factory.JsonFactory;
import ar.edu.utn.dds.poi.utils.HttpService;

public class BankSearchService {
	
	private final static String URL = "http://trimatek.org/Consultas/banco"; 
	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<BankServDTO> getBanks(String bank, String service) {
		String url = URL;
		if(bank != null && service != null) {
			url += "?banco=" + bank + "&servicio=" + service;
		}
		System.out.println(url);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<ArrayList<BankServDTO>>() {});
	}
}
