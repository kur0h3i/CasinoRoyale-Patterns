package salas;

import acciones.Mesa;
import acciones.Pasillo;
import mapas.SalaCartasMapa;
import mapas.SalaPrincipalMapa;
import personas.Jugador;

import java.util.ArrayList;
import java.util.Arrays;

public class SalaCartas extends Sala {

    public SalaCartas(Jugador jugador) {
        super(jugador, SalaCartasMapa.mapaAzar,
                // Mesas disponibles (agregar las mesas a la lista)
                new ArrayList<Mesa>(
                        Arrays.asList(
                        )
                ),
                new ArrayList<Pasillo>(
                        Arrays.asList(
                        )
                )
        );
    }


    @Override
    public void subscribe(Jugador jugador) {

    }

    @Override
    public void unsubscribe(Jugador jugador) {

    }
}
