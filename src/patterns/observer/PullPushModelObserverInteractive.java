package patterns.observer;

import excep.ExcepcionJugadorSinFichas;

public interface PullPushModelObserverInteractive extends PullPushModelObserver {

    void interactive() throws ExcepcionJugadorSinFichas;

}
