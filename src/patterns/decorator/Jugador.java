package patterns.decorator;

public class Jugador {
    private String nombre;
    private Ficha fichas;

    public Jugador(String nombre, int cantidadInicialFichas) {
        this.nombre = nombre;
        this.fichas = new FichaBase(cantidadInicialFichas);
    }

    public void agregarFichas(int cantidad) {
        this.fichas = new AgregarFichas(this.fichas, cantidad);
    }

    public void quitarFichas(int cantidad) {
        this.fichas = new QuitarFichas(this.fichas, cantidad);
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