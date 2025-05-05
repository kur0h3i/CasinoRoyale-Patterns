package patterns.decorator;

import patterns.decorator.fichas.AgregarFichasComponentDecorator;
import patterns.decorator.fichas.FichaComponent;
import patterns.decorator.fichas.FichaBaseComponent;
import patterns.decorator.fichas.QuitarFichasComponentDecorator;

public class Jugador {
    private String nombre;
    private FichaComponent fichas;

    public Jugador(String nombre, int cantidadInicialFichas) {
        this.nombre = nombre;
        this.fichas = new FichaBaseComponent(cantidadInicialFichas);
    }

    public void agregarFichas(int cantidad) {
        this.fichas = new AgregarFichasComponentDecorator(this.fichas, cantidad);
    }

    public void quitarFichas(int cantidad) {
        this.fichas = new QuitarFichasComponentDecorator(this.fichas, cantidad);
    }

    public int getCantidadFichas() {
        return fichas.getCantidad();
    }

    public String getDescripcionFichas() {
        return fichas.getDescripcion();
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre + ", Fichas totales: " + getCantidadFichas();
    }
}