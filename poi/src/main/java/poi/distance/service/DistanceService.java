package poi.distance.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import poi.distance.service.response.DTO.ElementDTO;
import poi.distance.service.response.DTO.FullResponseDTO;
import poi.distance.service.response.DTO.RowDTO;
import poi.domain.Coordenate;
import poi.json.JsonFactory;
import poi.web.service.HttpService;

public class DistanceService {

	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public Integer metersFromTo(Coordenate origin, Coordenate destination) {
		FullResponseDTO fullResponseDTO = getFullResponseOf(origin, destination);
		List<RowDTO> rows = fullResponseDTO.getRows();
		List<ElementDTO> elements = rows.get(0).getElements();
		return Integer.parseInt(elements.get(0).getDistance().get("value"));
	}
	
	private FullResponseDTO getFullResponseOf(Coordenate origin, Coordenate destination) {
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
