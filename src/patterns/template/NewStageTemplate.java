package patterns.template;

import patterns.observer.Subscription;
import personas.Jugador;

public abstract class NewStageTemplate implements Subscription {

    public abstract void enterNewStage(Jugador jugador);

    public abstract void iniciarInterfaz();

}
