package pointsOfInterest;

import java.util.List;

import pointsOfInterest.distanceService.DistanceService;
import pointsOfInterest.distanceService.responseDTO.ElementDTO;
import pointsOfInterest.distanceService.responseDTO.FullResponseDTO;
import pointsOfInterest.distanceService.responseDTO.RowDTO;
import pointsOfInterest.position.Coordenate;

public class App {

	public static void main(String[] args) {
	
		//A modo de prueba
		Coordenate originCoordenate = new Coordenate(-34.605655, -58.454029);
		Coordenate destinationCoordenate = new Coordenate(-34.605187, -58.459587);
		
		DistanceService distanceService = new DistanceService();
		FullResponseDTO fullResponseDTO = distanceService.getFullResponseOf(originCoordenate, destinationCoordenate);
		List<String> originAddresses = fullResponseDTO.getOriginAddresses();
		List<String> destinationAddresses = fullResponseDTO.getDestinationAddresses();
		List<RowDTO> rows = fullResponseDTO.getRows();
		List<ElementDTO> elements = rows.get(0).getElements();

		System.out.println(originAddresses.get(0));
		System.out.println(destinationAddresses.get(0));
		System.out.println(	fullResponseDTO.getStatus());
		System.out.println(elements.get(0).getDistance().get("text"));
		System.out.println(elements.get(0).getDistance().get("value"));
		System.out.println(elements.get(0).getDuration().get("text"));
		System.out.println(elements.get(0).getDuration().get("value"));
	}

}
