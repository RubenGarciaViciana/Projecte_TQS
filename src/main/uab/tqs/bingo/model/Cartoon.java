package main.uab.tqs.bingo.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cartoon {
	private int[][] cartoon;
	private boolean[][] checked;
	
	public Cartoon() {
		this.cartoon = new int[3][9];
		this.checked = new boolean[3][9];

		// Inicializamos las matrices
		for (int i=0; i < 3; i++){
			for (int j = 0; j < 9; j++) {
				this.cartoon[i][j] = 0;
				this.checked[i][j] = false;
			}
		}

		// Post-condición: el tamaño del cartón es correcto
		assert this.cartoon.length == 3 && this.cartoon[0].length == 9;
		assert this.checked.length == 3 && this.checked[0].length == 9;
	}
	
	public void generateCartoon() {
		Random random = new Random();
		Set<Integer> numerosUsados = new HashSet<>();

		// Pre-condición: el tamaño del cartón es correcto y este inicializado
		assert this.cartoon != null;
		assert this.cartoon.length == 3 && this.cartoon[0].length == 9;

		for (int fila=0; fila < 3; fila++) {
			int numRestantes = 5;
			Set<Integer> columnasLlenas = new HashSet<>();

			while (numRestantes > 0) {
				int col = random.nextInt(9);

				// Si la columna ya tiene un número, seguimos buscando columnas disponibles
				if (columnasLlenas.contains(col)) {
					continue;
				}

				int numero = 0;

				// Generamos un numero segun en la columna en la que este 1-9, 10-19, ...
				do {
					numero = random.nextInt(10) + (col * 10);
					// Seguimos generando el numero si ya lo tenemos o es 0
				} while (numerosUsados.contains(numero) || numero == 0);

				// Ponemos el numero en el cartoon y en los usados
				this.cartoon[fila][col] = numero;
				numerosUsados.add(numero);
				// Ponemos que la columna que ya tiene numero
				columnasLlenas.add(col);
				// Restamos en el contador de numeros
				numRestantes--;
			}
		}

		//Post-condición: Mirar que se haya generado correctamente
		for (int fila = 0; fila < 3; fila++) {
			int countNumeros = 0;
			for (int col = 0; col < 9; col++) {
				if (this.cartoon[fila][col] != 0) {
					assert this.cartoon[fila][col] > 0 && this.cartoon[fila][col] <= 90 : "Número fuera de rango >0 - <=90";
					countNumeros++;
				}
			}
			assert countNumeros == 5 : "La fila no tiene 5 numeros";
		}
	}
	
	public boolean checkNumber(int number) {

		if (number > 0 && number <= 90){
			for (int fila = 0; fila < 3; fila++) {
				for (int columna = 0; columna < 9; columna++) {
					if (this.cartoon[fila][columna] == number) {

						if (this.checked[fila][columna]) {
							return false;
						}else{
							this.checked[fila][columna] = true;

							//Post-condición: Mirar que se ha marcado correctamente
							assert this.checked[fila][columna] == true;

							return true;
						}

					}
				}
			}
		}
		return false;
	}

	public boolean putNumber(int fila, int columna, int number) {
		// Pre-condiciones: Verificar que fila y columna están en el rango, y el número
		if (fila >= 0 && fila < 3 && columna >= 0 && columna < 9) {
			if (number > 0 && number <= 90) {
				this.cartoon[fila][columna] = number;
				this.checked[fila][columna] = false;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}

		// Postcondiciones: Verificar que el número ha sido colocado correctamente
		assert this.cartoon[fila][columna] == number : "El numero no ha sido colocado";
		assert !this.checked[fila][columna] : "La casilla ha sido marcada";

		return true;
	}

	public int[][] getCartoon() {
		return this.cartoon;
	}

	public boolean[][] getChecked() {
		return this.checked;
	}
}
