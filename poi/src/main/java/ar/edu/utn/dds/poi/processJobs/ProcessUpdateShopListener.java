package ar.edu.utn.dds.poi.processJobs;
import ar.edu.utn.dds.poi.processService.ProcessListener;

import org.quartz.JobListener;

public class ProcessUpdateShopListener extends ProcessListener implements JobListener {

	@Override
	protected void rollback() {
		System.out.println("Rollback de ActualizaLocales");
		
	}
	
}

