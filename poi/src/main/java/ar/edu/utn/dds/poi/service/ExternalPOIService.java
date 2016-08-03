package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.utn.dds.poi.connector.BankSearch;
import ar.edu.utn.dds.poi.connector.CGPSearch;
import ar.edu.utn.dds.poi.domain.Bank;
import ar.edu.utn.dds.poi.domain.CGP;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.OpeningHour;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.model.bankSearchServiceDTO.BankServDTO;
import ar.edu.utn.dds.poi.model.cgpSearchServiceDTO.CGPServDTO;
import ar.edu.utn.dds.poi.model.cgpSearchServiceDTO.ServiceDTO;

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

		for (CGPServDTO cgpServDTO : cgpSearch.getCGPs(null)) 
		{
			CGP cgpPOI = new CGP();
			
			cgpPOI.setName("Comuna " + cgpServDTO.getComuna().toString());
			
			String tags = String.join(",", cgpServDTO.getZonas());
			cgpPOI.setTags(tags);
			
			List<String> services = new ArrayList<String>();
			
			for (ServiceDTO service : cgpServDTO.getServicios()) 
			{
				String serviceName = service.getNombre();
				
				services.add(serviceName);
				
				for (HashMap<String, Integer> horario : service.getHorarios())
				{
					OpeningHour openingHour = new OpeningHour(serviceName, 
													service.getDiaSemana(horario), 
													service.getHoraDesde(horario), 
													service.getMinutosDesde(horario), 
													service.getHoraHasta(horario), 
													service.getMinutosHasta(horario));
					
					cgpPOI.addOpeningHour(openingHour);
				}
			}
			
			cgpPOI.setServices(services);
			
			// TODO: Set the correct coordenate
			Coordenate cgpCoord = new Coordenate(-34.608828, -58.430982);	
			cgpPOI.setCoordenate(cgpCoord);

			pois.add(cgpPOI);
		}
		
		return pois;
	}
}
