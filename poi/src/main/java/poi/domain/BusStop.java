package poi.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.*;

import poi.constant.Service;

public class BusStop extends POI
{
	protected String name;
	protected Integer busLine;
	protected Coordenate coordenate;
	
	List<BusStop> BusPoi = new ArrayList<BusStop>();
	
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
		if (BusPoi.contains(Integer.parseInt(filter)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		// TODO: Completar este metodo y borrar el comentario.
		return true;
	}
}