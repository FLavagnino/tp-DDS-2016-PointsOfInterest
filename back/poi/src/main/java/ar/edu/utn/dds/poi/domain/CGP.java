package ar.edu.utn.dds.poi.domain;

import java.util.List;
import javax.persistence.*;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.*;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.utils.LevenshteinDistance;

@Entity
@PrimaryKeyJoinColumn(name="poi_id")
public class CGP extends POI 
{
	protected String type;
	protected String services;
	protected String zone;
	protected List<ZoneCoordenate> zoneCoordenates;
	
	public CGP()
	{		
		type = "cgp";
	}
	
	public CGP(String name, Coordenate coordenate, String services, String tags) 
	{
		super(name, coordenate, tags);
		this.services = services;
		type = "cgp";
	}
	
	public CGP(String name, Coordenate coordenate, List<ZoneCoordenate> zoneCoordenates, String services, String tags) 
	{
		super(name, coordenate, tags);
		this.services = services;
		this.zoneCoordenates = zoneCoordenates;
		type = "cgp";
	}
	
	public void setServices(String services)
	{
		this.services = services;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public void setZoneCoordenates(List<ZoneCoordenate> zoneCoordenates)
	{
		this.zoneCoordenates = zoneCoordenates;
	}
	
	public void setZone(String zone)
	{
		this.zone = zone;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="cgp", cascade = CascadeType.ALL)
    @JsonManagedReference
	public List<ZoneCoordenate> getZoneCoordenates()
	{
		return zoneCoordenates;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public String getServices ()
	{
		return services;
	}

	public String getZone()
	{
		return zone;
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
		String[] servList = services.split(",");
		
		for(int i=0; i< servList.length ; i++)
		{
			if (servList[i].toLowerCase().contains(filter.toLowerCase()))
			{
				return true;	
			}
		}
	
		// Now we will try with Levenshtein for the name
		distance = LevenshteinDistance.distance(name.toLowerCase(), filter.toLowerCase());
		
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
		return inCGPZone(zoneCoordenates, poiFrom.getCoordenate());
	}
	
	private boolean inCGPZone(List<ZoneCoordenate> polyCoords, Coordenate poiCoord)
	{
		Coordinate[] coords = new Coordinate[polyCoords.size()];
		
		for(int i=0; i< polyCoords.size() ; i++)
		{
			ZoneCoordenate point = polyCoords.get(i);
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
