package ar.edu.utn.dds.poi.domain;
import ar.edu.utn.dds.poi.domain.Process;

public class Admin extends User 
{
	public Process process;
	
	public void selectProcess(int selectedProcess){
		process.setType(selectedProcess);
	}
			
}
