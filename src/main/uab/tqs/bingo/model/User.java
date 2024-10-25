package main.uab.tqs.bingo.model;

public class User {
    private String name;
    private Cartoon cartoon;

    public User() {
        this.name = "";
        this.cartoon = null;
    }

    public User(String name, Cartoon cartoon) {

        // Pre-condición
        assert name != "" : "El no,bre no puede estar vacio";
        assert cartoon != null : "El cartoon no puede ser nulo";

        this.name = name;
        this.cartoon = cartoon;

        // Post-condición
        assert this.name != "" : "No se ha cambiado el nombre";
        assert this.cartoon != null : "No se ha cambiado el cartoon del objeto";
    }

    public void setName(String name) {
        // Pre-condición
        assert name != "" : "El nombre no puede estar vacio";

        this.name = name;

        // Post-condición
        assert this.name != "" : "No se ha cambiado el nombre";
    }

    public void setCartoon(Cartoon cartoon) {

        // Pre-condición
        assert cartoon != null : "El cartoon no puede ser nulo";

        this.cartoon = cartoon;

        // Post-condición
        assert this.cartoon != null : "No se ha cambiado el cartoon del objeto";
    }

    public String getName() {
        return this.name;
    }

    public Cartoon getCartoon() {
        return this.cartoon;
    }

}
