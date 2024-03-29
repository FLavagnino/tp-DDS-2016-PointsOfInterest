package ar.edu.utn.dds.poi.process.jobs;

import java.util.ArrayList;
import java.util.Iterator;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ar.edu.utn.dds.poi.constant.Actions;
import ar.edu.utn.dds.poi.domain.Terminal;
import ar.edu.utn.dds.poi.process.ProcessPoi;

public class MultiProcessAddActionToUsers extends ProcessPoi 
{	
	private ArrayList<Actions> actionList;
	protected ArrayList<Actions> actionListInitial;
	Terminal terminal = new Terminal();
	
//	En el constructor es donde queda definido el proceso siguiente
	public MultiProcessAddActionToUsers()
	{
		setSiguienteProceso(null);
		
		actionList = new ArrayList<Actions>();
		actionList.add(Actions.UPDATEPOI);
		actionList.add(Actions.ADDPOI);
		
		actionListInitial = actionList;
				
		//De alguna forma hay que pasarle las acciones
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{		
		Iterator<Actions> iter = actionList.iterator();
			
		listActions();

		while (iter.hasNext())
		{
			// TODO terminal.getActionList().add((Actions) iter.next());
			//System.out.println(Constant.ACTION_ADDED_TO_USER);
		}
		
		listActions();
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
