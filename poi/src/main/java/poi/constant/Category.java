package poi.constant;

public enum Category 
{	
	FURNITURE("muebleria", 1000),
	HARDSTORE("ferreteria", 500), 
	NEWSSTAND("kiosko de diarios", 700),
	LIBRARY("libreria", 1200),
	BAZAAR("bazar", 100);
	
	String name;
	int distance;

    private Category(String name, int distance) 
	{
        this.name = name;
        this.distance = distance;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public int getDistance()
    {
    	return this.distance;
    }
}
