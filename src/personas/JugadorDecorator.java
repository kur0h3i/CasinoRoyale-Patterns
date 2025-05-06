package personas;

public abstract class JugadorDecorator implements Jugador {
    protected Jugador jugadorDecorado;

    public JugadorDecorator(Jugador jugador) {
        this.jugadorDecorado = jugador;
    }

    @Override
    public void move(Integer x, Integer y) {
        jugadorDecorado.move(x, y);
    }

    @Override
    public void interacting() {
        jugadorDecorado.interacting();
    }

    @Override
    public void datosUsuarioEnPartida() {
        jugadorDecorado.datosUsuarioEnPartida();
    }

    @Override
    public Integer getFichas() {
        return jugadorDecorado.getFichas();
    }

    @Override
    public void setFichas(Double fichas) {
        jugadorDecorado.setFichas(fichas);
    }

    @Override
    public Double getDinero() {
        return jugadorDecorado.getDinero();
    }

    @Override
    public void setDinero(Double dinero) {
        jugadorDecorado.setDinero(dinero);
    }
}
