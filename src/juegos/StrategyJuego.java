
package juegos;

import excep.ExcepcionJugadorSinFichas;

/**
 * Interfaz que define el contrato para las diferentes
 * estrategias de juego
 * disponibles en el casino virtual.
 * Cada implementacion representa un juego concreto (Bingo, Ruleta, Dados, etc.)
 * y debe proporcionar su propia logica de partida.
 */
public interface StrategyJuego {

    /**
     * Inicia la partida del juego.
     *
     * @throws ExcepcionJugadorSinFichas si el jugador no tiene fichas disponibles
     */
    void iniciarPartida() throws ExcepcionJugadorSinFichas;
}
