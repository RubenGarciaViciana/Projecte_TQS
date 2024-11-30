package main.uab.tqs.bingo.view;

import main.uab.tqs.bingo.model.Cartoon;

public class DisplayCartoon {

	public void display(Cartoon cartoon) {
		int[][] cartoonNumbers = cartoon.getCartoon();
		boolean[][] checked = cartoon.getChecked();

		System.out.println("===== Tu cartón: =====");
		for (int fila = 0; fila < cartoonNumbers.length; fila++) {
			for (int columna = 0; columna < cartoonNumbers[fila].length; columna++) {
				int number = cartoonNumbers[fila][columna];
				boolean isChecked = checked[fila][columna];

				if (number == 0) {

					// Celda vacía
					System.out.print("[    ] ");
				} else {
					System.out.printf("[%s%3d%s] ", isChecked ? "(" : " ", number, isChecked ? ")" : " ");
				}
			}
			System.out.println();
		}
		System.out.println("===========================\n");
	}
}
