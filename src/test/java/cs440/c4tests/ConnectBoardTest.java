package cs440.c4tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cs440.c4.ConnectBoard;

public class ConnectBoardTest {

	/**
	 * Tests to make sure it can create an empty board and can get the empty board.
	 */
	@Test
	public void constructorAndGetBoardTest() {
		ConnectBoard c = new ConnectBoard(6,7); //6 rows, 7 columns
		int[][] test = c.getBoard();
		for(int i = 0; i < test.length; i++) {
			for(int j = 0; j < test[i].length; j++) {
				if(test[i][j] != 0) {
					fail("Did not create and initialize an empty board.");
				}
				
			}
		}
	}
	
	@Test
	public void addDiskTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		//Full column. Tests to make sure it is in the correct row while going through.
		int test = 0;
		test = c.addDisk(0, 1);
		assertTrue(test == 0);
		test = c.addDisk(0, 1);
		assertTrue(test == 1);
		test = c.addDisk(0, 1);
		assertTrue(test == 2);
		test = c.addDisk(0, 1);
		assertTrue(test == 3);
		test = c.addDisk(0, 1);
		assertTrue(test == 4);
		test = c.addDisk(0, 1);
		assertTrue(test == 5);
		
		//Full row
		test = c.addDisk(1, -1);
		assertTrue(test == 0);
		test = c.addDisk(2, -1);
		assertTrue(test == 0);
		test = c.addDisk(3, -1);
		assertTrue(test == 0);
		test = c.addDisk(4, -1);
		assertTrue(test == 0);
		test = c.addDisk(5, -1);
		assertTrue(test == 0);
		test = c.addDisk(6, -1);
		assertTrue(test == 0);

		//Gets the board
		int[][] testA = c.getBoard();
		
		//Tests to make sure the column is filled.
		for(int i = 0; i < testA.length; i++) {
			if(testA[i][0] != 1) {
				fail("Did not fill first column!");
			}
		}
		
		//Tests to make sure the first row is filled.
		for(int i = 1; i < testA[0].length; i++) {
			if(testA[0][i] != -1) {
				fail("Did not fill first row!");
			}
		}
	}
	
	@Test
	public void connectedVerticalTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		
		assertTrue(c.connected() == 1);
		assertTrue(c.gameOver());
	}
	
	@Test
	public void connectedVerticalCloseToBoundaryTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(0, -1);
		assertFalse(c.gameOver());
		c.addDisk(0, -1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		
		assertTrue(c.connected() == 1);
		assertTrue(c.gameOver());
	}
	
	@Test
	public void connectedHorizontalTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(1, 1);
		assertFalse(c.gameOver());
		c.addDisk(2, 1);
		assertFalse(c.gameOver());
		c.addDisk(3, 1);
		
		assertTrue(c.connected() == 1);
		assertTrue(c.gameOver());
	}
	
	@Test
	public void connectedHorizontalCloseToBoaundTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(3, 1);
		assertFalse(c.gameOver());
		c.addDisk(4, 1);
		assertFalse(c.gameOver());
		c.addDisk(5, 1);
		assertFalse(c.gameOver());
		c.addDisk(6, 1);
		
		assertTrue(c.connected() == 1);
		assertTrue(c.gameOver());
	}
	
	
	/**
	 * Representation of what the board looks like.
	 *    *
	 *   *-
	 *  *--
	 * *---
	 */
	@Test
	public void connectedRightAndUpTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(0, 1);
		assertFalse(c.gameOver());
		c.addDisk(1, -1);
		assertFalse(c.gameOver());
		c.addDisk(1, 1);
		assertFalse(c.gameOver());
		c.addDisk(2, -1);
		assertFalse(c.gameOver());
		c.addDisk(2, -1);
		assertFalse(c.gameOver());
		c.addDisk(2, 1);
		assertFalse(c.gameOver());
		c.addDisk(3, -1);
		assertFalse(c.gameOver());
		c.addDisk(3, -1);
		assertFalse(c.gameOver());
		c.addDisk(3, -1);
		assertFalse(c.gameOver());
		c.addDisk(3, 1);
		
		assertTrue(c.connected() == 1);
		assertTrue(c.gameOver());
	}
	
	/**
	 * Representation of what the board looks like.
	 * *
	 * -*
	 * --*
	 * ---*
	 */
	@Test
	public void connectedLeftAndDownTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(3, 1);
		assertFalse(c.gameOver());
		c.addDisk(2, -1);
		assertFalse(c.gameOver());
		c.addDisk(2, 1);
		assertFalse(c.gameOver());
		c.addDisk(1, -1);
		assertFalse(c.gameOver());
		c.addDisk(1, -1);
		assertFalse(c.gameOver());
		c.addDisk(1, 1);
		assertFalse(c.gameOver());
		c.addDisk(0, -1);
		assertFalse(c.gameOver());
		c.addDisk(0, -1);
		assertFalse(c.gameOver());
		c.addDisk(0, -1);
		assertFalse(c.gameOver());
		c.addDisk(0, 1);
		
		assertTrue(c.connected() == 1);
		assertTrue(c.gameOver());
	}

	@Test
	public void boardFullGameOverTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(0, 1);
		c.addDisk(0, 1);
		c.addDisk(0, -1);
		c.addDisk(0, 1);
		c.addDisk(0, 1);
		c.addDisk(0, -1);
		
		c.addDisk(1, 1);
		c.addDisk(1, -1);
		c.addDisk(1, -1);
		c.addDisk(1, 1);
		c.addDisk(1, -1);
		c.addDisk(1, 1);
		
		c.addDisk(2, -1);
		c.addDisk(2, -1);
		c.addDisk(2, 1);
		c.addDisk(2, -1);
		c.addDisk(2, 1);
		c.addDisk(2, 1);
		
		c.addDisk(3, 1);
		c.addDisk(3, -1);
		c.addDisk(3, 1);
		c.addDisk(3, -1);
		c.addDisk(3, 1);
		c.addDisk(3, 1);
		
		c.addDisk(4, -1);
		c.addDisk(4, 1);
		c.addDisk(4, -1);
		c.addDisk(4, 1);
		c.addDisk(4, -1);
		c.addDisk(4, -1);
		
		c.addDisk(5, -1);
		c.addDisk(5, 1);
		c.addDisk(5, -1);
		c.addDisk(5, 1);
		c.addDisk(5, -1);
		c.addDisk(5, -1);
		
		c.addDisk(6, 1);
		c.addDisk(6, 1);
		c.addDisk(6, -1);
		c.addDisk(6, 1);
		c.addDisk(6, -1);
		assertFalse(c.gameOver());
		c.addDisk(6, -1);
		assertTrue(c.gameOver());
	}
	
	@Test
	public void columnFullTest() {
		ConnectBoard c = new ConnectBoard(6,7);
		c.addDisk(0, 1);
		assertFalse(c.isColumnFull(0));
		c.addDisk(0, 1);
		assertFalse(c.isColumnFull(0));
		c.addDisk(0, 1);
		assertFalse(c.isColumnFull(0));
		c.addDisk(0, 1);
		assertFalse(c.isColumnFull(0));
		c.addDisk(0, 1);
		assertFalse(c.isColumnFull(0));
		c.addDisk(0, 1);
		assertTrue(c.isColumnFull(0));
	}
}
