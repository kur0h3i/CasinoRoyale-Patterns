
package patterns.observer;

import personas.Jugador;

/**
 * Clase abstracta que define el comportamiento de suscripcion
 * de un Jugador a eventos o flujos de datos.
 * Permite registrar y desregistrar jugadores como suscriptores.
 */
public abstract class Subscription {

    /**
     * Suscribe al jugador a la fuente de eventos o datos.
     *
     * @param jugador instancia de Jugador que desea suscribirse
     */
    public abstract void subscribe(Jugador jugador);

    /**
     * Cancela la suscripcion del jugador, dejando de recibir eventos o datos.
     *
     * @param jugador instancia de Jugador que desea desuscribirse
     */
    public abstract void unsubscribe(Jugador jugador);
}