package ar.edu.utn.dds.poi.domain;
import java.util.*;

public class Admin extends User 
{
	private ArrayList<String> ListOfProcess = new ArrayList<String>();
	
	public void setProcessList(ArrayList<String> List){
		this.ListOfProcess = List;
	}
	
	public ArrayList<String> getListOfProcess(){
		return ListOfProcess;
	}
		
}
