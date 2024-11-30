package test.uab.tqs.bingo.controller;

import main.uab.tqs.bingo.controller.GameController;
import main.uab.tqs.bingo.model.Cartoon;
import main.uab.tqs.bingo.model.RandomNumberGenerator;
import main.uab.tqs.bingo.model.User;
import main.uab.tqs.bingo.view.DisplayMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.uab.tqs.bingo.model.MockCartoon;
import test.uab.tqs.bingo.model.MockRandomNumberGenerator;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameControllerTest {

	private User user;
	private Cartoon cartoon;
	private DisplayMessages displayMessages;
	private RandomNumberGenerator rng;
	private GameController gameController;

	@BeforeEach
	void setUp() {
		// Crear un cartón de prueba
		cartoon = new MockCartoon();
		cartoon.generateCartoon();

		// Crear un usuario con el cartón
		user = new User("Test Player", cartoon);

		// Crear un generador de números aleatorios
		rng = new MockRandomNumberGenerator(1,10,10);

		// Crear el controlador de juego
		gameController = new GameController(user);
		gameController.setRandomNumberGenerator(rng);
		gameController.setMessagesDisplay(new DisplayMessages());
	}

	@Test
	void markNumberTest() {
		// Casos para números válidos
		int validNumber = this.cartoon.getCartoon()[0][0];
		boolean result = gameController.markNumber(validNumber);
		assertTrue(result, "El número válido debería marcarse correctamente.");

		// Casos para números fuera del rango (99)
		int invalidNumber = 99;
		result = gameController.markNumber(invalidNumber);
		assertFalse(result, "El número fuera del rango no debería marcarse.");

		// Casos para números ya marcados
		gameController.markNumber(validNumber);
		result = gameController.markNumber(validNumber);
		assertFalse(result, "El número ya marcado no debería marcarse de nuevo.");
	}

	@Test
	void checkLineTest() {
		// Caso donde no hay ninguna línea completa
		gameController.markNumber(cartoon.getCartoon()[0][0]);
		gameController.markNumber(cartoon.getCartoon()[1][0]);
		gameController.markNumber(cartoon.getCartoon()[2][0]);
		boolean isNotLine = gameController.checkLine();
		assertFalse(isNotLine, "No debería estar la línea entera");

		// Caso donde falta un número para la línea
		gameController.markNumber(cartoon.getCartoon()[0][0]);
		gameController.markNumber(cartoon.getCartoon()[0][1]);
		gameController.markNumber(cartoon.getCartoon()[0][2]);
		gameController.markNumber(cartoon.getCartoon()[0][3]);
		boolean oneNumberLeft = gameController.checkLine();
		assertFalse(isNotLine, "Falta un número para la línea");

		// Casos donde hay línea
		for (int i = 0; i < 9; i++) {
			gameController.markNumber(cartoon.getCartoon()[0][i]);  // Marcar toda la fila 0
		}

		boolean isLine = gameController.checkLine();
		assertTrue(isLine, "La línea no se ha detectado correctamente");
	}

	@Test
	void checkBingoTest() {
		// Caso donde no hay ningun número marcado
		boolean noMark = gameController.checkBingo();
		assertFalse(noMark, "No debe haber bingo, no hay ningún número marcado");

		// Caso donde solo hay un número marcado
		gameController.markNumber(cartoon.getCartoon()[0][0]);
		boolean oneMark = gameController.checkBingo();
		assertFalse(oneMark, "No debe haber bingo si no todos los números están marcados.");

		// Caso donde falta un número ([1,5])
		gameController.markNumber(cartoon.getCartoon()[0][1]);
		gameController.markNumber(cartoon.getCartoon()[0][2]);
		gameController.markNumber(cartoon.getCartoon()[0][3]);
		gameController.markNumber(cartoon.getCartoon()[0][4]);
		gameController.markNumber(cartoon.getCartoon()[0][5]);
		gameController.markNumber(cartoon.getCartoon()[1][0]);
		gameController.markNumber(cartoon.getCartoon()[1][1]);
		gameController.markNumber(cartoon.getCartoon()[1][2]);
		gameController.markNumber(cartoon.getCartoon()[1][3]);
		gameController.markNumber(cartoon.getCartoon()[1][4]);
		boolean result = gameController.checkBingo();
		assertFalse(result, "No debe haber bingo si no todos los números están marcados.");

		// Casos donde hay bingo
		int[][] cartoonNumbers = cartoon.getCartoon();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (cartoonNumbers[i][j] != 0) {
					gameController.markNumber(cartoonNumbers[i][j]);  // Marcar todos los números
				}
			}
		}
		result = gameController.checkBingo();
		assertTrue(result, "Debe haber bingo si todos los números están marcados.");
	}

	@Test
	void isGameOverTest() {
		// Casos donde el juego no está terminado
		assertFalse(gameController.isGameOver(), "El juego no debería estar terminado al principio.");

		// Casos donde el juego está terminado (después de un bingo)
		int[][] cartoonNumbers = cartoon.getCartoon();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (cartoonNumbers[i][j] != 0) {
					gameController.markNumber(cartoonNumbers[i][j]);  // Marcar todos los números
				}
			}
		}
		gameController.checkBingo();
		assertTrue(gameController.isGameOver(), "El juego debería estar terminado después de un bingo.");

		// Caso donde se han dicho todos los números
		gameController = new GameController(user);
		gameController.setRandomNumberGenerator(rng);
		gameController.setMessagesDisplay(new DisplayMessages());

		// Simular entrada usuario
		StringBuilder datosSimulados = new StringBuilder();

		// Generar 10 elementos con el formato "N/nN/n"
		for (int i = 1; i <= 10; i++) {
			datosSimulados.append("N\nN\n");
		}

		String entradaSimulada = datosSimulados.toString();
		ByteArrayInputStream simulatedInputStream = new ByteArrayInputStream(entradaSimulada.getBytes());
		System.setIn(simulatedInputStream);
		gameController.startGame();

		assertTrue(gameController.isGameOver(), "El juego debería estar terminado después de mostrar todos los números.");

		System.setIn(System.in);
	}
}