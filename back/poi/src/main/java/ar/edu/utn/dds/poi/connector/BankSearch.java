package ar.edu.utn.dds.poi.connector;

import java.util.ArrayList;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.dto.BankServDTO;
import ar.edu.utn.dds.poi.utils.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;

public class BankSearch 
{		
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<BankServDTO> getBanks(String bank, String service) 
	{
		String url = Constant.BANK_SEARCH_URL;
		
		if(bank != null && service != null) 
		{
			url += String.format(Constant.BANK_SEARCH_ALL_PARAMS, bank, service);
		}
		else if (bank != null)
		{
			url += String.format(Constant.BANK_SEARCH_BANK_PARAM, bank);
		}
		else if (service != null)
		{
			url += String.format(Constant.BANK_SEARCH_SERVICE_PARAM, service);
		}
		
		//System.out.println(url);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), 
										new TypeReference<ArrayList<BankServDTO>>() {});
	}
}
