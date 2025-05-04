package patterns.decorator;

public class FichaBase implements Ficha {
    private int cantidad;

    public FichaBase(int cantidadInicial) {
        this.cantidad = cantidadInicial;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String getDescripcion() {
        return "Fichas base: " + cantidad;
    }
}