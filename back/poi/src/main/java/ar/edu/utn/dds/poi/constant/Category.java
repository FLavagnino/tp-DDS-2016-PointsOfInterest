package ar.edu.utn.dds.poi.constant;

public enum Category 
{		
	FURNITURE("muebleria", 1000),
	HARDSTORE("ferreteria", 500), 
	NEWSSTAND("kiosko de diarios", 700),
	LIBRARY("libreria", 1200),
	BAZAAR("bazar", 100);
	
	static int furnitureDistance = 1000;
	static int hardstoreDistance = 500;
	static int newstandDistance = 700;
	static int libraryDistance = 1200;
	static int bazaarDistance = 100;
	
	String name;
	int distance;

    private Category(String name, int distance) 
	{
        this.name = name;
        this.distance = distance;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public int getDistance()
    {
    	return distance;
    }
}
