package ar.edu.utn.dds.poi.service.web;

import ar.edu.utn.dds.poi.controller.PoiController;
import ar.edu.utn.dds.poi.service.ExternalPOIService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoiServer {

    private static PoiController poiController;

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = () -> ExternalPOIService.update();
        executor.scheduleAtFixedRate(periodicTask, 0, 10, TimeUnit.MINUTES);

        poiController.start();
    }
}
