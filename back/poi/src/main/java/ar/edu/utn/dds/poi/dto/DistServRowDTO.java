package ar.edu.utn.dds.poi.dto;

import java.util.List;

public class DistServRowDTO 
{
	List<DistServElementDTO> elements;

	public List<DistServElementDTO> getElements() 
	{
		return elements;
	}

	public void setElements(List<DistServElementDTO> elements) 
	{
		this.elements = elements;
	}
}
