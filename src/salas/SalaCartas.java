package salas;

import acciones.Mesa;
import acciones.Pasillo;
import mapas.SalaCartasMapa;
import personas.Jugador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SalaCartas extends Sala {

    private static SalaCartas salaCartas; // SINGLETON LAZY

    public static SalaCartas getInstance() {
        if (Objects.isNull(salaCartas)) {
            salaCartas = new SalaCartas(null);
        }
        return salaCartas;
    }


    public SalaCartas(Jugador jugador) {
        super(jugador, SalaCartasMapa.mapaCartas,
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

    @Override
    public String toString() {
        return "Cartas";
    }
}
