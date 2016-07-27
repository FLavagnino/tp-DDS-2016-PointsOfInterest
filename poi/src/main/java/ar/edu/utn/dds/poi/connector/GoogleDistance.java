package ar.edu.utn.dds.poi.connector;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.model.distanceServiceDTO.DistServFullResponseDTO;
import ar.edu.utn.dds.poi.utils.JsonFactory;

public class GoogleDistance {

	private final static String URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s,%s&destinations=%s,%s&mode=walking&language=es&key=AIzaSyCaiXB_SY0hgO5l3sLfXnx_L4lB2GRTWOQ";
	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();

	public DistServFullResponseDTO getFullResponse(Coordenate origin, Coordenate destination) {
		String url = getUrl(origin, destination);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<DistServFullResponseDTO>() {});
	}
	
	private String getUrl(Coordenate origin, Coordenate destination) {
		String originLatitude = origin.getLatitude().toString();
		String originLongitude = origin.getLongitude().toString();
		String destinationLatitude = destination.getLatitude().toString();
		String destinationLongitude = destination.getLongitude().toString();
		return String.format(URL, originLatitude, originLongitude, destinationLatitude, destinationLongitude);
	}
}
