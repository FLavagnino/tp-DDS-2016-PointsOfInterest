package ar.edu.utn.dds.poi.process;

import java.util.ArrayList;
import ar.edu.utn.dds.poi.constant.Actions;
import ar.edu.utn.dds.poi.domain.Terminal;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.KeyMatcher;

public abstract class ProcessListener implements JobListener 
{
	public String getName() 
	{
		return getClass().getName();
	}

	protected abstract void rollback();
	protected abstract void rollback(ArrayList<Actions> originalList, Terminal terminal);
	

	public void jobToBeExecuted(JobExecutionContext context) 
	{
		System.out.println("Before running the process: " + context.getJobDetail().getKey().getName());
	}
	
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) 
	{
	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) 
	{
		String jobName = context.getJobDetail().getKey().getName();

		// Logueamos el resultado del job, la idea es que se persista en base de datos
		System.out.println("\nProcess: " + jobName + " process executed successfully");
		System.out.println("Start time: " + context.getFireTime());
		System.out.println("End time: " + context.getFireTime());
		
		if (jobException == null) 
		{	
			System.out.println("Result: SUCCESS");
			
			try 
			{
				ejecutarProcesoAnidado(context);
			} 
			catch (SchedulerException e) 
			{
				System.out.println(e.getMessage());
			}

		} 
		else 
		{
			System.out.println("Result: FAIL");
			System.out.println("Exception: " + jobException.getMessage() + "\n");
			rollback();
		}
	}

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public void ejecutarProcesoAnidado(JobExecutionContext context) throws SchedulerException 
	{
		Scheduler scheduler = context.getScheduler();
		String nombreProcesoActual = getClass().getName().replace("Listener", "");
		
		try 
		{
			Class actualProceso = getClass().getClassLoader().loadClass(nombreProcesoActual);
			Class siguienteProceso = ((ProcessPoi)actualProceso.newInstance()).getSiguienteProceso();

			if (siguienteProceso != null) 
			{
				ProcessListener siguienteListener = ((ProcessPoi) siguienteProceso.newInstance()).getProcesoListener();
				JobKey jobKey = new JobKey(siguienteProceso.getSimpleName());
				JobDetail nextJob = JobBuilder.newJob(siguienteProceso).withIdentity(jobKey).requestRecovery(true).build();
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey + "trigger").startNow().build();
				scheduler.getListenerManager().addJobListener((JobListener) siguienteListener, KeyMatcher.keyEquals(jobKey));
				scheduler.scheduleJob(nextJob, trigger);
			}

		} 
		catch (ClassNotFoundException cnf) 
		{
			System.out.println(cnf.getMessage());
		} 
		catch (InstantiationException ie) 
		{
			System.out.println(ie.getMessage());
		} 
		catch (IllegalAccessException iae) 
		{
			System.out.println(iae.getMessage());
		}
	}
}
