package ar.edu.utn.dds.poi.process.jobs;
import java.util.ArrayList;

import org.quartz.JobListener;

import ar.edu.utn.dds.poi.constant.Actions;
import ar.edu.utn.dds.poi.domain.Terminal;
import ar.edu.utn.dds.poi.process.ProcessListener;

public class ProcessUpdateShopListener extends ProcessListener implements JobListener 
{
	@Override
	protected void rollback() 
	{
		System.out.println("Rollback de ActualizaLocales");		
	}

	@Override
	protected void rollback(ArrayList<Actions> originalList, Terminal terminal) 
	{	
	}	
}

