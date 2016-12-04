import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Wmelende
 *
 */
public class IllegalUnitsExceptionTest {
	/**
	 * Test method for {@link IllegalUnitsException#IllegalUnitsException(java.lang.String)}.
	 */
	@Test(expected=IllegalUnitsException.class)
	public void testIllegalUnitsException() throws IllegalUnitsException
	{
		try 
		{
			String strUnits = "lb/ft3";
			verifyUnits(strUnits);
			
		}
		finally
		{
			System.out.println("Exception was thrown successfully.");
		}
  
	}

	private void verifyUnits(String str) throws IllegalUnitsException
	{
		
		if (!str.equals("mg/mL")) 
		{
			IllegalUnitsException excp = new IllegalUnitsException("An exception has been thrown.");
			throw excp;
		}
	}
	
	/**
	 * Test method for {@link IllegalUnitsException#getMessage()}.
	 */
	@Test
	public void testGetMessage() {
		IllegalUnitsException strEx = new IllegalUnitsException("This is a test.");
		String strMessage = strEx.getMessage();
		assertEquals("This is a test.", strMessage);
	}

}
