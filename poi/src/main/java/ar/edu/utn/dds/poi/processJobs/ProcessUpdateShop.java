package ar.edu.utn.dds.poi.processJobs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.domain.Shop;
import ar.edu.utn.dds.poi.exception.InvalidPoiException;
import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.utils.Mock;
import ar.edu.utn.dds.poi.utils.ReadTextFile;
import ar.edu.utn.dds.poi.processService.ProcessPoi;
import ar.edu.utn.dds.poi.processJobs.ProcessDeletePoi;

public class ProcessUpdateShop extends ProcessPoi
{	
	private ReadTextFile readFile;
	POIService poiservice = new POIService();
	
	public ProcessUpdateShop()
	{
		setSiguienteProceso(ProcessDeletePoi.class);
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException  
	{	
		try
		{
			readFile = new ReadTextFile();
			readFile.readText(Constant.UPDATE_SHOP_FILE_PATH);
			
			// Esto lo deberiamos reemplazar por la base de datos.
			List<Shop> poiList = new ArrayList<Shop>();
			Mock mockValues = new Mock();
			poiList = mockValues.getUpdateShopList();
			
			for (Shop shopToUpdate : readFile.shopsToUpdate) 
			{
				if (poiList.contains(shopToUpdate))
				{
					try
					{
						poiservice.updatePoi(shopToUpdate);	
					}
					catch(InvalidPoiException a)
					{
						System.out.println(Constant.UPDATE_SHOP_FILE_INVALID_POI_ERROR_MSG);
					}
				}
				else
				{
					try
					{
						poiservice.addPoi(shopToUpdate);
					}
					catch(InvalidPoiException a)
					{
						System.out.println(Constant.UPDATE_SHOP_FILE_INVALID_POI_ERROR_MSG);
					}
				}
			}
		}
		catch(FileNotFoundException a)
		{
			System.out.println(Constant.UPDATE_SHOP_FILE_NOT_FOUND_ERROR_MSG);
		}
		catch(IOException b)
		{
			System.out.println(Constant.UPDATE_SHOP_FILE_ERROR_MSG);
		}	
	}	
}
