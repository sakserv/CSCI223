package edu.csci233.mastermind;

import java.io.Serializable;

public class Row implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private RowType rowType;
	private int width = 4;
	private Peg[] pegs;
	
	public enum RowType {
		CODE, GUESS, RESPONSE;
	}

	public RowType getRowType() {
		return rowType;
	}
	
	public void setRowType(RowType rowType) {
		this.rowType = rowType;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void populate(Peg[] pegs) {
		this.pegs = pegs;
	}
	
	public Peg[] getPegs() {
		return pegs;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Row) {
			Row row = (Row)o;
			if (this.getRowType().equals(row.getRowType()) && this.getWidth() == row.getWidth()) {
				
				Peg[] thisPegs = this.getPegs();
				Peg[] thatPegs = row.getPegs();
				
				for (int i = 0; i < thisPegs.length; i++) {
					if(!this.pegs[i].equals(thatPegs[i])) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + this.getWidth();
		result = result * 31 + this.getRowType().hashCode();
		result = result * 31 + this.getPegs().hashCode();
		return result;
	}
}
