package test.uab.tqs.bingo.model;

import main.uab.tqs.bingo.model.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomNumberGeneratorTest {

    @Test
    void testConstructor() {
        RandomNumberGenerator rng = new RandomNumberGenerator(1, 90, 5);
        assertEquals(1, rng.getMin());
        assertEquals(90, rng.getMax());
        assertEquals(5, rng.getTotalNumbers());

        // Rango min y max  iguales (solo se puede generar un número)
        rng = new RandomNumberGenerator(10, 10, 1);
        assertEquals(10, rng.getMin());
        assertEquals(10, rng.getMax());
        assertEquals(1, rng.getTotalNumbers());

        // TotalNumbers igual a 0
        rng = new RandomNumberGenerator(5, 10, 0);
        assertEquals(5, rng.getMin());
        assertEquals(10, rng.getMax());
        assertEquals(0, rng.getTotalNumbers());
    }

    @Test
    void testGenerateNumbers() {
        // Generar 5 números únicos dentro de un rango
        RandomNumberGenerator rng = new RandomNumberGenerator(1, 90, 5);
        int[] numbers = rng.generateNumbers();
        assertEquals(5, numbers.length);

        // Verificar que los números estén en el rango y no se repitan
        for (int num : numbers) {
            assertTrue(num >= 1 && num <= 90, "Número fuera de rango");
        }
        assertEquals(5, uniqueCount(numbers), "Los números generados deben ser únicos");

        // Generar todos los números posibles en el rango
        rng = new RandomNumberGenerator(1, 5, 5);
        numbers = rng.generateNumbers();
        assertEquals(5, numbers.length);
        for (int i = 1; i <= 5; i++) {
            assertTrue(contains(numbers, i), "El número " + i + " debería estar en el array");
        }

        // TotalNumbers 0
        rng = new RandomNumberGenerator(1, 90, 0);
        numbers = rng.generateNumbers();
        assertEquals(0, numbers.length, "El array debería estar vacío cuando totalNumbers es 0");

        // Un solo número disponible en el rango
        rng = new RandomNumberGenerator(10, 10, 1);
        numbers = rng.generateNumbers();
        assertEquals(1, numbers.length);
        assertEquals(10, numbers[0]);
    }

    @Test
    void testGetMin() {
        RandomNumberGenerator rng = new RandomNumberGenerator(5, 20, 3);
        assertEquals(5, rng.getMin());

        // Min y max iguales
        rng = new RandomNumberGenerator(10, 10, 1);
        assertEquals(10, rng.getMin());
    }

    @Test
    void testGetMax() {
        RandomNumberGenerator rng = new RandomNumberGenerator(5, 20, 3);
        assertEquals(20, rng.getMax());

        // Min y max iguales
        rng = new RandomNumberGenerator(10, 10, 1);
        assertEquals(10, rng.getMax());
    }

    @Test
    void testGetTotalNumbers() {
        RandomNumberGenerator rng = new RandomNumberGenerator(5, 20, 3);
        assertEquals(3, rng.getTotalNumbers());

        // TotalNumbers = 0
        rng = new RandomNumberGenerator(5, 20, 0);
        assertEquals(0, rng.getTotalNumbers());

        // TotalNumbers igual a todos los valores únicos del rango
        rng = new RandomNumberGenerator(1, 5, 5);
        assertEquals(5, rng.getTotalNumbers());
    }

    // Método auxiliar para comprobar si un número está en el array
    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para contar números únicos en un array
    private int uniqueCount(int[] array) {
        Set<Integer> uniqueSet = new HashSet<>();
        for (int num : array) {
            uniqueSet.add(num);
        }
        return uniqueSet.size();
    }
}
