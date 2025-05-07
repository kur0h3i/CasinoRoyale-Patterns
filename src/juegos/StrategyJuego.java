
package juegos;

import excep.ExcepcionJugadorSinFichas;

/**
 * StrategyJuego => Interfaz que define el contrato para las diferentes
 * estrategias de juego
 * disponibles en el casino virtual.
 * Cada implementación representa un juego concreto (Bingo, Ruleta, Dados, etc.)
 * y debe proporcionar su propia lógica de partida.
 */
public interface StrategyJuego {

    /**
     * Inicia la partida del juego.
     *
     * @throws ExcepcionJugadorSinFichas si el jugador no tiene fichas disponibles
     */
    void iniciarPartida() throws ExcepcionJugadorSinFichas;
}
