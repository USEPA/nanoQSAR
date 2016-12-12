import static org.junit.Assert.*;
import org.junit.Assert;
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
	
	/**
	 * This method checks whether the class for the driver was found.
	 * @author Wilson Melendez
	 */
	@Test
	public void testGetDriver() {
		
		try
		{
			String filename = System.getProperty("user.dir") + "\\properties.txt";
			DBUtil.loadProperties(filename);
			ConnectionManager connManager = new ConnectionManager();
			connManager.getDriver();
		}
		catch(ClassNotFoundException ex)
		{
			Assert.fail("Exception was thrown: " + ex);			
		}
		catch(IOException ex)
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
		Connection conn = null;
		ConnectionManager connManager = new ConnectionManager();
		try
		{		
			String filename = System.getProperty("user.dir") + "\\properties.txt";
			DBUtil.loadProperties(filename);
			conn = connManager.createConnection();		
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
			assertFalse(conn == null);
		}
		catch(IOException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
		
		assertTrue(conn != null);	
		if (conn != null)
		{
			DBUtil.close(conn);
		}		
	}

	@Test
	public void testGetConnection() {
		Connection conn = null;
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
			DBUtil.close(conn);
		}		
	}

}
