package main.uab.tqs.bingo.controller;

import main.uab.tqs.bingo.model.Cartoon;
import main.uab.tqs.bingo.model.RandomNumberGenerator;
import main.uab.tqs.bingo.model.User;
import main.uab.tqs.bingo.view.DisplayMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {

    private User player;
    private List<Integer> numbers;
    private int currentNumberIndex;
    private DisplayMessages messagesDisplay;
    private boolean isGameOver;

    private RandomNumberGenerator randomNumberGenerator;

    public GameController(User player) {

        // Precondiciones:
        assert player != null : "El jugador no puede ser null";

        this.player = player;
        this.numbers = new ArrayList<>();
        this.currentNumberIndex = 0;
        setMessagesDisplay(new DisplayMessages());
        this.isGameOver = false;

        // Postcondiciones:
        assert this.player == player : "El jugador no fue asignado correctamente";
        assert this.numbers.isEmpty() : "La lista de números no está vacía al inicializar";
        assert !this.isGameOver : "El juego no debería estar marcado como terminado al inicio";
    }

    public void setRandomNumberGenerator(RandomNumberGenerator RNG) {

        // Precondiciones:
        assert RNG != null : "El generador de números aleatorios no puede ser null";

        this.randomNumberGenerator = RNG;

        // Postcondiciones:
        assert this.randomNumberGenerator == RNG : "El generador de números no se asignó correctamente";
    }

    public void startGame() {

        // Precondiciones:
        assert randomNumberGenerator != null : "El generador de números aleatorios no ha sido configurado";
        assert player.getName() != null && !player.getName().isEmpty() : "El nombre del jugador no puede ser null o vacío";

        int[] generatedNumbers = randomNumberGenerator.generateNumbers();

        for (int num : generatedNumbers) {
            numbers.add(num);
        }

        messagesDisplay.showWelcomeMessage(player.getName());

        startNumberDisplay();

        // Postcondiciones:
        assert !numbers.isEmpty() : "La lista de números debería contener números generados";
    }

    public void setMessagesDisplay(DisplayMessages messagesDisplay) {

        // Precondiciones:
        assert messagesDisplay != null : "La instancia de DisplayMessages no puede ser null";

        this.messagesDisplay = messagesDisplay;

        // Postcondiciones:
        assert this.messagesDisplay == messagesDisplay : "La instancia de mensajes no se asignó correctamente";
    }

    public void startNumberDisplay() {

        // Precondiciones:
        assert !numbers.isEmpty() : "No se pueden mostrar números porque la lista está vacía";

        Scanner scanner = new Scanner(System.in);

        while (!isGameOver && currentNumberIndex < numbers.size()) {
            int number = numbers.get(currentNumberIndex);
            System.out.println("Tienes el siguiente numero ? ( 'Y' o 'N' )");
            messagesDisplay.showNumberGenerated(number);
            startPlayerInteraction(number, scanner);
            currentNumberIndex++;
        }
        scanner.close();

        if (currentNumberIndex >= numbers.size()) {
            messagesDisplay.showFinishGame();
            isGameOver = true;
        }

        // Postcondiciones:
        assert currentNumberIndex == numbers.size() || isGameOver : "El índice de números o el estado del juego no son consistentes";
    }

    public void startPlayerInteraction(int number, Scanner scanner) {

        // Precondiciones:
        assert number >= 0 : "El número mostrado al jugador no puede ser negativo";
        assert scanner != null : "El scanner no puede ser null";

        while (!isGameOver) {

            String input = scanner.nextLine();
            if (input.equals("Y")) {
            	
                markNumber(number);
                
                System.out.println("Tienes Linea o Bingo ? ( 'L', 'B' o '' )");
                
                String input2 = scanner.nextLine();
                if (input2.equals("B")) {
                    checkBingo();
                } else if (input2.equals("L")) {
                    checkLine();
                }
                break;
                
            } else if (input.equals("N")){
                String input2 = scanner.nextLine();
                if (input2.equals("B")) {
                    checkBingo();
                } else if (input2.equals("L")) {
                    checkLine();
                }
                break;
            }else {
            	System.out.println("Tienes que poner 'Y' o 'N' en caso de tener el numero o no");
            }
        }
    }

    public boolean markNumber(int number) {

        // Precondiciones:
        assert number >= 0 : "El número marcado no puede ser negativo";
        Cartoon playerCartoon = player.getCartoon();
        assert playerCartoon != null : "El jugador debe tener un cartón asignado";

        boolean result = false;

        if (playerCartoon.checkNumber(number)) {
            messagesDisplay.showMarkedNumber(number);
            result = true;
        } else {
            messagesDisplay.showError("El número " + number + " no está en tu cartón o ya está marcado.");
            result = false;
        }

        // Postcondiciones:
        assert result || !playerCartoon.checkNumber(number) : "El número debería estar marcado correctamente o no existir en el cartón";

        return result;
    }

    public boolean checkLine() {
        Cartoon cartoon = player.getCartoon();

        // Precondiciones:
        assert cartoon != null : "El jugador debe tener un cartón asignado";
        boolean hasLine = false;

        for (int i = 0; i < cartoon.getChecked().length; i++) {
            int count = 0;
            for (int j = 0; j < cartoon.getChecked()[i].length; j++) {
                if (cartoon.getChecked()[i][j]) {
                    count++;
                }
            }
            if (count == 5) {
                hasLine = true;
                break;
            }
        }

        if (hasLine) {
            messagesDisplay.showLineWinner(player.getName());
        } else {
            messagesDisplay.showError("No tienes ninguna línea completa.");
        }

        // Postcondiciones:
        assert !hasLine || messagesDisplay != null : "Si hay una línea completa, debería mostrarse un mensaje";

        return hasLine;
    }

    public boolean checkBingo() {
        Cartoon cartoon = player.getCartoon();

        // Precondiciones:
        assert cartoon != null : "El jugador debe tener un cartón asignado";
        boolean isBingo = true;

        for (int i = 0; i < cartoon.getChecked().length; i++) {
            for (int j = 0; j < cartoon.getChecked()[i].length; j++) {
                if (cartoon.getCartoon()[i][j] != 0 && !cartoon.getChecked()[i][j]) {
                    isBingo = false;
                    break;
                }
            }
            if(!isBingo) {
            	break;
            }
        }

        if (isBingo) {
            messagesDisplay.showBingoWinner(player.getName());
            isGameOver = true;
            isBingo = true;
        } else {
            messagesDisplay.showError("Aún no tienes todos los números marcados.");
            isBingo = false;
        }

        // Postcondiciones:
        assert isBingo == isGameOver : "Si hay Bingo, el juego debería terminar";

        return isBingo;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public User getPlayer() {
        return player;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getCurrentNumberIndex() {
        return currentNumberIndex;
    }

    public DisplayMessages getMessagesDisplay() {
        return messagesDisplay;
    }
}