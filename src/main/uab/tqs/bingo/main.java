package main.uab.tqs.bingo;

import java.util.Scanner;

import main.uab.tqs.bingo.controller.GameController;
import main.uab.tqs.bingo.model.Cartoon;
import main.uab.tqs.bingo.model.RandomNumberGenerator;
import main.uab.tqs.bingo.model.User;
import main.uab.tqs.bingo.view.DisplayCartoon;
import main.uab.tqs.bingo.view.DisplayMessages;

public class main {
	public boolean hola() {
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("Bienvenido al juego del Bingo!");
		System.out.println("------------------------------------");
		System.out.println("INSTRUCCIONES");
		System.out.println("------------------------------------");
		System.out.println("Se te iran mostrando numeros de manera aleatoria dentro del rango del 1-90 (incluidos)");
		System.out.println("Cada vez que salga un numero tendras que decir si tienes este numero o no en el carton que te damos");
		System.out.println("");
		System.out.println("Si tienes el numero deberas escribir 	- 'Y' ");
		System.out.println("Sino tienes el numero deberas escribir 	- 'N' ");
		System.out.println("");
		System.out.println("Despues en caso de tener linea o bigo tendras las siguientes opciones");
		System.out.println("");
		System.out.println("Si tienes Linea 	- 'L' ");
		System.out.println("Si tienes Bingo 	- 'B' ");
		System.out.println("Sino tines Nada 	- Enter ");
		System.out.println("");
		System.out.println("Una vez tengas bingo o hayan salido todos los numero acaba la partida.");
		System.out.println("------------------------------------");
		System.out.println("Para iniciar el juego Primero necesitamos que escriba su nombre de usuario (este no puede estar vacio):");
		
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.nextLine();
		
		Cartoon cartoon = new Cartoon();
		cartoon.generateCartoon();
		User usuario = new User(nombre, cartoon);
		
		System.out.println("");
		System.out.println("Aqui tienes tu carton:");
		DisplayCartoon dc = new DisplayCartoon();
		dc.display(cartoon);
		System.out.println("");
		
		GameController gc = new GameController(usuario);
		RandomNumberGenerator rng = new RandomNumberGenerator(1, 90, 90);
		gc.setRandomNumberGenerator(rng);
		gc.setMessagesDisplay(new DisplayMessages());
		gc.startGame();
		
		// Crear scanner con escribe tu nombre e instrucciones de como se juega
		// Crear usuario con el cartoon que antes se tiene que hacer generate cartoon
	}
}
