package ar.edu.utn.dds.poi.exception;

public class InvalidPoiException extends Exception
{
	private static final long serialVersionUID = 1L;

	//Parameterless Constructor
	public InvalidPoiException() {}

	//Constructor that accepts a message
	public InvalidPoiException(String message)
	{
		super(message);
	}
	
    public InvalidPoiException (Throwable cause) 
    {
        super (cause);
    }

    public InvalidPoiException (String message, Throwable cause) 
    {
        super (message, cause);
    }
}
