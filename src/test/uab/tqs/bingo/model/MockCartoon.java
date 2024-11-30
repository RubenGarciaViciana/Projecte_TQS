package test.uab.tqs.bingo.model;

import main.uab.tqs.bingo.model.Cartoon;

import static org.mockito.internal.matchers.text.ValuePrinter.print;

public class MockCartoon extends Cartoon {

    int[][] cartoon = new int[3][9];
    boolean[][] checked = new boolean[3][9];

    @Override
    public void generateCartoon() {
        for (int i=0; i < 3; i++){
            for (int j = 0; j < 9; j++) {
                this.cartoon[i][j] = 0;
                this.checked[i][j] = false;
            }
        }

        this.cartoon[0][0] = 1;
        this.cartoon[0][1] = 2;
        this.cartoon[0][2] = 3;
        this.cartoon[0][3] = 4;
        this.cartoon[0][4] = 5;

        this.cartoon[1][0] = 6;
        this.cartoon[1][1] = 7;
        this.cartoon[1][3] = 8;
        this.cartoon[1][4] = 9;
        this.cartoon[1][5] = 10;
    }

    @Override
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

    @Override
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

    @Override
    public int[][] getCartoon() {
        return this.cartoon;
    }

    @Override
    public boolean[][] getChecked() {
        return this.checked;
    }
}
