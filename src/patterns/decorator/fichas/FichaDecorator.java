package patterns.decorator.fichas;

public abstract class FichaDecorator implements FichaComponent {
    protected FichaComponent fichaDecorada;

    public FichaDecorator(FichaComponent fichaDecorada) {
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