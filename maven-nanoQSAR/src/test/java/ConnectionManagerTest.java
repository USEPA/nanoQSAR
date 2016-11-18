import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.Assert;

/**
 * This class checks connectivity to remote MySQL server.
 * @author Wilson Melendez
 *
 */
public class ConnectionManagerTest {

	
	
	@Before  // Unused.
	public void setUp()
	{
		
	}
	
	@After  // Unused.
	public void afterTest()
	{
		
	}
	
	/**
	 * This method checks whether the class for the driver was found.
	 * @author Wilson Melendez
	 */
	@Test
	public void getDriverTest() 
	{
		String driverName = "com.mysql.jdbc.Driver";
		boolean driverFound = true;
		try
		{
			Class.forName(driverName);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Driver was not found.");
			driverFound = false;
			assertTrue("Driver was not found", driverFound);
			
		}
	}
	
	/**
	 * This method checks whether a connection was created or not.
	 * @author Wilson Melendez
	 */
	@Test
	public void createConnectiontest()
	{		
		Connection conn = null;
		ConnectionManager connManager = new ConnectionManager();
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
			DBUtil.close(conn);
		}		
	}

}
