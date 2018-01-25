package datamine;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

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
//	public static Class getDriver(DBUtil dbUtil) throws ClassNotFoundException
//	{
//		try
//		{
//			String driverName = dbUtil.getDriverName();
//			return Class.forName(driverName);
//		}
//		catch(ClassNotFoundException ex)
//		{
////			lOGGER.log(Level.SEVERE, "getDriver error: driver class " + dbUtil.getDriverName() + " was not found.", ex);
//			throw ex;
//		}
//	}
	
	/**
	 * This method creates a connection and returns an object of type Connection.  It throws
	 * an exception if for whatever reason a connection could not be established with
	 * the remote server.
	 * @author Wilson Melendez
	 * @return  Connection
	 * @throws SQLException
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	public static Connection createConnection(DBUtil dbUtil, String keyFilename) throws SQLException, GeneralSecurityException, IOException
	{

//		String info = DBUtil.getDatabaseUrl()+"?user="+DBUtil.getUsername()+"&password="+DBUtil.getPassword()+"&key="+DBUtil.getPasswordKey()+"&useSSL=false";
//		return DriverManager.getConnection(info);
		
		Properties info = new Properties();
		info.put("user", dbUtil.getUsername());
		info.put("password", DBUtil.decrypt(dbUtil.getPassword(), new File(keyFilename)));
		info.put("useSSL", "false");
		return DriverManager.getConnection(dbUtil.getDatabaseUrl(), info);

//		return DriverManager.getConnection(DBUtil.getDatabaseUrl(), DBUtil.getUsername(), DBUtil.getPassword());

	}

	/**
	 * This method instantiates a object of this class.  It then calls the getDriver and 
	 * createConnection methods of the class to get a driver and create a connection to the
	 * remote database server.  
	 * @author Wilson Melendez
	 * @return  Connection
	 * @throws ClassNotFoundException  It is thrown if not driver could be found.
	 * @throws SQLException  It is thrown whenever a connection to the server fails.
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	public static Connection getConnection(DBUtil dbUtil, String keyFilename) throws SQLException, ClassNotFoundException, GeneralSecurityException, IOException
	{	
			
//		ConnectionManager.getDriver(dbUtil);		
		return ConnectionManager.createConnection(dbUtil, keyFilename);
		
	}
	
	/**
	 * This method instantiates a object of this class.  It then calls the getDriver and 
	 * createConnection methods of the class to get a driver and create a connection to the
	 * remote database server.  
	 * @author Wilson Melendez & Paul Harten
	 * @return  Connection
	 * @throws ClassNotFoundException  It is thrown if not driver could be found.
	 * @throws SQLException  It is thrown whenever a connection to the server fails.
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	public static Boolean testConnection(DBUtil dbUtil, String keyFilename) throws SQLException, ClassNotFoundException, GeneralSecurityException, IOException
	{	
		
		try	{
			
			if (createConnection(dbUtil, keyFilename)==null) return false;
			
		} catch(CommunicationsException ex) {
			
			return false;
			
		}
		
		return true;
	}
}
