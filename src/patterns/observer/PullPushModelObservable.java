
package patterns.observer;


/**
 * Interfaz que define el contrato para un sujeto observable
 * en el patron Observer (Pull-Push). Permite gestionar observadores que reaccionan
 * a cambios de estado mediante metodos de adjuntar, eliminar y notificar.
 */
public interface PullPushModelObservable {

    /**
     * Adjunta un observador a este observable.
     *
     * @param observer instancia de PullPushModelObserver que se anadire
     */
    void attach(PullPushModelObserver observer);

    /**
     * Elimina un observador de este observable.
     *
     * @param observer instancia de PullPushModelObserver que se eliminare
     */
    void detach(PullPushModelObserver observer);

    /**
     * Elimina todos los observadores actualmente adjuntos.
     */
    void detachAll();

    /**
     * Notifica a todos los observadores adjuntos sobre un cambio de estado.
     */
    void notifyObservers();

}
