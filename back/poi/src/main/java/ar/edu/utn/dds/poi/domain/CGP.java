package ar.edu.utn.dds.poi.domain;

import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.utils.LevenshteinDistance;

public class CGP extends POI 
{
	protected List<String> services;
	protected List<Coordenate> zoneCoord;
	
	public CGP()
	{		
	}
	
	public CGP(String name, Coordenate coordenate, List<String> services, String tags) 
	{
		super(name, coordenate, tags);
		this.services = services;
	}
	
	public CGP(String name, Coordenate coordenate, List<Coordenate> zoneCoord, List<String> services, String tags) 
	{
		super(name, coordenate, tags);
		this.services = services;
		this.zoneCoord = zoneCoord;
	}
	
	public void setServices (List<String> services)
	{
		this.services = services;
	}
		
	public List<Coordenate> getZoneCoord()
	{
		return this.zoneCoord;
	}
	
	public boolean isAvailable(DateTime dateTime, String service)
	{
		int dayOfWeek = dateTime.getDayOfWeek();
		
		for (OpeningHour openingHour : openingHours) 
		{
			int distance = LevenshteinDistance.distance(service.toLowerCase(), openingHour.getService().toLowerCase());
			
			if (distance < Constant.LEVENSHTEIN_ACCEPTED_DIST)
			{				
				if (openingHour.getDayOfWeek() == dayOfWeek)
				{
					DateTime from = new DateTime(Constant.JODATIME_COMPARE_YEAR, Constant.JODATIME_COMPARE_MONTH, 
													Constant.JODATIME_COMPARE_DAY, openingHour.getHoursFrom(), 
													openingHour.getMinutesFrom());
					
					DateTime to = new DateTime(Constant.JODATIME_COMPARE_YEAR, Constant.JODATIME_COMPARE_MONTH, 
													Constant.JODATIME_COMPARE_DAY, openingHour.getHoursTo(), 
													openingHour.getMinutesTo());
					
					Interval interval = new Interval(from, to);
					DateTime avaTime = new DateTime (Constant.JODATIME_COMPARE_YEAR, Constant.JODATIME_COMPARE_MONTH,
														Constant.JODATIME_COMPARE_DAY, dateTime.getHourOfDay(), dateTime.getMinuteOfHour());
					
					if (interval.contains(avaTime))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean matchFilter(String filter)
	{		
		// Now we will try with Categories
		int distance = 0;	
		for(int i=0; i< this.services.size() ; i++)
		{
			if (this.services.get(i).toLowerCase().contains(filter.toLowerCase()))
			{
				return true;	
			}
		}
	
		// Now we will try with Levenshtein for the name
		distance = LevenshteinDistance.distance(this.name.toLowerCase(), filter.toLowerCase());
		
		if (distance < Constant.LEVENSHTEIN_ACCEPTED_DIST)
		{
			return true;
		}

		// Now we will try with Levenshtein for the tags
		if (!tags.equals(null) && !tags.equals(""))
		{
			String[] tagList = tags.split(",");
			
			for (int i=0; i < tagList.length; i++)
			{
				distance = LevenshteinDistance.distance(tagList[i].toLowerCase(), filter.toLowerCase());
				
				if (distance < Constant.LEVENSHTEIN_ACCEPTED_DIST)
				{
					return true;
				}
			}		
		}

		// Doesn't match anything
		return false;
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		return this.inCGPZone(this.zoneCoord, poiFrom.getCoordenate());
	}
	
	private boolean inCGPZone(List<Coordenate> polyCoords, Coordenate poiCoord)
	{
		Coordinate[] coords = new Coordinate[polyCoords.size()];
		
		for(int i=0; i< polyCoords.size() ; i++)
		{
			Coordenate point = polyCoords.get(i);
			Coordinate coord = new Coordinate(point.getLatitude(), point.getLongitude());
			
			coords[i] = coord;
		}
		
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		
		LinearRing ring = geometryFactory.createLinearRing( coords );
		LinearRing holes[] = null; // use LinearRing[] to represent holes
		Polygon polygon = geometryFactory.createPolygon(ring, holes );
		
		com.vividsolutions.jts.geom.Point point = geometryFactory.createPoint(new Coordinate(poiCoord.getLatitude(), 
																				poiCoord.getLongitude()));
		boolean result = polygon.contains(point);
		
		return result;
	}
}
