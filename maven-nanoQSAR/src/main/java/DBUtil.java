/**
 * 
 */

/**
 * @author Wmelende
 *
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to call static methods that perform general purpse tasks such as
 * closing Java connector related objects and printing out selected items to the screen.
 * @author Wilson Melendez
 * 
 */
public class DBUtil 
{
	/**
	 * @author Wilson Melendez
	 * @param connection It is used to establish connection to remote MySQL server.
	 */
	public static void close(Connection connection)
	{
		if (connection != null)
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	/**
	 * @author Wilson Melendez
	 * @param statement
	 */
	public static void close(Statement statement)
	{
		if (statement != null)
		{
			try
			{
				statement.close();
			}
			catch(SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param resultset It is an object used to hold results returned by remote MySQL server.
	 */
	public static void close(ResultSet resultset)
	{
		if (resultset != null)
		{
			try
			{
				resultset.close();
			}
			catch(SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	
	/**
	 * This methods prints out header information to the console.  It is only used 
	 * to spot check selected items.
	 * @author Wilson Melendez
	 * @param None
	 * @return Nothing.
	 */
	public static void displayMetaData()
	{
		String col1 = "ORDMaterialID";
		String col2 = "DataSource";
		String col3 = "AssayName";
		String col4 = "SampleName";
		String col5 = "CellType";
		String col6 = "ParticleConcentration";
		String col7 = "ParticleExposDuration";
		String col8 = "LC50";
		System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s \n", col1, col2, col3, col4, col5, col6, col7, col8);
	}
	
	/**
	 * This method prints out selected fields of the NanoMaterial class.  It is only used 
	 * to spot check selected fields.
	 * @author Wilson Melendez
	 * @param nanomaterial  It is an object of type NanoMaterial.  It contains a row of data.
	 * @return Nothing.
	 */
	public static void displayNanoMaterial(NanoMaterial nanomaterial)
	{
		String strA = "                   ";
		String strB = "                   ";
		
		String str1 = nanomaterial.getOrdMaterialID();
		String str2 = nanomaterial.getDataSource();
		String str3 = nanomaterial.getAssayName();
		String str4 = nanomaterial.getSampleName();
		String str5 = nanomaterial.getCellType();
		double d1 = nanomaterial.getParticleConcentration();
		double d2 = nanomaterial.getParticleExposDuration();
		String d3 = String.valueOf(nanomaterial.getLc50());
		
		// Display values
		System.out.printf("%-25s %-25s %-25s %-25s %-25s %-5.1f %-19s %-5.2f %-19s %-4s \n", str1, str2, str3, str4, str5, d1, strA, d2, strB, d3);

	}
}
