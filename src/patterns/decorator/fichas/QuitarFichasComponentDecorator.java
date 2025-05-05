package patterns.decorator.fichas;

public class QuitarFichasComponentDecorator extends FichaDecorator {
    private int cantidadQuitar;

    public QuitarFichasComponentDecorator(FichaComponent fichaDecorada, int cantidadQuitar) {
        super(fichaDecorada);
        this.cantidadQuitar = cantidadQuitar;
    }

    @Override
    public int getCantidad() {
        return super.getCantidad() - cantidadQuitar;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " - " + cantidadQuitar + " fichas quitadas";
    }
}