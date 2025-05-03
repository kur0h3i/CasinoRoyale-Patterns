package salas;

import acciones.Mesa;
import acciones.Pasillo;
import mapas.SalaJuegosAzarMapa;
import mapas.SalaPrincipalMapa;
import personas.Jugador;

import java.util.ArrayList;

public class SalaAzar extends Sala {

    private Integer posInitialX = SalaJuegosAzarMapa.posX, posInitialY = SalaJuegosAzarMapa.posY;

    public SalaAzar(Jugador jugador) {
        super(jugador, SalaJuegosAzarMapa.mapaAzar,
                new ArrayList<Mesa>(), new ArrayList<Pasillo>());
    }

    @Override
    public void subscribe(Jugador jugador) {

    }

    @Override
    public void unsubscribe(Jugador jugador) {

    }
}
