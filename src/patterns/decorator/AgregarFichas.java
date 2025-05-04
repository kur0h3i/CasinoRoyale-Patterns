package patterns.decorator;

public class AgregarFichas extends FichaDecorator {
    private int cantidadAgregar;

    public AgregarFichas(Ficha fichaDecorada, int cantidadAgregar) {
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