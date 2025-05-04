package patterns.decorator;

public class QuitarFichas extends FichaDecorator {
    private int cantidadQuitar;

    public QuitarFichas(Ficha fichaDecorada, int cantidadQuitar) {
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