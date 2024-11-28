package test.uab.tqs.bingo.model;

import static org.junit.jupiter.api.Assertions.*;

import main.uab.tqs.bingo.model.Cartoon;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class CartoonTest {

	@Test
	void testCartoon() {
		Cartoon c = new Cartoon();

		//Miramos si tienen las dim correctas
		assertEquals(3,c.getCartoon().length);
		assertEquals(9,c.getCartoon()[0].length);

		assertEquals(3,c.getChecked().length);
		assertEquals(9,c.getChecked()[0].length);

		//Miramos que al inicial los checked sean falsos
		for (int i=0; i < 3; i++){
			for (int j = 0; j < 9; j++) {
				assertFalse(c.getChecked()[i][j]);
			}
		}

		//Miramos que el carton tiene numeros sean 0
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(c.getCartoon()[i][j],0);
			}
		}
	}

	@Test
	void testGenerateCartoon() {
		Cartoon c = new Cartoon();
		c.generateCartoon();

		int[][] cartoon = c.getCartoon();
		// Verifico que as filas y columnas estan bien
		assertEquals(3,c.getCartoon().length);
		assertEquals(9,c.getCartoon()[0].length);

		assertEquals(3,c.getChecked().length);
		assertEquals(9,c.getChecked()[0].length);

		// Verificar que cada fila tiene exactamente 5 números no nulos(0) y estan dentro del ranfo >0 y <=90

		Set<Integer> numUnicos = new HashSet<>();

		for (int i = 0; i < 3; i++) {
			int count = 0;
			for (int j = 0; j < 9; j++) {
				if (cartoon[i][j] != 0) { // 0 es que no hay numero
					// Miramos que esten entre > 0 y <=90
					assertTrue(c.getCartoon()[i][j] > 0 && c.getCartoon()[i][j] <= 90);
					// Miramos que los numero sean unicos
					assertTrue(numUnicos.add(c.getCartoon()[i][j]));
					count++;
				}
			}
			// Miramos que cada fila tenga 5 numeros
			assertEquals(5, count);
		}

		//Miramos que al generarlo los checked sean falsos
		for (int i=0; i < 3; i++){
			for (int j = 0; j < 9; j++) {
				assertFalse(c.getChecked()[i][j]);
			}
		}

	}

	@Test
	void testCheckNumber() {
		Cartoon c = new Cartoon();

		//Marcar numero que tenemos en el carton
		c.putNumber(0,0,2);

		// Mirar uno que no hemos puesto
		assertFalse(c.checkNumber(1));
		
		assertTrue(c.checkNumber(2));
		assertTrue(c.getChecked()[0][0]); // Miramos que este marcado

		Cartoon c2 = new Cartoon();
		//Marcar numero que no existe
		assertFalse(c2.checkNumber(100));
		//Marcar numero que no existe
		assertFalse(c2.checkNumber(0));
		//Ninguno debería estar marcado
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				assertFalse(c2.getChecked()[i][j]);
			}
		}

		//Marcar un numero ya marcado
		Cartoon c3 = new Cartoon();

		c3.putNumber(0,0,2);

		assertTrue(c3.checkNumber(2));
		assertTrue(c3.getChecked()[0][0]); // Miramos que este marcado

		assertFalse(c3.checkNumber(2));
		assertTrue(c3.getChecked()[0][0]); // Miramos que sigue marcado

	}

	@Test
	void testPutNumber(){
		Cartoon c = new Cartoon();

		assertEquals(c.putNumber(1,2,5),true);
		assertEquals(5,c.getCartoon()[1][2]);

		// Miramos que ponga los dentro del array
		assertFalse(c.putNumber(-1,0,1));
		assertFalse(c.putNumber(0,-1,1));

		assertFalse(c.putNumber(3, 0, 1));
		assertFalse(c.putNumber(0, 9, 1));

		//Miramos valores limite
		assertTrue(c.putNumber(0, 0, 90));
		assertEquals(90,c.getCartoon()[0][0]);

		assertTrue(c.putNumber(0, 0, 1));
		assertEquals(1,c.getCartoon()[0][0]);
		// Valores fuera del limite
		assertFalse(c.putNumber(0, 0, 0));
		assertFalse(c.putNumber(0, 0, 91));
	}

}
