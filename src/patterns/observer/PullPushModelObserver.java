
package patterns.observer;

import excep.ExcepcionJugadorSinFichas;

/**
 * Interfaz que define el contrato para un observador
 * en el patrón Observer (Pull-Push). Las implementaciones reaccionan a notificaciones
 * de un PullPushModelObservable ejecutando el método update.
 */
public interface PullPushModelObserver {

    /**
     * Se invoca cuando el observable notifica un cambio.
     *
     * @param observable sujeto que notifica la actualización
     * @param obj dato opcional con información adicional
     * @throws ExcepcionJugadorSinFichas si ocurre una falta de fichas al procesar la notificación
     */
    void update(PullPushModelObservable observable, Object obj) throws ExcepcionJugadorSinFichas;

}