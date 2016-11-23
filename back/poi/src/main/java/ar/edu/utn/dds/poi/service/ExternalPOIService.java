package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.utn.dds.poi.connector.*;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.dto.*;
import ar.edu.utn.dds.poi.utils.MongoUtil;

public class ExternalPOIService 
{	
	private BankSearch bankSearch;
	private CGPSearch cgpSearch;
	
	public ExternalPOIService() 
	{
		bankSearch = new BankSearch();
		cgpSearch = new CGPSearch();
	}
	
	public List<POI> getExternalPois(String keyString) 
	{
		List<POI> pois = new ArrayList<POI>();
		pois.addAll(getBanksAsPois(keyString));
		pois.addAll(getCGPsAsPois(keyString));
		return pois;
	}

	public List<POI> getExternalPoisCached(String keyString)
	{
		MongoUtil mongoUtil = new MongoUtil();

		List<POI> pois = new ArrayList<POI>();

		for (String keyword : keyString.split(" ")) {
			pois.addAll(mongoUtil.getPois(keyword));
		}

		return pois;
	}
	
	private List<POI> getBanksAsPois(String keyString) 
	{
		List<BankServDTO> banks = new ArrayList<BankServDTO>();
		for (String keyword : keyString.split(" ")) {
			banks = mergeBank(banks, bankSearch.getBanks(keyword, null));
			banks = mergeBank(banks, bankSearch.getBanks(null, keyword));
		}
		
		List<POI> pois = new ArrayList<POI>();

		for (BankServDTO bankServDTO : banks) 
		{
			pois.add(bankDTOToPOT(bankServDTO));
		}
		
		return pois;
	}

	private List<POI> getCGPsAsPois(String keyString) 
	{
		List<CGPServDTO> cgps = new ArrayList<CGPServDTO>();
		for (String keyword : keyString.split(" ")) {
			cgps = mergeCGP(cgps, cgpSearch.getCGPs(keyword));
		}
		
		List<POI> pois = new ArrayList<POI>();

		for (CGPServDTO cgpServDTO : cgps) 
		{
			pois.add(cgpDTOToPOI(cgpServDTO));
		}
		
		return pois;
	}
	
	private List<BankServDTO> mergeBank(List<BankServDTO> listA, List<BankServDTO> listB)
	{
		Boolean contain;
		for (BankServDTO bankB : listB) 
		{
			contain = false;
			for (BankServDTO bankA : listA) 
			{
				if (bankA.getBanco().equals(bankB.getBanco()) && bankA.getX().equals(bankB.getX()) && bankA.getY().equals(bankB.getY())) 
				{
					contain = true;
					break;
				}
			}
			if(!contain) 
			{
				listA.add(bankB);
			}
		}
		
		return listA;
	}
	
	private List<CGPServDTO> mergeCGP(List<CGPServDTO> listA, List<CGPServDTO> listB)
	{
		Boolean contain;
		for (CGPServDTO cgpB : listB) 
		{
			contain = false;
			for (CGPServDTO cgpA : listA) 
			{
				if (cgpA.getComuna().equals(cgpB.getComuna()) && cgpA.getDomicilio().equals(cgpB.getDomicilio())) 
				{
					contain = true;
					break;
				}
			}
			if(!contain) 
			{
				listA.add(cgpB);
			}
		}
		
		return listA;
	}

	public List<POI> getAllBanksAsPois()
	{
		List<BankServDTO> banks = bankSearch.getBanks(null, null);

		List<POI> pois = new ArrayList<POI>();

		for (BankServDTO bankServDTO : banks)
		{
			pois.add(bankDTOToPOT(bankServDTO));
		}

		return pois;
	}

	public List<POI> getAllCGPsAsPois()
	{
		List<CGPServDTO> cgps = cgpSearch.getCGPs(null);

		List<POI> pois = new ArrayList<POI>();

		for (CGPServDTO cgpServDTO : cgps)
		{
			pois.add(cgpDTOToPOI(cgpServDTO));
		}

		return pois;
	}

	private POI bankDTOToPOT(BankServDTO bankServDTO) {
		Coordenate coordenate = new Coordenate(bankServDTO.getX(), bankServDTO.getY());
		String tags = String.join(",", bankServDTO.getServicios());
		return new Bank(bankServDTO.getBanco(), coordenate, tags);
	}

	private POI cgpDTOToPOI(CGPServDTO cgpServDTO) 
	{
		CGP cgpPOI = new CGP();

		cgpPOI.setName("Comuna " + cgpServDTO.getComuna().toString());

		String tags = String.join(",", cgpServDTO.getZonas());
		cgpPOI.setTags(tags);

		String services = "";

		for (ServiceDTO service : cgpServDTO.getServicios())
		{
			String serviceName = service.getNombre().toLowerCase();
			services = services.concat(serviceName + ",");

			for (HashMap<String, Integer> horario : service.getHorarios())
			{
				cgpPOI.addOpeningHour(new OpeningHour(serviceName,
						horario.get("diaSemana"),
						horario.get("horaDesde"),
						horario.get("minutosDesde"),
						horario.get("horaHasta"),
						horario.get("minutosHasta")
				));
			}
		}

		cgpPOI.setServices(services);

		// TODO: Set the correct coordenate
		Coordenate cgpCoord = new Coordenate(0.0, 0.0);
		cgpPOI.setCoordenate(cgpCoord);

		return cgpPOI;
	}

	public static void update() {
		ExternalPOIService externalPOIService = new ExternalPOIService();

		List<POI> pois = new ArrayList<>();

		pois.addAll(externalPOIService.getAllBanksAsPois());
		pois.addAll(externalPOIService.getAllCGPsAsPois());

		MongoUtil mongoUtil = new MongoUtil();
		List<POI> newPois = new ArrayList<>();

		for(POI poi : pois) {
			if(!mongoUtil.existPoi(poi.getName())) {
				newPois.add(poi);
			}
		}

		if(!newPois.isEmpty()) {
			mongoUtil.savePois(newPois);
		}
	}


}
