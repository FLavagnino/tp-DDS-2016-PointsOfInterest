package ar.edu.utn.dds.poi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.DateTime;
import static java.util.Comparator.comparing;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class POI 
{
	protected Long id;
	protected String name;
	protected Integer unit;
	protected Coordenate coordenate;
	protected Address address;
	protected List<OpeningHour> openingHours;
	protected String tags;
	
	protected POI()
	{
		this.openingHours = new ArrayList<OpeningHour>();
	}
	
	protected POI(String name, Coordenate coordenate, String tags) 
	{
		this.name = name;
		this.coordenate = coordenate;
		this.openingHours = new ArrayList<OpeningHour>();
		this.tags = tags;
	}

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true , nullable = false )
    public Long getId() 
    {
            return this.id;
    }
 
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="poi", cascade = CascadeType.ALL)
    @JsonManagedReference
	public List<OpeningHour> getOpeningHours()
	{
    	Collections.sort(this.openingHours, comparing(OpeningHour::getService));
		return this.openingHours;
	}
		
	public String getName() 
	{
		return name;
	}
		
	public Integer getUnit()
	{
		return unit;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "coordenate_id", referencedColumnName = "id")
	@JsonManagedReference
	public Coordenate getCoordenate() 
	{
		return coordenate;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@JsonManagedReference
	public Address getAddress() 
	{
		return address;
	}

	public String getTags() 
	{
		return tags;
	}
	
    public void setId(Long id)
    {
    	this.id = id;
    }
    
	public void setAddress(Address address) 
	{
		this.address = address;
	}

	public void setOpeningHours(List<OpeningHour> openingHours)
	{
		this.openingHours = openingHours;
	}
    
	public void setAddress(String street, Integer number, String streetOne, String streetTwo, Integer floor, 
			String apartment, String postalCode, String locality, String neighborhood, String province, String country) 
	{	
		this.address = new Address(street, number, streetOne, streetTwo, floor, apartment, 
				postalCode, locality, neighborhood, province, country);
	}
	
	public void setCoordenate(Coordenate coordenate)
	{
		this.coordenate = coordenate;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setTags(String tags) 
	{
		this.tags = tags;
	}
	
	public void setUnit(Integer unit)
	{
		this.unit = unit;
	}
	
	// Methods
	public boolean isAvailable(DateTime dateTime, String service)
	{
		return true;
	}
	
	public boolean matchFilter(String filter)
	{
		return true;
	}
	
	public void addOpeningHour(OpeningHour openingHour)
	{
		this.openingHours.add(openingHour);
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		return true;
	}

	public String getType()
	{
		return this.getType();
	}

	public void setType(String type)
	{
		this.setType(type);
	}
}
