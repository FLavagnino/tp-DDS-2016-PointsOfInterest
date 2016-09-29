package ar.edu.utn.dds.poi.domain;

import ar.edu.utn.dds.poi.constant.Category;
import ar.edu.utn.dds.poi.service.POIService;

public class UpdateShop extends Thread{
	
	protected Integer type; // El tipo de proceso es el numero de proceso (1,2,3 o 4)
	
	protected Category category;
	protected Coordenate coordenate;
	protected String name;
	protected String tags;
	protected String path;
	
	POIService poiservice = new POIService();
	
	Shop poishop = new Shop(name, coordenate , category, tags);
	
	public void setId(int type){
		this.type = type;
	}
	public int getType(){
		return type;
	}

	public void run() {
		poiservice.updateShopOfProcess1(poishop, path);
	}
	
}
