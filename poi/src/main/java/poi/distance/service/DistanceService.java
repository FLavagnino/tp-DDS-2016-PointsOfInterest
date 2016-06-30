package poi.distance.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import poi.distance.service.response.DTO.ElementDTO;
import poi.distance.service.response.DTO.FullResponseDTO;
import poi.distance.service.response.DTO.RowDTO;
import poi.domain.Coordenate;
import poi.json.JsonFactory;
import poi.web.service.HttpService;

public class DistanceService 
{
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
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
	
	public Integer metersFromTo(Coordenate origin, Coordenate destination) 
	{
		FullResponseDTO fullResponseDTO = getFullResponseOf(origin, destination);
		List<RowDTO> rows = fullResponseDTO.getRows();
		List<ElementDTO> elements = rows.get(0).getElements();
		return Integer.parseInt(elements.get(0).getDistance().get("value"));
	}
	
	private FullResponseDTO getFullResponseOf(Coordenate origin, Coordenate destination) 
	{
		String url = getUrl(origin, destination);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<FullResponseDTO>() {});
	}
	
	private String getUrl(Coordenate origin, Coordenate destination) 
	{
		String originLatitude = origin.getLatitude().toString();
		String originLongitude = origin.getLongitude().toString();
		String destinationLatitude = destination.getLatitude().toString();
		String destinationLongitude = destination.getLongitude().toString();
		return "https://maps.googleapis.com/maps/api/distancematrix/json?"
				+ "origins=" + originLatitude
				+ "," + originLongitude
				+ "&destinations=" + destinationLatitude
				+ "," + destinationLongitude
				+ "&mode=walking&language=es&key=AIzaSyCaiXB_SY0hgO5l3sLfXnx_L4lB2GRTWOQ";
	}
}
