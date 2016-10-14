package ar.edu.utn.dds.poi.service.web;

import ar.edu.utn.dds.poi.controller.PoiController;

public class PoiServer {

    private static PoiController poiController;

    public static void main(String[] args) {
        poiController.start();
    }
}
