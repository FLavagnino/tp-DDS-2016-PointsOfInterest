package ar.edu.utn.dds.poi.utils;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.poi.domain.Shop;

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
}
