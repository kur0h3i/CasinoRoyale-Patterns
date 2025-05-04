package salas;

import acciones.Mesa;
import acciones.Pasillo;
import juegos.CartaMasAlta;
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
                                new Mesa("CartaMasAlta",1, SalaCartasMapa.posXCartaMas, SalaCartasMapa.posYCartaMas)
                        )
                ),
                new ArrayList<Pasillo>(
                        Arrays.asList(
                        )
                ),
                SalaCartasMapa.posXInicial,
                SalaCartasMapa.posYInicial
        );
    }


    @Override
    public void subscribe(Jugador jugador) {
        if (!Objects.isNull(jugador)) {
            for (Mesa mesa : this.mesas) {
                mesa.setJugador(jugador);
                mesa.putStrategy();
                jugador.attach(mesa);
            }

            for (Pasillo pasillo : this.pasillos) {
                jugador.attach(pasillo);
            }

        }
    }

    @Override
    public void unsubscribe(Jugador jugador) {

    }

    @Override
    public String toString() {
        return "Cartas";
    }
}
