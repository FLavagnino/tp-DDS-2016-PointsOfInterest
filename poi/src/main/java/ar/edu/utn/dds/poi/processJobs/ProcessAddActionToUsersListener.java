package ar.edu.utn.dds.poi.processJobs;

import org.quartz.JobListener;

import ar.edu.utn.dds.poi.processService.ProcessListener;

public class ProcessAddActionToUsersListener extends ProcessListener implements JobListener {

	@Override
	protected void rollback(){
		System.out.println("Rollback de AgregarAcciones");
	}
}
