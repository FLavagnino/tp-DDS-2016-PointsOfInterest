package ar.edu.utn.dds.poi.service;

import java.util.List;

import ar.edu.utn.dds.poi.connector.GoogleDistance;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.model.distanceServiceDTO.DistServElementDTO;
import ar.edu.utn.dds.poi.model.distanceServiceDTO.DistServFullResponseDTO;
import ar.edu.utn.dds.poi.model.distanceServiceDTO.DistServRowDTO;

public class DistanceService 
{
	
	public Integer metersFromToHaversine(Coordenate origin, Coordenate destination) 
	{
		double earthRadius = 6372.8; // In kilometers
        double dLat = Math.toRadians(destination.getLatitude() - origin.getLatitude());
        double dLon = Math.toRadians(destination.getLongitude() - origin.getLongitude());
        double lat1 = Math.toRadians(origin.getLatitude());
        double lat2 = Math.toRadians(destination.getLatitude());
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double result = earthRadius * c * 1000; //for meters

        return (int)result;	
	}
	
	public Integer metersFromTo(Coordenate origin, Coordenate destination) {
		GoogleDistance googleDistance = new GoogleDistance();
		DistServFullResponseDTO fullResponseDTO = googleDistance.getFullResponse(origin, destination);
		List<DistServRowDTO> rows = fullResponseDTO.getRows();
		List<DistServElementDTO> elements = rows.get(0).getElements();
		return Integer.parseInt(elements.get(0).getDistance().get("value"));
	}
	
}
