package ar.edu.utn.dds.poi.processJobs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.exception.InvalidPoiException;
import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.utils.readTextFile;
import ar.edu.utn.dds.poi.processService.ProcessPoi;
import ar.edu.utn.dds.poi.processJobs.ProcessDeletePoi;

public class ProcessUpdateShop extends ProcessPoi{
	
	private List<POI> poiList;
	private String path;
	private readTextFile readFile;
	
	POIService poiservice = new POIService();
	
	public ProcessUpdateShop(){
		setSiguienteProceso(ProcessDeletePoi.class);
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException  {
		
		try{
			readFile.readText(path);
			if (poiList.contains(readFile.newShop)){
				try{
						poiservice.updatePoi(readFile.newShop);	
					}
					catch(InvalidPoiException a){
						System.out.println("Invalid POI");
					}
				}
				else{
					try{
						poiservice.addPoi(readFile.newShop);
					}
					catch(InvalidPoiException a){
						System.out.println("Invalid POI.");
					}
				}
		}
		catch(FileNotFoundException a){
			System.out.println("File not found");
		}
		catch(IOException b){
			System.out.println("File error");
		}	
	}
	
}
