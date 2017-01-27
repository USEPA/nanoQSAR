import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * This is a test of the Matrix.java class.
 * @author Wilson Melendez
 *
 */
public class MatrixTest {

	@Test
	public void testSetMx() 
	{
		int Mx = 10;
		Matrix.setMx(Mx);
		int Mx1 = Matrix.getMx();
		assertEquals(Mx, Mx1);
	}
	
	@Test
	public void testSetNx() 
	{
		int Nx = 20;
		Matrix.setNx(Nx);
		int Nx1 = Matrix.getNx();
		assertEquals(Nx, Nx1);
	}
	
	@Test
	public void testSetMy() 
	{
		int My = 15;
		Matrix.setMy(My);
		int My1 = Matrix.getMy();
		assertEquals(My, My1);
	}
	
	@Test
	public void testSetNy() 
	{
		int Ny = 25;
		Matrix.setNy(Ny);
		int Ny1 = Matrix.getNy();
		assertEquals(Ny, Ny1);
	}
	
	@Test
	public void testSetDataX()
	{
		double[][] arrX = {{1.0,2.3,5.3}, {0.7,3.8,7.9}, {4.2,6.8,9.3}};
		Matrix.setDataX(arrX);
		double[][] arrX1 = Matrix.getDataX();
		assertArrayEquals(arrX, arrX1);
	}
	
	@Test
	public void testSetDataY()
	{
		double[][] arrY = {{3.0,2.9,5.1}, {2.7,4.8,1.9}, {0.2,5.3,9.2}};
		Matrix.setDataY(arrY);
		double[][] arrY1 = Matrix.getDataY();
		assertArrayEquals(arrY, arrY1);
	}
	
}
