package poi.constant;

public enum Categories 
{
	
	FURNITURE("muebleria"), HARDSTORE("ferreteria"), NEWSSTAND("kiosko de diarios"), 
	
	LIBRARY("libreria"), BAZAAR("bazar");
	
	 String name;

    Categories(String name) {
        this.name = name;
    }
	
}
