package salas;

import acciones.Mesa;
import acciones.Pasillo;
import mapas.SalaJuegosAzarMapa;
import personas.Jugador;

import java.util.ArrayList;
import java.util.Objects;

public class SalaAzar extends Sala {

    private static SalaAzar salaAzar; // SINGLETON LAZY

    public static SalaAzar getInstance() {
        if (Objects.isNull(salaAzar)) {
            salaAzar = new SalaAzar(null);
        }
        return salaAzar;
    }

    public SalaAzar(Jugador jugador) {
        super(jugador, SalaJuegosAzarMapa.mapaAzar,
                new ArrayList<Mesa>(),
                new ArrayList<Pasillo>(
                ),
                SalaJuegosAzarMapa.posXInicial, SalaJuegosAzarMapa.posYInicial);
    }

    @Override
    public void subscribe(Jugador jugador) {
        if (!Objects.isNull(jugador)) {
            for (Mesa mesa : this.mesas) {
                jugador.attach(mesa);
            }

            for (Pasillo pasillo : this.pasillos) {
                jugador.attach(pasillo);
            }

        }
    }

    @Override
    public void unsubscribe(Jugador jugador) {
        if (!Objects.isNull(jugador)) {
            jugador.detachAll();
        }
    }

    @Override
    public String toString() {
        return "Azar";
    }
}
