package ar.edu.utn.dds.poi.model.distanceServiceDTO;

import java.util.List;

public class DistServFullResponseDTO {

	private List<String> destinationAddresses;
	private List<String> originAddresses;
	private List<DistServRowDTO> rows;
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
	public List<DistServRowDTO> getRows() {
		return rows;
	}
	public void setRows(List<DistServRowDTO> rows) {
		this.rows = rows;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
