package ar.edu.utn.dds.poi.connector;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.model.distanceServiceDTO.DistServFullResponseDTO;
import ar.edu.utn.dds.poi.utils.JsonFactory;

public class GoogleDistance 
{	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();

	public DistServFullResponseDTO getFullResponse(Coordenate origin, Coordenate destination) 
	{
		String url = getUrl(origin, destination);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), 
										new TypeReference<DistServFullResponseDTO>() {});
	}
	
	private String getUrl(Coordenate origin, Coordenate destination) 
	{
		String originLatitude = origin.getLatitude().toString();
		String originLongitude = origin.getLongitude().toString();
		String destinationLatitude = destination.getLatitude().toString();
		String destinationLongitude = destination.getLongitude().toString();
		return String.format(Constant.GOOGLE_DIST_URL, originLatitude, originLongitude, 
								destinationLatitude, destinationLongitude);
	}
}