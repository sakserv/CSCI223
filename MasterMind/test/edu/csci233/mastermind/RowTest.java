package edu.csci233.mastermind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.csci233.mastermind.Peg.Color;
import edu.csci233.mastermind.Peg.PegType;
import edu.csci233.mastermind.Row.RowType;

public class RowTest {

	Row row;
	
	@Before
	public void setUp() throws Exception {
		row = new Row();
	}

	@After
	public void tearDown() throws Exception {
		row = null;
	}

	@Test
	public void testRowTypeIsNullOnCreate() {
		assertNull(row.getRowType());
	}
	
	@Test
	public void testRowTypeIsCodeRow() {
		row.setRowType(RowType.CODE);
		assertEquals(row.getRowType(), RowType.CODE);
	}
	
	@Test
	public void testRowWidthIsFourByDefault() {
		assertEquals(4, row.getWidth());
	}
	
	@Test
	public void testPopulateCodeRowRandom() {
		Peg[] codePegs = new Peg[row.getWidth()];
		Random rand = new Random();
		for(int i = 0; i < codePegs.length; i++) {
			int pegColorIdx = rand.nextInt(Color.values().length);
			Peg peg = new Peg();
			peg.setPegType(PegType.LARGE);
			peg.setColor(Color.values()[pegColorIdx]);
			codePegs[i] = peg;
		}
		row.populate(codePegs);
		Peg[] codeRow = row.getPegs();
		assertEquals(codePegs, codeRow);
	}

}
