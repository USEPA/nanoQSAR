/**
 * 
 */

/**
 * This class defines a user-defined exception.  In this case we are defining an illegal
 * units exception. IL will be thrown every time a non-appropriate unit is found in one of 
 * the fields.
 * @author Wilson Melendez
 *
 */
public class IllegalUnitsException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String message;
	
	/**
	 * This is a constructor.  It stores the exception message.
	 * @author Wilson Melendez
	 * @param message
	 */
	public IllegalUnitsException(String message)
	{
		this.message = message;
	}
	
	/**
	 * This method returns the exception message.
	 * @author Wilson Melendez
	 */
	public String getMessage()
	{
		return message;
	}

}
