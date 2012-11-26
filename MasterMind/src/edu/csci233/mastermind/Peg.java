package edu.csci233.mastermind;

import java.io.Serializable;

public class Peg implements Serializable {

	private static final long serialVersionUID = 1L;
	private Color color;
	private PegType pegType;
	
	public enum Color {
		RED, GREEN, ORANGE, BLUE, YELLOW, PURPLE, GRAY, WHITE, NONE;
	}
	
	public enum PegType {
		LARGE, SMALL;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public PegType getPegType() {
		return pegType;
	}
	
	public void setPegType(PegType pegType) {
		this.pegType = pegType;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Peg) {
			Peg peg = (Peg)o;
			if (this.getColor().equals(peg.getColor()) && this.getPegType().equals(peg.getPegType())) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + this.color.hashCode();
		result = result * 31 + this.pegType.hashCode();
		return result;
	}
}
