
package patterns.observer;

import personas.Jugador;

/**
 * Clase abstracta que define el comportamiento de suscripcion
 * de un Jugador a eventos o flujos de datos.
 * Permite registrar y desregistrar jugadores como suscriptores.
 */
public interface Subscription {

    /**
     * Suscribe al jugador a la fuente de eventos o datos.
     *
     * @param jugador instancia de Jugador que desea suscribirse
     */
    void subscribe(Jugador jugador);

    /**
     * Cancela la suscripcion del jugador, dejando de recibir eventos o datos.
     *
     * @param jugador instancia de Jugador que desea desuscribirse
     */
    void unsubscribe(Jugador jugador);
}