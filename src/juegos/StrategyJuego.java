package juegos;

import excep.ExcepcionJugadorSinFichas;

public interface StrategyJuego {
    void iniciarPartida() throws ExcepcionJugadorSinFichas;
}
