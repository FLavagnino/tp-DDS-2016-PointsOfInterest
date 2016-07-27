package ar.edu.utn.dds.poi.connector;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.model.bankSearchServiceDTO.BankServDTO;
import ar.edu.utn.dds.poi.utils.JsonFactory;

public class BankSearch {
	
	private final static String URL = "http://trimatek.org/Consultas/banco";
	private final static String PARAM = "?banco=%s&servicio=%s";
	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<BankServDTO> getBanks(String bank, String service) {
		String url = URL;
		if(bank != null && service != null) {
			url += String.format(PARAM, bank, service);
		}
		System.out.println(url);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<ArrayList<BankServDTO>>() {});
	}
}
