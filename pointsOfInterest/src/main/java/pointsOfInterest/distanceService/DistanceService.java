package pointsOfInterest.distanceService;

import com.fasterxml.jackson.core.type.TypeReference;

import pointsOfInterest.distanceService.responseDTO.FullResponseDTO;
import pointsOfInterest.json.JsonFactory;
import pointsOfInterest.position.Coordenate;
import pointsOfInterest.web.service.HttpService;

public class DistanceService {

	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public FullResponseDTO getFullResponseOf(Coordenate origin, Coordenate destination) {
		String url = getUrl(origin, destination);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<FullResponseDTO>() {});
	}
	
	private String getUrl(Coordenate origin, Coordenate destination) {
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
