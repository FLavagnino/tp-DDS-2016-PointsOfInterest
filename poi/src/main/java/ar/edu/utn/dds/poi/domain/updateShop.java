package ar.edu.utn.dds.poi.domain;

import ar.edu.utn.dds.poi.domain.Shop;
import ar.edu.utn.dds.poi.constant.Category;

public class updateShop extends Process{
	
	protected Category category;
	protected Coordenate coordenate;
	
		public void update(Shop nameShop, String name, Coordenate coordenate, Category category, String things){
			if (nameShop.getName() == name){
				nameShop.setTags(things);
			}
			else{
				Shop newShop = new Shop(name, coordenate, category ,things );
				newShop.setName(name);
				newShop.setCoordenate(coordenate);
				newShop.setTags(things);
			}
		}
}
