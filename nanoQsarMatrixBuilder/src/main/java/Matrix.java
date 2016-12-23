

/**
 * 
 */

/**
 * @author Wilson Melendez
 *
 */
public class Matrix 
{
	private static int M;             // number of rows
	 private static int N;             // number of columns
	 private static double[][] data;   // M-by-N array

	 // create M-by-N matrix of 0's
	 public Matrix(int M, int N) 
	 {
		 Matrix.M = M;
	     Matrix.N = N;
	     Matrix.data = new double[M][N];
	 }

	 // create matrix based on 2d array
	 public Matrix(double[][] data) 
	 {
	     M = data.length;
	     N = data[0].length;
	     Matrix.data = new double[M][N];
	     for (int i = 0; i < M; i++)
	          for (int j = 0; j < N; j++)
	               Matrix.data[i][j] = data[i][j];
	 }
	 
	 // Print matrix to standard output.
	 public static void show() 
	 {
		 for (int i = 0; i < M; i++) 
	        {
	            for (int j = 0; j < N; j++) 
	            {
	            	System.out.printf("%9.4f ", data[i][j]);
	            }
	                System.out.println();
	        } 
	 }

}
