package test.uab.tqs.bingo.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import main.uab.tqs.bingo.model.Cartoon;
import main.uab.tqs.bingo.model.User;

class UserTest {

    @Test
    void testUser() {
        // Creamos un objeto de User por defecto
        User user = new User();

        assertEquals("", user.getName());
        assertEquals(null, user.getCartoon());
    }

    @Test
    void testUserStringCartoon() {

		// Creo el mock y
		Cartoon _cartoon = mock(Cartoon.class);

		// Defino lo que devolvera la función
        /*
        when(_cartoon.checkNumber(0)).thenReturn(true);
		_cartoon.checkNumber(0); // Ahora este metodo devolvera true

		assertEquals(true, _cartoon.checkNumber(0));
        */

        //Creamos el objeto de user con atributos
		User user = new User("Rubén",_cartoon);

		assertEquals("Rubén", user.getName());
		assert(null != user.getCartoon());
    }

    @Test
    void testSetName() {

        User user = new User();

        user.setName("Rubén");

        assertEquals("Rubén", user.getName());
    }

    @Test
    void testSetCartoon() {

        // Creo un mock
        Cartoon _cartoon = mock(Cartoon.class);

        User user = new User();
        user.setCartoon(_cartoon);

        assert(null != user.getCartoon());
    }

}
