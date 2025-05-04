package acciones;

import salas.Sala;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import personas.Jugador;

import java.util.Objects;

public class Pasillo implements PullPushModelObserver {

    private final Integer posX, posY;
    private final Sala salaPosterior; // Necesito a que sala me llevara

    public Pasillo(Integer posX, Integer posY, Sala salaPosterior) {
        this.posX = posX;
        this.posY = posY;
        this.salaPosterior = salaPosterior;
    }

    @Override
    public void update(PullPushModelObservable pullPushModelObservable, Object object) {
        if (pullPushModelObservable instanceof Jugador) {
            Jugador jugadorTMP = (Jugador) pullPushModelObservable;

            if (Objects.equals(jugadorTMP.getPosX(), this.posX) && Objects.equals(jugadorTMP.getPosY(), this.posY)) {

                jugadorTMP.detachAll();

                jugadorTMP.setPosX(this.salaPosterior.getPosInitialX());
                jugadorTMP.setPosY(this.salaPosterior.getPosInitialY());

                this.salaPosterior.setJugador(jugadorTMP);
                this.salaPosterior.iniciarInterfaz();

            }
        }

    }
}
