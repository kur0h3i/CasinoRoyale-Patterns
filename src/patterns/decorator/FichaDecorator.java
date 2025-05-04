package patterns.decorator;

public abstract class FichaDecorator implements Ficha {
    protected Ficha fichaDecorada;

    public FichaDecorator(Ficha fichaDecorada) {
        this.fichaDecorada = fichaDecorada;
    }

    @Override
    public int getCantidad() {
        return fichaDecorada.getCantidad();
    }

    @Override
    public String getDescripcion() {
        return fichaDecorada.getDescripcion();
    }
}