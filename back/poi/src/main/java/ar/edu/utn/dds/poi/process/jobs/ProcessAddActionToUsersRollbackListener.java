package ar.edu.utn.dds.poi.process.jobs;

import org.quartz.JobListener;
import ar.edu.utn.dds.poi.process.ProcessListener;

public class ProcessAddActionToUsersRollbackListener extends ProcessListener implements JobListener 
{	
	@Override
	protected void rollback()
	{
	}
}
