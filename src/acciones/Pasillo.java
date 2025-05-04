package acciones;

import mapas.SalaCartasMapa;
import mapas.SalaJuegosAzarMapa;
import salas.Sala;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import personas.Jugador;
import salas.SalaAzar;

import java.util.Objects;

public class Pasillo implements PullPushModelObserver {

    private final Integer posX, posY;
    private final Sala salaSiguiente; // Necesito a que sala me llevara

    public Pasillo(Integer posX, Integer posY, Sala salaSiguiente) {
        this.posX = posX;
        this.posY = posY;
        this.salaSiguiente = salaSiguiente;
    }

    @Override
    public void update(PullPushModelObservable pullPushModelObservable, Object object) {
        if (pullPushModelObservable instanceof Jugador) {
            Jugador jugadorTMP = (Jugador) pullPushModelObservable;
            
            if (Objects.equals(jugadorTMP.getPosX(), this.posX) && Objects.equals(jugadorTMP.getPosY(), this.posY)) {

                jugadorTMP.detachAll();

                if (jugadorTMP.getSalaActual().toString().equals("Cartas")) {
                    jugadorTMP.setPosX(SalaCartasMapa.posXEntrada);
                    jugadorTMP.setPosY(SalaCartasMapa.posYEntrada);
                } else if(jugadorTMP.getSalaActual().toString().equals("Azar")){
                    jugadorTMP.setPosX(SalaJuegosAzarMapa.posXEntrada);
                    jugadorTMP.setPosY(SalaJuegosAzarMapa.posYEntrada);
                } else {
                    jugadorTMP.setPosX(this.salaSiguiente.getPosInitialX());
                    jugadorTMP.setPosY(this.salaSiguiente.getPosInitialY());
                }



                jugadorTMP.setSala(this.salaSiguiente);
                this.salaSiguiente.setJugador(jugadorTMP);
                this.salaSiguiente.iniciarInterfaz();

            }
        }

    }
}
