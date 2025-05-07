
package patterns.observer;

import excep.ExcepcionJugadorSinFichas;

/**
 * Interfaz que define el contrato para un observador
 * en el patron Observer (Pull-Push). Las implementaciones reaccionan a notificaciones
 * de un PullPushModelObservable ejecutando el metodo update.
 */
public interface PullPushModelObserver {

    /**
     * Se invoca cuando el observable notifica un cambio.
     *
     * @param observable sujeto que notifica la actualizacion
     * @param obj dato opcional con informacion adicional
     * @throws ExcepcionJugadorSinFichas si ocurre una falta de fichas al procesar la notificacion
     */
    void update(PullPushModelObservable observable, Object obj) throws ExcepcionJugadorSinFichas;

}