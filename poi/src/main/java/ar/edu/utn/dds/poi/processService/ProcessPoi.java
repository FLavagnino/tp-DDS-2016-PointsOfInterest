package ar.edu.utn.dds.poi.processService;

import org.quartz.Job;

public abstract class ProcessPoi implements Job {

	private static Class SIGUIENTE_PROCESO;

	public static Class getSiguienteProceso() {
		return SIGUIENTE_PROCESO;
	}
	
	public static void setSiguienteProceso(Class siguienteProceso) {
		SIGUIENTE_PROCESO = siguienteProceso;
	}

//	Con el prop�sito de simplificar el c�digo se usa la API Reflection de Java
	public ProcessListener getProcesoListener() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return (ProcessListener) getClass().getClassLoader().loadClass(getClass().getName()+"Listener").newInstance();
	}

}