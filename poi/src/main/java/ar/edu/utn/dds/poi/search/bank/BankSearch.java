package ar.edu.utn.dds.poi.search.bank;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.search.bank.domain.Bank;
import ar.edu.utn.dds.poi.web.json.JsonFactory;
import ar.edu.utn.dds.poi.web.service.HttpService;

public class BankSearch {
	
	private final static String URL = "http://trimatek.org/Consultas/banco"; 
	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<Bank> getBanks(String bank, String service) {
		String url = URL;
		if(bank != null && service != null) {
			url += "?banco=" + bank + "&servicio=" + service;
		}
		System.out.println(url);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<ArrayList<Bank>>() {});
	}
}
