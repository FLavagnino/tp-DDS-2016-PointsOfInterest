package ar.edu.utn.dds.poi.domain;

import java.util.ArrayList;

public class Process {
	protected Integer type; // El tipo de proceso es el numero de proceso (1,2,3 o 4)
	private ArrayList<String> ListOfProcess = new ArrayList<String>();
	
	public void setProcessList(ArrayList<String> List){
		this.ListOfProcess = List;
	}
	
	public ArrayList<String> getListOfProcess(){
		return ListOfProcess;
	}
	
	public void setType(int type){
		this.type = type;
	}
	public int getType(){
		return type;
	}
	
	public void updateShop(){
		//en proceso.
	}
	
	public void deletePOI(){
		//hacer
	}
	public void addActionToUsers(){
		//hacer
	}
	public void definitionOfAMultipleProcess(){
		//hacer
	}
}
