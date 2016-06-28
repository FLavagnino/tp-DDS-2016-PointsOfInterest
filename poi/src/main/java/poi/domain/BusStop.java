package poi.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.*;

import poi.constant.Service;

public class BusStop extends POI
{
	protected Integer busLine;
	List<BusStop> BusPoi = new ArrayList<BusStop>();
	
	protected void setBusLine(int busLine){
		this.busLine = busLine;
	}
	public int getBusLine(){
		return busLine;
	}
	
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
		// busca por numero de bondi / etiqueta
		if (BusPoi.contains(this.busLine))
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