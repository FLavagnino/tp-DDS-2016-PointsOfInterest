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

public class ProcessAddActionToUsersRollback extends ProcessPoi 
{	
//	En el constructor es donde queda definido el proceso siguiente
	public ProcessAddActionToUsersRollback()
	{
		setSiguienteProceso(null);
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{		
		DBBackupUtil dbUtil = new DBBackupUtil();
		dbUtil.restoreDB("root", "root2016", "C:\\Backup\\Db_" + DateTime.now().toString("yyyyMMdd") + ".sql");
	}
}
