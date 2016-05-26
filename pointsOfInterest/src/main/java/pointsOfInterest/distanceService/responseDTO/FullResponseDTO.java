package pointsOfInterest.distanceService.responseDTO;

import java.util.List;

public class FullResponseDTO {

	private List<String> destinationAddresses;
	private List<String> originAddresses;
	private List<RowDTO> rows;
	private String status;

	public List<String> getDestinationAddresses() {
		return destinationAddresses;
	}
	public void setDestinationAddresses(List<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}
	public List<String> getOriginAddresses() {
		return originAddresses;
	}
	public void setOriginAddresses(List<String> originAddresses) {
		this.originAddresses = originAddresses;
	}
	public List<RowDTO> getRows() {
		return rows;
	}
	public void setRows(List<RowDTO> rows) {
		this.rows = rows;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
