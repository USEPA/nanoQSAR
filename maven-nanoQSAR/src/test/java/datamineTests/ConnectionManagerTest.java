package datamineTests;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import datamine.ConnectionManager;
import datamine.DBUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.SQLException;

/**
* This class checks connectivity to remote MySQL server.
* @author Wilson Melendez
*
*/
public class ConnectionManagerTest {
		
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	static String keyFilename = System.getProperty("user.dir") + "\\nanoQSAR.key";
//	ConnectionManager connManager = new ConnectionManager();
	
//	@BeforeClass
//	public static void setUpProperties() throws IOException, GeneralSecurityException
//	{
//		try
//		{
//			dbUtil = new DBUtil();
//			dbUtil.loadProperties(propFilename, keyFilename);
//		}
//		catch(GeneralSecurityException ex)
//		{
//			throw ex;
//		}
//	}
	
	/**
	 * This method checks whether the class for the driver was found.
	 * @author Wilson Melendez
	 * @throws ClassNotFoundException 
	 */
//	@Test
//	public void testGetDriver() {
//		
//		try
//		{
//			DBUtil dbUtil = new DBUtil();
//			dbUtil.loadProperties(propFilename);
//			Class driverClass = ConnectionManager.getDriver(dbUtil);
//		}
//		catch(ClassNotFoundException | IOException ex)
//		{
//			Assert.fail("Exception was thrown: " + ex);			
//		}
//
//	}

	
	/**
	 * This method checks whether a connection was created or not.
	 * @author Wilson Melendez
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	@Test
	public void testCreateConnection() throws GeneralSecurityException, IOException {
		
		Connection conn = null;
		try
		{
			DBUtil dbUtil = new DBUtil();
			dbUtil.loadProperties(propFilename, keyFilename);
			conn = ConnectionManager.createConnection(dbUtil, keyFilename);		
		}
		catch(SQLException ex)
		{
			Assert.assertNotNull(ex.getMessage(), conn);
		}
		catch(IOException ex)
		{
			Assert.assertNotNull(ex.getMessage(), conn);
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
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	@Test
	public void testGetConnection() throws GeneralSecurityException, IOException {
		
		Connection conn = null;
		try
		{
			DBUtil dbUtil = new DBUtil();
			dbUtil.loadProperties(propFilename, keyFilename);
			conn = ConnectionManager.getConnection(dbUtil, keyFilename);
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
