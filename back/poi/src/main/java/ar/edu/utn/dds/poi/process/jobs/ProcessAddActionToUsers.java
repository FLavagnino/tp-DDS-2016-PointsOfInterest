package ar.edu.utn.dds.poi.process.jobs;

import java.util.ArrayList;
import java.util.Iterator;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ar.edu.utn.dds.poi.constant.Actions;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.Terminal;
import ar.edu.utn.dds.poi.process.ProcessPoi;
import ar.edu.utn.dds.poi.utils.DBBackupUtil;

public class ProcessAddActionToUsers extends ProcessPoi 
{	
	private ArrayList<Actions> actionList;
	protected ArrayList<Actions> actionListInitial;
	Terminal terminal = new Terminal();
	
//	En el constructor es donde queda definido el proceso siguiente
	public ProcessAddActionToUsers()
	{
		setSiguienteProceso(null);
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{		
//		Iterator<Actions> iter = actionList.iterator();
//		
//		System.out.println("\nAntes de actualizar las acciones...");
//		listActions();
//
//		while (iter.hasNext())
//		{
//			// TODO terminal.getActionList().add((Actions) iter.next());
//			System.out.println(Constant.ACTION_ADDED_TO_USER);
//		}
//		
//		System.out.println("\nDespues de actualizar las acciones...");
//		listActions();
		
		DBBackupUtil dbUtil = new DBBackupUtil();
		dbUtil.backupDB("poi", "root", "root2016", "C:\\Backup\\Db_" + DateTime.now().toString("yyyyMMdd") + ".sql");
	}
	
	private void listActions()
	{
		// TODO
//		for (Actions action : terminal.getActionList())
//		{
//			System.out.println("Action: " + action.toString());
//		}
	}
}
