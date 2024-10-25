package main.uab.tqs.bingo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Cartoon {
	private int[][] cartoon;
	private boolean[][] checked;
	
	public Cartoon() {
		
	}
	
	public void generateCartoon() {
		
	}
	
	public boolean checkNumber(int number) {
		return false;
	}

	public boolean putNumber(int fila, int columnas, int number) {
		return true;
	}

	public int[][] getCartoon() {
		return this.cartoon;
	}

	public boolean[][] getChecked() {
		return this.checked;
	}
}
