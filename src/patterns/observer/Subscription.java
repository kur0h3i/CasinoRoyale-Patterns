package patterns.observer;

import personas.Jugador;

public interface Subscription {

    void subscribe(Jugador jugador);
    void unsubscribe(Jugador jugador);

}
