package ar.edu.utn.dds.poi.utils;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.poi.domain.Shop;
import ar.edu.utn.dds.poi.domain.Bank;
import ar.edu.utn.dds.poi.domain.BusStop;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.POI;


public class Mock 
{
	public Mock()
	{
	}
	
	public List<Shop> getUpdateShopList()
	{
		List<Shop> shopsToUpdate = new ArrayList<Shop>();
		
		Shop shopToUpdate = new Shop("kiosko", "kiosko prueba1 pepe");
		shopsToUpdate.add(shopToUpdate);

		shopToUpdate = new Shop("muebleria", "muebles mesas sillas");
		shopsToUpdate.add(shopToUpdate);
		
		shopToUpdate = new Shop("ferreteria", "tornillo martillo taladro");
		shopsToUpdate.add(shopToUpdate);
				
		return shopsToUpdate;
	}
	public List<POI> getPoisToDelete(){
		List<POI> poisToDelete = new ArrayList<POI>();
		
		Coordenate bankCoord = new Coordenate(-34.618191, -58.428769);
		Coordenate busCoord = new Coordenate(-34.618191, -58.428769);
		
		Shop poiToDelete = new Shop("kiosko", "kiosko prueba1 pepe");
		poisToDelete.add(poiToDelete);
		
		Bank poiToDelete2 = new Bank("Banco Ciudad",bankCoord, "tags");
		poisToDelete.add(poiToDelete2);
		
		BusStop poiToDelete3 = new BusStop("Parada 114", busCoord,114 ,"114" );
		poisToDelete.add(poiToDelete3);
		
		return poisToDelete;
	}
	
}
