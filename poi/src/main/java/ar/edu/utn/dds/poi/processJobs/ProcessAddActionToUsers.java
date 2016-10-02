package ar.edu.utn.dds.poi.processJobs;

import java.util.ArrayList;
import java.util.Iterator;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ar.edu.utn.dds.poi.constant.Actions;
import ar.edu.utn.dds.poi.domain.Terminal;
import ar.edu.utn.dds.poi.processService.ProcessPoi;


public class ProcessAddActionToUsers extends ProcessPoi {
	
	private ArrayList<Actions> actionList;
	Terminal terminal = new Terminal();

//	En el constructor es donde queda definido el proceso siguiente
	public ProcessAddActionToUsers(){
		setSiguienteProceso(null);
	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		Iterator iter = actionList.iterator();
		
		while (iter.hasNext())
			actionList.add((Actions) iter.next());
				
			System.out.println("La lista de acciones fue actualizada");

	}

}
