package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.main;

class mainTest {

	@Test
	void testHola() {
		
		main a = new main();
		
		assertEquals(true, a.hola());
	}

}
