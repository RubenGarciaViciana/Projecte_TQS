package test.uab.tqs.bingo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.uab.tqs.bingo.main;

class mainTest {

	@Test
	void testHola() {
		
		main a = new main();
		
		assertEquals(true, a.hola());
	}

}
