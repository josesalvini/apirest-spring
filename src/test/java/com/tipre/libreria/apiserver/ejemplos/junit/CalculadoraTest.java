package com.tipre.libreria.apiserver.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculadoraTest {

	@Autowired
	private Calculadora calculadora;
	
	@Test
	@Disabled("Prueba deshabilitada, verificada.")
	public void testAssertEqual() {	
		assertEquals(2, calculadora.sumar(1,1));		
		assertEquals(0, calculadora.restar(1,1));
		assertEquals(1, calculadora.multiplicar(1,1));
		assertEquals(1, calculadora.dividir(1,1));	
	}
	
	@BeforeAll
	public static void primerTest() {
		System.out.println("Se ejecuta el primer test");
	}
	
	@AfterAll
	public static void ultimoTest() {
		System.out.println("Se ejecuta el ultimo test");
	}
	
	@BeforeEach
	public void initObjectTest() {
		calculadora = new Calculadora();
		System.out.println("Se inicializa el objeto calculadora.");
	}
	
	@AfterEach
	public void testAfterEach() {	
		System.out.println("Se ejecuta el metodo AfterEach().");
	}
	
	
	@Test
	@DisplayName("Prueba unitaria AssertTrue")
	public void testAssertTrue() {		
		assertTrue(2 == calculadora.sumar(1,1));	
		assertTrue(0 == calculadora.restar(1,1));
		assertTrue(1 == calculadora.multiplicar(1,1));
		assertTrue(1 == calculadora.dividir(1,1));
		//assertTrue(expresion);
		//assertFalse(expresion1);
		System.out.println("Se ejecuta el metodo testAssertTrue");		
	}
	
	@Test
	@DisplayName("Prueba unitaria AssertFalse")
	public void testAssertFalse() {
		assertFalse(3 == calculadora.sumar(1,1));	
		assertFalse(2 == calculadora.restar(1,1));
		assertFalse(2 == calculadora.multiplicar(1,1));
		assertFalse(4 == calculadora.dividir(1,1));	
		System.out.println("Se ejecuta el metodo testAssertFalse");	
	}

	
	
}
