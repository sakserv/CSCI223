package edu.csci233.mastermind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.csci233.mastermind.Peg.Color;
import edu.csci233.mastermind.Peg.PegType;

public class GameTest {

	Game game;
	
	@Before
	public void setUp() throws Exception {
		game = Game.setupGame();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBoardHeightIsTen() {
		assertEquals(10, game.BOARD_HEIGHT);
	}
	
	@Test
	public void testCodeSizeIsFourByDefault() {
		assertEquals(4, game.getCodeSize());
	}
	
	@Test
	public void testDuplicatesNotAllowedByDefault() {
		assertFalse(game.getAllowDuplicates());
	}
	
	@Test
	public void testSetupCodeRow() {
		game.generateCodeRow();
		Row codeRow = game.getCodeRow();
		Peg[] pegs = codeRow.getPegs();
		
		for (int i = 0; i < pegs.length; i++) {
			assertTrue(pegs[i] instanceof Peg);
		}
	}
	
	@Test
	public void testCheckGuess() {
		
		// Mock up the user response
		Row userRow = new Row();
		Peg peg1 = new Peg();
		Peg peg2 = new Peg();
		Peg peg3 = new Peg();
		Peg peg4 = new Peg();
		
		Peg[] userPegs = new Peg[userRow.getWidth()];
		
		peg1.setPegType(PegType.LARGE);
		peg2.setPegType(PegType.LARGE);
		peg3.setPegType(PegType.LARGE);
		peg4.setPegType(PegType.LARGE);
		
		peg1.setColor(Color.RED);
		peg2.setColor(Color.RED);
		peg3.setColor(Color.RED);
		peg4.setColor(Color.RED);
		
		userPegs[0] = peg1;
		userPegs[1] = peg2;
		userPegs[2] = peg3;
		userPegs[3] = peg4;
		
		userRow.populate(userPegs);
		game.setUserRow(userRow);
		userRow = game.getUserRow();
		
		// Mock up a code row
		Row codeRow = new Row();
		Peg[] pegs = new Peg[game.getCodeSize()];
		for (int i = 0; i < pegs.length; i++) {
			Peg peg = new Peg();
			peg.setPegType(PegType.LARGE);
			peg.setColor(Color.RED);
			pegs[i] = peg;
		}
		codeRow.populate(pegs);
		
		// Assert they are the same
		assertTrue(game.checkGuess(game.getUserRow(), codeRow));
	}

}
