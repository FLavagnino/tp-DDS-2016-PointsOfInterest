package pointsOfInterest.distanceService.responseDTO;

import java.util.HashMap;

public class ElementDTO {

	private HashMap<String, String> distance;
	private HashMap<String, String> duration;
	private String status;
	
	public HashMap<String, String> getDistance() {
		return distance;
	}
	public void setDistance(HashMap<String, String> distance) {
		this.distance = distance;
	}
	public HashMap<String, String> getDuration() {
		return duration;
	}
	public void setDuration(HashMap<String, String> duration) {
		this.duration = duration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
