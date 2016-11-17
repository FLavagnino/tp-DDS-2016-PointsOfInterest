package ar.edu.utn.dds.poi.process.jobs;

import java.util.ArrayList;
import ar.edu.utn.dds.poi.constant.Constant;

import org.quartz.JobListener;

import ar.edu.utn.dds.poi.constant.Actions;
import ar.edu.utn.dds.poi.domain.Terminal;
import ar.edu.utn.dds.poi.process.ProcessListener;

public class MultiProcessAddActionToUsersListener extends ProcessListener implements JobListener 
{
	@Override
	protected void rollback(ArrayList<Actions> list, Terminal term)
	{	
		// TODO term.setActionList(list);	
		System.out.println(Constant.ROLLBACK_DONE);
	}
	
	@Override
	protected void rollback()
	{
	}
}
