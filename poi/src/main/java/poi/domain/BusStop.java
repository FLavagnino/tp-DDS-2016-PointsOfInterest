package poi.domain;

import org.joda.time.*;

import poi.constant.Service;

public class BusStop extends POI
{
	public BusStop(String name, Coordenate coordenate) 
	{
		super(name, coordenate);
	}
	
	public boolean isAvailable(DateTime dateTime, Service service)
	{
		return true;
	}
	
	public boolean matchFilter(String filter)
	{
		// Aca buscas por numero de bondi / etiqueta
		return true;
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		// TODO: Completar este metodo y borrar el comentario.
		return true;
	}
}