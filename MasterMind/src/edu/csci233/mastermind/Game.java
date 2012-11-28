package edu.csci233.mastermind;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import edu.csci233.mastermind.Peg.Color;
import edu.csci233.mastermind.Peg.PegType;
import edu.csci233.mastermind.Row.RowType;

public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public final static int BOARD_HEIGHT = 10;
	private int codeSize;
	private Row codeRow;
	private Row responseRow;
	private Row[] gameState;
	private Row[] responseState;
	private int activeRowId;
	private boolean codeRowHidden;
	private boolean gameOver = false;
	
	private Game() {
		super();
	}
	
	public static Game setupGame() {
		Game game = new Game();
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
	
	public Row getCodeRow() {
		return codeRow;
	}
	
	public void setCodeRow(Row codeRow) {
		this.codeRow = codeRow;
	}
	
	public void generateCodeRow() {
		codeRow = new Row();
		codeRow.setRowType(RowType.CODE);
		
		Peg[] codePegs = new Peg[getCodeSize()];
		Random rand = new Random();
		
		main:
			for (int i = 0; i < codePegs.length; i++) {
				int pegColorIdx = rand.nextInt(Color.values().length);
				Peg peg = new Peg();
				peg.setPegType(PegType.LARGE);
				peg.setColor(Color.values()[pegColorIdx]);
				
				if(peg.getColor() == Color.NONE) {
					i--;
					continue;
				}
			
				for(int j = 0; j < codePegs.length - 1; j++) {
					if(codePegs[j] != null) {
						if(codePegs[j].getColor().equals(peg.getColor())) {
							i--;
							continue main;
						}
					}
				}
				codePegs[i] = peg;
			}
		codeRow.populate(codePegs);
	}
	
	public void setResponseRow(Row userRow) {
		responseRow = new Row();
		Peg[] responsePegs = new Peg[getCodeSize()];
		Peg[] userPegs = userRow.getPegs();
		Peg[] codePegs = new Peg[getCodeSize()];
		System.arraycopy(codeRow.getPegs(), 0, codePegs, 0, codeRow.getPegs().length);
		
		// Correct answer, all response pegs red
	//	if(checkGuess(userRow)) {
	//		for(int i = 0; i < getCodeSize(); i++) {
	//			responsePegs[i] = new Peg();
	//			responsePegs[i].setColor(Color.RED);
	//			responsePegs[i].setPegType(PegType.SMALL);
	//		}
	//		responseRow.populate(responsePegs);
	//	} else {
			
			
			
			System.out.println("INCORRECT ANSWER");
			System.out.print("YOUR ANSWER: ");
			for(Peg peg : userPegs) {
				System.out.print(peg.getColor().toString() + " ");
			}
			System.out.println();
			
			System.out.print("CORRECT: ");
			for(Peg peg : codePegs) {
				System.out.print(peg.getColor().toString() + " ");
			}
			System.out.println();
			
			
			Color[] colorsUsed = new Color[getCodeSize()];
			// Setup correct color/pos first
			int redCount = 0;
			
			outer:
			for(int i = 0; i < userPegs.length; i++) {
				if(userPegs[i].equals(codePegs[i])) {
					responsePegs[redCount] = new Peg();
					responsePegs[redCount].setColor(Color.RED);
					responsePegs[redCount].setPegType(PegType.SMALL);
					colorsUsed[redCount] = userPegs[i].getColor();
					redCount++;
				} else {
					for(int j = 0; j < userPegs.length; j++) {
						
						if(userPegs[i].equals(codePegs[j])) {
						
							for(int k = 0; k < colorsUsed.length; k++) {
								if(userPegs[i].getColor().equals(colorsUsed[k])) {
									continue outer;
								}
							}
							responsePegs[redCount] = new Peg();
							responsePegs[redCount].setColor(Color.WHITE);
							responsePegs[redCount].setPegType(PegType.SMALL);
							colorsUsed[redCount] = userPegs[i].getColor();
							redCount++;
						}
					}
				}
			}
			
			
			for(int i = 0; i < userPegs.length; i++) {
				if(responsePegs[i] == null) {
					responsePegs[i] = new Peg();
					responsePegs[i].setColor(Color.NONE);
					responsePegs[i].setPegType(PegType.SMALL);
				}
			}
			responseRow.populate(responsePegs);
			
		responseState[activeRowId] = responseRow;
	}
	
	public Row getReponseRow() {
		return responseRow;
	}
	
	public boolean checkGuess(Row userRow) {
		Peg[] userPegs = userRow.getPegs();
		Peg[] codePegs = codeRow.getPegs();
		
		for (int i = 0; i < userPegs.length; i++) {
			if (!userPegs[i].equals(codePegs[i])) {
				return false;
			}
		}
		gameOver = true;
		return true;
	}
	
	public void initGameState() {
		gameState = new Row[BOARD_HEIGHT];
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			gameState[i] = new Row();
		}
		
		for(int i = 0; i < gameState.length; i++) {
			Peg[] pegs = new Peg[getCodeSize()];
			for(int j = 0; j < getCodeSize(); j++) {
				pegs[j] = new Peg();
				pegs[j].setColor(Color.NONE);
				pegs[j].setPegType(PegType.LARGE);
			}
			gameState[i].populate(pegs);
		}
	}
	
	public void setGameState(Row[] gameState) {
		this.gameState = gameState;
	}
	
	public Row[] getGameState() {
		return gameState;
	}
	
	public void initReponseState() {
		responseState = new Row[BOARD_HEIGHT];
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			responseState[i] = new Row();
		}
		
		for(int i = 0; i < responseState.length; i++) {
			Peg[] pegs = new Peg[getCodeSize()];
			for(int j = 0; j < getCodeSize(); j++) {
				pegs[j] = new Peg();
				pegs[j].setColor(Color.NONE);
				pegs[j].setPegType(PegType.SMALL);
			}
			responseState[i].populate(pegs);
		}
	}
	
	public void setResponseState(Row[] responseState) {
		this.responseState = responseState;
	}
	
	public Row[] getResponseState() {
		return responseState;
	}
	
	public void setActiveRowId(int activeRowId) {
		this.activeRowId = activeRowId;
	}
	
	public int getActiveRowId() {
		return activeRowId;
	}
	
	public void setCodeRowHidden(boolean codeRowHidden) {
		this.codeRowHidden = codeRowHidden;
	}
	
	public boolean getCodeRowHidden() {
		return codeRowHidden;
	}

}
