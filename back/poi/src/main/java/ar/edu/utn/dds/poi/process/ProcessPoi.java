package ar.edu.utn.dds.poi.process;

import org.quartz.Job;

//Todos los procesos deben heredar de esta clase e implementar el método execute() que exige la interfaz Job
@SuppressWarnings({ "rawtypes" })
public abstract class ProcessPoi implements Job 
{
	private static Class SIGUIENTE_PROCESO;
	
	public static Class getSiguienteProceso() 
	{
		return SIGUIENTE_PROCESO;
	}
	
	public static void setSiguienteProceso(Class siguienteProceso) 
	{
		SIGUIENTE_PROCESO = siguienteProceso;
	}
	
//	Con el propósito de simplificar el código se usa la API Reflection de Java
	public ProcessListener getProcesoListener() throws ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		return (ProcessListener) getClass().getClassLoader()
									.loadClass(getClass().getName()+"Listener").newInstance();
	}
}
