

/**
 * @author Wilson Melendez
 *
 */
public class Matrix 
{
	private static int mX;             // number of rows of X matrix
    private static int nX;             // number of columns of X matrix
	private static double[][] dataX;   // M-by-N array
	
	private static int mY;             // number of rows of Y matrix
    private static int nY;             // number of columns of Y matrix
	private static double[][] dataY;   // M-by-N array

	 /**
	 * @return the m
	 */
	public static int getMx() {
		return mX;
	}

	/**
	 * @param m the m to set
	 */
	public static void setMx(int m) {
		mX = m;
	}

	/**
	 * @return the n
	 */
	public static int getNx() {
		return nX;
	}

	/**
	 * @param n the n to set
	 */
	public static void setNx(int n) {
		nX = n;
	}

	/**
	 * @return the data
	 */
	public static double[][] getDataX() {
		return dataX;
	}

	/**
	 * @param data the data to set
	 */
	public static void setDataX(double[][] data) 
	{
		mX = data.length;      // Number of rows
		nX = data[0].length;   // Number of columns
	    dataX = new double[mX][nX];
	    
	    for (int i = 0; i < mX; i++)
	    {
	    	for (int j = 0; j < nX; j++)
	    	{
	    		dataX[i][j] = data[i][j];
	    	}	              
	    }	         
	}
	 
	 /**
	 * @return the my
	 */
	public static int getMy() {
		return mY;
	}

	/**
	 * @param my the my to set
	 */
	public static void setMy(int my) {
		mY = my;
	}

	/**
	 * @return the ny
	 */
	public static int getNy() {
		return nY;
	}

	/**
	 * @param ny the ny to set
	 */
	public static void setNy(int ny) {
		nY = ny;
	}

	/**
	 * @return the dataY
	 */
	public static double[][] getDataY() {
		return dataY;
	}

	/**
	 * @param dataY the dataY to set
	 */
	public static void setDataY(double[][] data) 
	{
		mY = data.length;      // Number of rows
	    nY = data[0].length;   // Number of columns
	    dataY = new double[mY][nY];
	    
	    for (int i = 0; i < mY; i++)
	    {
	    	for (int j = 0; j < nY; j++)
	         {
	        	 dataY[i][j] = data[i][j];
	         }
	    }	         	              
	}

	/**
	 * Print X matrix to standard output.
	 */
	 public static void showX() 
	 {
		 for (int i = 0; i < mX; i++) 
		 {
			 for (int j = 0; j < nX; j++) 
			 {
				 System.out.printf("%9.4f ", dataX[i][j]); 
			 }
	         System.out.println();
		 }	         
	 }
	  
	 /**
	  * Print Y matrix to standard output.
	  */
	 public static void showY() 
	 {
		 for (int i = 0; i < mY; i++) 
		 {
			 for (int j = 0; j < nY; j++) 
			 {
				 System.out.printf("%9.4f ", dataY[i][j]); 
			 }
			 System.out.println();
		 }
	 }

}
