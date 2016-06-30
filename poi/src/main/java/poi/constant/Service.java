package poi.constant;

public enum Service 
{
	INCOMES("Rentas"),
	CAR_PLATES("Patentes"), 
	ABL("ABL"), 
	WEDDINGS("Casamientos"),
	NA("No aplica"),
	LEGAL_ADVICE("Asesoramiento Legal"),
	ACCOUNTING_ADVICE("Asesoramiento Contable");
	
	String name;
	int distance;

    private Service(String name) 
	{
        this.name = name;
    }
    
    public String getName()
    {
    	return this.name;
    }
}
