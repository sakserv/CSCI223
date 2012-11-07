package edu.csci233.mastermind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.csci233.mastermind.Peg.Color;
import edu.csci233.mastermind.Peg.PegType;

public class PegTest {
	
	Peg peg;

	@Before
	public void setUp() {
		peg = new Peg();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testColorIsNullOnCreate() {
		assertNull(peg.getColor());
	}
	
	@Test
	public void testPegTypeIsNullOnCreate() {
		assertNull(peg.getPegType());
	}
	
	@Test
	public void testPegIsRed() {
		peg.setColor(Color.RED);
		assertEquals(peg.getColor(), Color.RED);
	}
	@Test
	public void testPegIsSmallPegType() {
		peg.setPegType(PegType.SMALL);
		assertEquals(peg.getPegType(), PegType.SMALL);
	}
	

}
