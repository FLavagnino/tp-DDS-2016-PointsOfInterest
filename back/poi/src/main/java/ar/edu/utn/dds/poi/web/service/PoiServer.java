package ar.edu.utn.dds.poi.web.service;

import ar.edu.utn.dds.poi.service.ExternalPOIService;
import ar.edu.utn.dds.poi.web.controller.PoiController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoiServer 
{
    private static PoiController poiController;

    @SuppressWarnings("static-access")
	public static void main(String[] args) 
    {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = () -> ExternalPOIService.update();
        executor.scheduleAtFixedRate(periodicTask, 0, 30, TimeUnit.MINUTES);

        poiController.start();
    }
}
