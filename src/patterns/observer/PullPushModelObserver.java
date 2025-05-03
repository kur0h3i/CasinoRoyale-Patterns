package patterns.observer;

import excep.ExcepcionJugadorSinFichas;

public interface PullPushModelObserver {

    void update(PullPushModelObservable pullPushModelObservable, Object object) throws ExcepcionJugadorSinFichas;
    // void update(PullPushModelObservable pullPushModelObservable);

}
