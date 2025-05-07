
package patterns.observer;

import excep.ExcepcionJugadorSinFichas;

/**
 * Interfaz que extiende PullPushModelObserver para casos
 * donde, ademes de la actualizacion besica, se necesita un metodo interactivo especifico.
 * Implementaciones tipicas lanzan un flujo de interaccion (por ejemplo, mostrar menus ASCII).
 */
public interface PullPushModelObserverInteractive extends PullPushModelObserver {

    /**
     * Metodo que inicia la logica interactiva del observador (menus, interfaces, etc.).
     * Se invoca cuando el jugador este en posicion y pulse la tecla de interactuar.
     *
     * @throws ExcepcionJugadorSinFichas si el jugador no dispone de fichas para comenzar la interaccion
     */
    void interactive() throws ExcepcionJugadorSinFichas;
}
