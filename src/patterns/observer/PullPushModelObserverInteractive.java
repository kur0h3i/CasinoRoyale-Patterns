
package patterns.observer;

import excep.ExcepcionJugadorSinFichas;

/**
 * PullPushModelObserverInteractive => Interfaz que extiende PullPushModelObserver para casos
 * donde, además de la actualización básica, se necesita un método interactivo específico.
 * Implementaciones típicas lanzan un flujo de interacción (por ejemplo, mostrar menús ASCII).
 */
public interface PullPushModelObserverInteractive extends PullPushModelObserver {

    /**
     * Método que inicia la lógica interactiva del observador (menús, interfaces, etc.).
     * Se invoca cuando el jugador esté en posición y pulse la tecla de interactuar.
     *
     * @throws ExcepcionJugadorSinFichas si el jugador no dispone de fichas para comenzar la interacción
     */
    void interactive() throws ExcepcionJugadorSinFichas;
}
