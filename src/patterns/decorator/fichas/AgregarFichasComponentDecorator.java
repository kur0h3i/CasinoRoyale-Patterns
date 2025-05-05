package patterns.decorator.fichas;

public class AgregarFichasComponentDecorator extends FichaDecorator {
    private int cantidadAgregar;

    public AgregarFichasComponentDecorator(FichaComponent fichaDecorada, int cantidadAgregar) {
        super(fichaDecorada);
        this.cantidadAgregar = cantidadAgregar;
    }

    @Override
    public int getCantidad() {
        return super.getCantidad() + cantidadAgregar;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + " + cantidadAgregar + " fichas añadidas";
    }
}