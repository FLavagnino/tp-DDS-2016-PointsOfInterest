package ar.edu.utn.dds.poi.exception;

public class InvalidUserException extends Exception
{
	private static final long serialVersionUID = 2L;

	//Parameterless Constructor
	public InvalidUserException() {}

	//Constructor that accepts a message
	public InvalidUserException(String message)
	{
		super(message);
	}
	
    public InvalidUserException (Throwable cause) 
    {
        super (cause);
    }

    public InvalidUserException (String message, Throwable cause) 
    {
        super (message, cause);
    }
}
