
package main.uab.tqs.bingo.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumberGenerator {
    private int max;
    private int min;
    private int totalNumbers;

    public RandomNumberGenerator(int min, int max, int totalNumbers) {

        // Precondiciones:
        assert min <= max : "El valor mínimo debe ser menor o igual que el máximo";
        assert totalNumbers >= 0 : "Total numbers should be non-negative";
        assert totalNumbers <= (max - min + 1) : "El rango no contiene suficientes valores únicos para generar el totalNumbers solicitado";

        this.min = min;
        this.max = max;
        this.totalNumbers = totalNumbers;

        // Postcondiciones:
        assert this.min == min : "El valor mínimo no se asignó correctamente";
        assert this.max == max : "El valor máximo no se asignó correctamente";
        assert this.totalNumbers == totalNumbers : "El total de números no se asignó correctamente";
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getTotalNumbers() {
        return totalNumbers;
    }

    public int[] generateNumbers() {

        // Precondiciones:
        assert this.totalNumbers <= (max - min + 1) : "No hay suficientes valores únicos en el rango para el totalNumbers solicitado";

        Random rand = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < totalNumbers) {
            int number = rand.nextInt((max - min) + 1) + min;
            uniqueNumbers.add(number);
        }

        int[] numbers = uniqueNumbers.stream().mapToInt(Integer::intValue).toArray();

        // Postcondiciones:
        assert numbers.length == totalNumbers : "El array generado no contiene el número correcto de elementos";
        for (int num : numbers) {
            assert num >= min && num <= max : "Número fuera de rango en el array generado";
        }
        assert uniqueNumbers.size() == numbers.length : "El array contiene números repetidos";

        return numbers;
    }
}

