package patterns.observer;

import personas.Jugador;

public abstract class Subscription {

    public abstract void subscribe(Jugador jugador);

    public abstract void unsubscribe(Jugador jugador);

}
