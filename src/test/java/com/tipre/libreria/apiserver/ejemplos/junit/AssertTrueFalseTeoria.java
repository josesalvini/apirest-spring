package com.tipre.libreria.apiserver.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertTrueFalseTeoria {

	@Test
	public void testAsserts() {
		
		assertTrue(true);
		assertFalse(false);
	}
	
	@Test
	public void testAssertsV2() {
		boolean expresion = 4 == 4;
		boolean expresion1 = 4 == 3;
		assertTrue(expresion);
		assertFalse(expresion1);
	}
	
}
