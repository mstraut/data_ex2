package a2;

import static org.junit.Assert.*;
import java.util.Arrays;



public class MatrixMult {

	/**
	 * Function to multiply matrices Assumes A and B are square and have the same
	 * n!
	 * 
	 * @param A - matrix A to multiply (first operand)
	 * @param B - matrix B to multiply (second operand)
	 ***/
	public static int[][] multiply(int[][] A, int[][] B) {
		int n = A.length;
		int[][] R = new int[n][n];
		/** base case **/
		if (n == 1)
			R[0][0] = A[0][0] * B[0][0];
		else {
			// initializes C's matrix's array positions, the first and second
			int[][] cFirst11 = new int[n / 2][n / 2]; // cFirst11 = [1][1] a 1x1 matrix. same for all
			int[][] cFirst12 = new int[n / 2][n / 2];
			int[][] cFirst21 = new int[n / 2][n / 2];
			int[][] cFirst22 = new int[n / 2][n / 2];
			int[][] cSecond11 = new int[n / 2][n / 2];
			int[][] cSecond12 = new int[n / 2][n / 2];
			int[][] cSecond21 = new int[n / 2][n / 2];
			int[][] cSecond22 = new int[n / 2][n / 2];
			
			// ***SPLITTING MATRIX***
			// splits A into four sections (values) from matrix
			split(A, cFirst11, 0, 0);
			split(A, cFirst12, 0, n / 2);
			split(A, cFirst21, n / 2, 0);
			split(A, cFirst22, n / 2, n / 2);
			// splits B into four sections (values) from matrix
			split(B, cSecond11, 0, 0);
			split(B, cSecond12, 0, n / 2);
			split(B, cSecond21, n / 2, 0);
			split(B, cSecond22, n / 2, n / 2);
			
			// multiplies matrix values
			int[][] cMult1 = multiply(add(cFirst11, cFirst22), add(cSecond11, cSecond22));
			int[][] cMult2 = multiply(add(cFirst21, cFirst22), cSecond11);
			int[][] cMult3 = multiply(cFirst11, sub(cSecond12, cSecond22));
			int[][] cMult4 = multiply(cFirst22, sub(cSecond21, cSecond11));
			int[][] cMult5 = multiply(add(cFirst11, cFirst12), cSecond22);
			int[][] cMult6 = multiply(sub(cFirst21, cFirst11), add(cSecond11, cSecond12));
			int[][] cMult7 = multiply(sub(cFirst12, cFirst22), add(cSecond21, cSecond22));

			// places above cMult values into C matrix values
			int[][] C11 = add(sub(add(cMult1, cMult4), cMult5), cMult7);
			int[][] C12 = add(cMult3, cMult5);
			int[][] C21 = add(cMult2, cMult4);
			int[][] C22 = add(sub(add(cMult1, cMult3), cMult2), cMult6);

			// joins matrix values into matrix, will be stored and returned in R
			join(C11, R, 0, 0);
			join(C12, R, 0, n / 2);
			join(C21, R, n / 2, 0);
			join(C22, R, n / 2, n / 2);
		}
		/** return result **/
		return R;
	}

	/**
	 * Function to subtract two matrices A and B
	 ***/
	public static int[][] sub(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] - B[i][j];
		return C;
	}

	/**
	 * Function to add two matrices A and B
	 ***/
	public static int[][] add(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}

	/**
	 * Function to split parent matrix into child matrices. Assumes C is
	 * initialized.
	 * 
	 * @param P  - parent matrix
	 * @param C  - A child matrix that will get the corresponding indices of the
	 *           parent
	 * @param iB - start row in parent
	 * @param jB - start column in parent
	 ***/
	public static void split(int[][] P, int[][] C, int iB, int jB) {
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
				C[i1][j1] = P[i2][j2];
	}

	/**
	 * Function to join child matrices into a parent matrix Assumes C is
	 * initialized.
	 * 
	 * @param P  - parent matrix
	 * @param C  - A child matrix that will provide the corresponding indices of the
	 *           parent
	 * @param iB - start row in parent
	 * @param jB - start column in parent
	 ***/
	public static void join(int[][] C, int[][] P, int iB, int jB) {
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
				P[i2][j2] = C[i1][j1];
	}
	
	public static void printMatrix(int[][] A) {
		int num = A.length;
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Ensure that multiplying the two matrices gives the correct result!
		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };

		int[][] C = multiply(A, B);
		
		// I changed this to use deepToString for this matrix
		System.out.println(Arrays.deepToString(C));
		System.out.println("Testing...");
		assertEquals(C[0][0], 19);
		assertEquals(C[0][1], 22);
		assertEquals(C[1][0], 43);
		assertEquals(C[1][1], 50);
		System.out.println("Success!");
	}

}
