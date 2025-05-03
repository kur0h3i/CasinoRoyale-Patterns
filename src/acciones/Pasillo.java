package acciones;

import salas.Sala;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import personas.Jugador;

import java.util.Objects;

public class Pasillo implements PullPushModelObserver {

    private final Integer posX, posY;
    private final Sala sala; // Necesito a que sala me llevara

    public Pasillo(Integer posX, Integer posY, Sala sala) {
        this.posX = posX;
        this.posY = posY;
        this.sala = sala;
    }

    @Override
    public void update(PullPushModelObservable pullPushModelObservable, Object object) {
        Jugador jugadorTMP = (Jugador) pullPushModelObservable;

        if (Objects.equals(jugadorTMP.getPosX(), this.posX) && Objects.equals(jugadorTMP.getPosY(), this.posY)) {
            this.sala.setJugador(jugadorTMP);
            // TODO: Aplicar logica de cambio de sala, la linea anterior ha sido un ejemplo
        }

    }
}
