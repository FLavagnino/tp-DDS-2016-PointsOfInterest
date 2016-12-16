package ar.edu.utn.dds.poi.process;

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
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

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.JobResult;
import ar.edu.utn.dds.poi.repository.JobRepository;
import ar.edu.utn.dds.poi.utils.EmailSender;

public abstract class ProcessListener implements JobListener 
{
	public String getName() 
	{
		return getClass().getName();
	}

	protected abstract void rollback();

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
		try 
		{		
			JobRepository jobRep = new JobRepository();
			ProcessConfig config;
			
			config = (ProcessConfig) context.getScheduler().getContext().get("config");
			

			// Obtengo los valores a loguear como resultado.
			String startDate = new SimpleDateFormat("dd-MM-yyyy'T'hh:mm:ss'.000Z'").format(context.getFireTime());
			String endDate = DateTime.now().toString("dd-MM-yyyy'T'hh:mm:ss'.000Z'");
			String jobName = context.getJobDetail().getKey().getName();
			String userName = config.getUserName();
			String userMail = config.getUserMail();
			String resultCode = "";
			String resultMsg = "";
			
			JobResult result = new JobResult(startDate, endDate, jobName, userName, resultCode, resultMsg);
					
			// Logueamos el resultado del job, la idea es que se persista en base de datos
			System.out.println("\nProcess: " + jobName + "executed.");
			System.out.println("Start time: " + context.getFireTime());
			System.out.println("End time: " + DateTime.now());
			
			//jobException = new JobExecutionException("Prueba de error");
			
			if (jobException == null) 
			{	
				System.out.println("Result: SUCCESS");
				
				// Grabo el resultado por OK
				result.setResultCode(Constant.JOBRESULT_OK);
				result.setResultMsg(Constant.JOBRESULT_OK_MSG);
				
				jobRep.saveResult(result);
				
				ejecutarProcesoAnidado(context);
			} 
			else 
			{
				// Grabo el resultado por falla
				result.setResultCode(Constant.JOBRESULT_ERROR);
				result.setResultMsg(Constant.JOBRESULT_ERROR_MSG + jobException.getMessage());
				
				jobRep.saveResult(result);
				
				// Enviar mail
				if (config.getSendMail())
				{
					if(!userMail.isEmpty()) 
					{
						EmailSender emailSender = new EmailSender(userMail, Constant.JOB_ERROR_MAIL_SUBJECT, jobName + Constant.JOB_ERROR_MAIL_TEXT);
						new Thread(emailSender).run();
					}
				}
				
				System.out.println("Result: FAIL");
				System.out.println("Exception: " + jobException.getMessage() + "\n");
				rollback();
			}
		}
		catch (SchedulerException e) 
		{
			System.out.println(e.getMessage());
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
