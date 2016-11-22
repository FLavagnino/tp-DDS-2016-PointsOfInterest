package ar.edu.utn.dds.poi.process.jobs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ar.edu.utn.dds.poi.constant.Category;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.Shop;
import ar.edu.utn.dds.poi.exception.InvalidPoiException;
import ar.edu.utn.dds.poi.process.ProcessPoi;
import ar.edu.utn.dds.poi.process.jobs.ProcessDeletePoi;
import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.utils.ReadTextFile;

public class ProcessUpdateShop extends ProcessPoi
{	
	private ReadTextFile readFile;
	POIService poiservice = new POIService();
	
	public ProcessUpdateShop()
	{
		setSiguienteProceso(null);
	}
		
	public void execute(JobExecutionContext context) throws JobExecutionException  
	{	
		try
		{
			// Vemos como esta la lista actualmente, despues hay que borrarlo.
			System.out.println("\nAntes de actualizar...");
			poiservice.listPOIs();
			
			readFile = new ReadTextFile();
			readFile.readText(Constant.UPDATE_SHOP_FILE_PATH);
			
			for (Shop shopToUpdate : readFile.shopsToUpdate) 
			{
				SearchResult searchResult = poiservice.search(shopToUpdate.getName());
				
				if (searchResult != null && searchResult.getPois().size() > 0)
				{
					Shop poi = (Shop) searchResult.getPois().get(0);
					poi.setTags(shopToUpdate.getTags());
					
					try
					{	
						poiservice.updatePoi(poi);	
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
						Shop newShop = new Shop(shopToUpdate.getName(), null, "", 0, shopToUpdate.getTags());
						poiservice.addPoi(newShop);
					}
					catch(InvalidPoiException a)
					{
						System.out.println(Constant.UPDATE_SHOP_FILE_INVALID_POI_ERROR_MSG);
					}
				}
			}
			
			// Vemos como quedo la lista de POIs.
			System.out.println("\nLuego de actualizar...");
			poiservice.listPOIs();
			System.out.println("\n");
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
