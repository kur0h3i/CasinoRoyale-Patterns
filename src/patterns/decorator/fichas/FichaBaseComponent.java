package patterns.decorator.fichas;

public class FichaBaseComponent implements FichaComponent {
    private int cantidad;

    public FichaBaseComponent(int cantidadInicial) {
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