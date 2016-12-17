import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
* This class checks connectivity to remote MySQL server.
* @author Wilson Melendez
*
*/
public class ConnectionManagerTest {
		
	Connection conn = null;
	ConnectionManager connManager = new ConnectionManager();
	
	@BeforeClass
	public static void setUpProperties() throws IOException
	{
		String filename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
		DBUtil.loadProperties(filename);
	}
	
	/**
	 * This method checks whether the class for the driver was found.
	 * @author Wilson Melendez
	 */
	@Test
	public void testGetDriver() {		
		try
		{
			connManager.getDriver();
		}
		catch(ClassNotFoundException ex)
		{
			Assert.fail("Exception was thrown: " + ex);			
		}		
	}

	
	/**
	 * This method checks whether a connection was created or not.
	 * @author Wilson Melendez
	 */
	@Test
	public void testCreateConnection() {
		try
		{		
			conn = connManager.createConnection();		
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
			assertFalse(conn == null);
		}
				
		assertTrue(conn != null);	
		if (conn != null)
		{
			try
			{
				DBUtil.close(conn);
			}
			catch(SQLException ex)
			{
				Assert.fail("Exception was thrown: " + ex);	
			}
		}		
	}

	
	/**
	 * @author Wilson Melendez
	 */
	@Test
	public void testGetConnection() {
		try
		{
			conn = ConnectionManager.getConnection();
		}
		catch(ClassNotFoundException ex)
		{
			Assert.fail("Exception was thrown: " + ex);
		}
		catch(SQLException ex)
		{
			Assert.fail("Exception was thrown: " + ex);
		}
		
		if (conn != null)
		{
			try
			{
				DBUtil.close(conn);
			}
			catch(SQLException ex)
			{
				Assert.fail("Exception was thrown: " + ex);
			}
		}		
	}

}
