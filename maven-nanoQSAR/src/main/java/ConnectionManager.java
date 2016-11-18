import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to handle connections to the MySQL database server.
 * @author Wilson Melendez
 *
 */
public class ConnectionManager 
{
	/* Static fields for name of driver, URL of database, username and password. */
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String dbaseURL = "jdbc:mysql://au.epa.gov:3306/dev_naknowbase_v1";
	private static String username = "app_naknowbase";
	private static String password = "WgeV8hN938eDJsZX";
	
	public ConnectionManager()
	{
		// Empty constructor.
	}
	
	/**
	 * This method loads and initializes the class for the specified driver.
	 * @author Wilson Melendez
	 * @return Nothing.
	 * @throws ClassNotFoundException  If driver could not be found, throw exception.
	 */
	public void getDriver() throws ClassNotFoundException
	{
		try
		{
			Class.forName(driverName);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Error: " + ex.getMessage());
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
	public Connection createConnection() throws SQLException
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(dbaseURL, username, password);
				
		}
		catch(SQLException ex)
		{
			System.out.println("Error: Unable to connect to database.");
			System.out.println("SQLException: " + ex.getMessage());	
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
		ConnectionManager connManager = new ConnectionManager();
		Connection conn = null;
		try
		{
			connManager.getDriver();		
			conn = connManager.createConnection();
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			throw ex;
		}
		catch(SQLException ex)
		{
			System.out.println("Error: " + ex.getMessage());
			throw ex;
		}
		
		return conn;
	}
}
