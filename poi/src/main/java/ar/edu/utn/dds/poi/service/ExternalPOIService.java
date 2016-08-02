package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.poi.connector.BankSearch;
import ar.edu.utn.dds.poi.connector.CGPSearch;
import ar.edu.utn.dds.poi.domain.Bank;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.model.bankSearchServiceDTO.BankServDTO;

public class ExternalPOIService 
{	
	private BankSearch bankSearch;
	private CGPSearch cgpSearch;
	
	public ExternalPOIService() 
	{
		bankSearch = new BankSearch();
		cgpSearch = new CGPSearch();
	}
	
	public List<POI> getExternalPois() 
	{
		List<POI> pois = new ArrayList<POI>();
		pois.addAll(getBanksAsPois());
		pois.addAll(getCGPsAsPois());
		return pois;
	}
	
	private List<POI> getBanksAsPois() 
	{
		List<POI> pois = new ArrayList<POI>();

		for (BankServDTO bankServDTO : bankSearch.getBanks(null, null)) 
		{
			Coordenate coordenate = new Coordenate(bankServDTO.getX(), bankServDTO.getY());
			String tags = String.join(",", bankServDTO.getServicios());
			Bank bank = new Bank(bankServDTO.getBanco(), coordenate, tags);
			
			pois.add(bank);
		}
		
		return pois;
	}

	private List<POI> getCGPsAsPois() 
	{
		List<POI> pois = new ArrayList<POI>();

//		for (CGPServDTO cgpServDTO : cgpSearch.getCGPs(null)) 
//		{
//			
//		}
		
		return pois;
	}
}
