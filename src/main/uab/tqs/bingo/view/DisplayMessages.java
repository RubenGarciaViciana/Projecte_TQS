package main.uab.tqs.bingo.view;

public class DisplayMessages {
    public void showWelcomeMessage(String player) {
        System.out.println("¡Bienvenido al Bingo!" + player);
    }

    public void startMessage() {
        System.out.println("Preparáte, vamos a empezar");
    }

    public void showNumberGenerated(int number) {
        System.out.println(number);
    }

    public void showLineWinner(String playerName) {
        System.out.println("¡El jugador " + playerName + "ha cantado línea");
    }

    public void showBingoWinner(String playerName) {
        System.out.println("¡El jugador " + playerName + " ha ganado!");
    }

    public void showFinishGame() {
        System.out.println("El juego ha acabado, nadie ha cantado bingo!");
    }

    public void showMarkedNumber(int number) {
        System.out.println("El número " + number + " se ha marcado");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

}
