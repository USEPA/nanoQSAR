package datamine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to handle connections to the MySQL database server.
 * @author Wilson Melendez
 *
 */
public class ConnectionManager 
{	
	private final static Logger lOGGER = Logger.getLogger("nanoQSAR");
	
	public ConnectionManager()
	{
		// Nothing to initialize for this class.
	}
	
	/**
	 * This method loads and initializes the class for the specified driver.
	 * @author Wilson Melendez
	 * @return Nothing.
	 * @throws ClassNotFoundException  If driver could not be found, throw exception.
	 */
	public static void getDriver() throws ClassNotFoundException
	{
		try
		{
			Class.forName(DBUtil.getDriverName());
		}
		catch(ClassNotFoundException ex)
		{
			lOGGER.log(Level.SEVERE, "getDriver error: driver class " + DBUtil.getDriverName() + " was not found.", ex);
			throw ex;
		}
	}
	
	/**
	 * This method creates a connection and returns an object of type Connection.  It throws
	 * an exception if for whatever reason a connection could not be established with
	 * the remote server.
	 * @author Wilson Melendez
	 * @return  Connection
	 * @throws SQLException
	 */
	public static Connection createConnection() throws SQLException
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(DBUtil.getDatabaseUrl(), DBUtil.getUsername(), DBUtil.getPassword());					
		}
		catch(SQLException ex)
		{
			lOGGER.log(Level.SEVERE, "Connection error: a connection to the database was not established.", ex);	
			throw ex;
		}
		
		return conn;
	}

	/**
	 * This method instantiates a object of this class.  It then calls the getDriver and 
	 * createConnection methods of the class to get a driver and create a connection to the
	 * remote database server.  
	 * @author Wilson Melendez
	 * @return  Connection
	 * @throws ClassNotFoundException  It is thrown if not driver could be found.
	 * @throws SQLException  It is thrown whenever a connection to the server fails.
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{	
//		ConnectionManager connManager = new ConnectionManager();
		Connection conn = null;
		try
		{
			ConnectionManager.getDriver();		
			conn = ConnectionManager.createConnection();
		}
		catch(SQLException ex)
		{
			throw ex;
		}
		
		return conn;
	}
}
