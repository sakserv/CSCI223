package edu.csci233.mastermind;

import java.util.Random;

import edu.csci233.mastermind.Peg.Color;
import edu.csci233.mastermind.Peg.PegType;
import edu.csci233.mastermind.Row.RowType;

public class Game {
	
	public final static int BOARD_HEIGHT = 10;
	private int codeSize;
	private boolean allowDuplicates;
	private Row userRow;
	private Row codeRow;
	private Row responseRow;
	
	private Game() {
		super();
	}
	
	public static Game setupGame() {
		Game game = new Game();
		game.setAllowDuplicates(false);
		game.codeSize = 4;
		game.generateCodeRow();
		return game;
	}
	
	public int getCodeSize() {
		return codeSize;
	}
	
	public void setCodeSize(int codeSize) {
		this.codeSize = codeSize;
	}
	
	public boolean getAllowDuplicates() {
		return allowDuplicates;
	}
	
	public void setAllowDuplicates(boolean allowDuplicates) {
		this.allowDuplicates = allowDuplicates;
	}
	
	public Row getCodeRow() {
		return codeRow;
	}
	
	public void generateCodeRow() {
		codeRow = new Row();
		codeRow.setRowType(RowType.CODE);
		
		Peg[] codePegs = new Peg[codeRow.getWidth()];
		Random rand = new Random();
		for (int i = 0; i < codePegs.length; i++) {
			int pegColorIdx = rand.nextInt(Color.values().length);
			Peg peg = new Peg();
			peg.setPegType(PegType.LARGE);
			peg.setColor(Color.values()[pegColorIdx]);
			codePegs[i] = peg;
		}
		codeRow.populate(codePegs);
	}
	
	public Row getUserRow() {
		return userRow;
	}
	
	public void setUserRow(Row userRow) {
		this.userRow = userRow;
	}
	
	
	public boolean checkGuess(Row userRow, Row codeRow) {
		Peg[] userPegs = userRow.getPegs();
		Peg[] codePegs = codeRow.getPegs();
		
		for (int i = 0; i < userPegs.length; i++) {
			if (!userPegs[i].equals(codePegs[i])) {
				return false;
			}
		}
		return true;
	}

}
