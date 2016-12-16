package ar.edu.utn.dds.poi.process.jobs;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import ar.edu.utn.dds.poi.domain.Action;
import ar.edu.utn.dds.poi.domain.User;
import ar.edu.utn.dds.poi.process.ProcessPoi;
import ar.edu.utn.dds.poi.repository.ActionRepository;
import ar.edu.utn.dds.poi.repository.UserRepository;
import ar.edu.utn.dds.poi.utils.DBBackupUtil;

public class ProcessAddActionToUsers extends ProcessPoi 
{		
	// En el constructor es donde queda definido el proceso siguiente
	public ProcessAddActionToUsers()
	{
		setSiguienteProceso(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{	
		UserRepository userRep = new UserRepository();
		ActionRepository actionRep = new ActionRepository();
		
		try 
		{
			DBBackupUtil dbUtil = new DBBackupUtil();
			dbUtil.backupDB("poi", "root", "root2016", "C:\\Backup\\Db_" + DateTime.now().toString("yyyyMMdd") + ".sql");
			
			Map<String, List<Action>> actionsByUser = (Map<String, List<Action>>) context.getScheduler().getContext().get("actionsByUser");
			
			for ( Map.Entry<String, List<Action>> entry : actionsByUser.entrySet()) 
			{
			    String userName = entry.getKey();
			    List<Action> actions = entry.getValue();
			    
			    User user = userRep.getUser(userName);
			    
			    for (Action action : actions)
			    {
			    	action.setUser(user);
			    	actionRep.save(action);
			    }
			}
		}
		catch (SchedulerException e) 
		{
		}
	}
}
