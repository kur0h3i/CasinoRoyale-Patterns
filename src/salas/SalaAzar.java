package salas;

import acciones.Mesa;
import acciones.Pasillo;
import mapas.SalaJuegosAzarMapa;
import mapas.SalaPrincipalMapa;
import personas.Jugador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SalaAzar extends Sala {



    public SalaAzar(Jugador jugador) {
        super(jugador, SalaJuegosAzarMapa.mapaAzar,
                new ArrayList<Mesa>(),
                new ArrayList<Pasillo>(
                        Arrays.asList(
                                // new Pasillo(3, 0, )
                        )
                ),
                SalaJuegosAzarMapa.posX, SalaJuegosAzarMapa.posY);
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
}
