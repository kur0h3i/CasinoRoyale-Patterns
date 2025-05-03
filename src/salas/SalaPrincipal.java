// SalaPrincipal.java

package salas;

// Util
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

// Aciones Casino
import acciones.Mesa;
import acciones.Pasillo;
import acciones.PuertaSalida;
import acciones.Cajero;

// Excepciones
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;

//Juegos

// Jugador
import mapas.SalaPrincipalMapa;
import personas.Jugador;

// ASCII
import ascii.ASCIIGeneral;



public class SalaPrincipal extends Sala {

    private final Cajero cajero = new Cajero(2, 7);
    private final PuertaSalida puertaSalida = new PuertaSalida(4, 0);

    private Integer posInitialX = SalaPrincipalMapa.posX, posInitialY = SalaPrincipalMapa.posY;

    public SalaPrincipal(Jugador jugador) throws ExcepcionJugadorSinFichas, ExcepcionJugadorSinDinero {
        super(
                jugador,
                SalaPrincipalMapa.mapaSalaPrincipal,
                // Mesas disponibles (agregar las mesas a la lista)
                new ArrayList<Mesa>(
                        Arrays.asList(
                                new Mesa("Ruleta", 1, 9, 4),
                                new Mesa("Bingo", 1, 14, 11),
                                new Mesa("Slot", 1, 25, 11),
                                new Mesa("Dados", 1, 23, 4),
                                new Mesa("Carta Mas Alta", 1, 37, 4)
                        )
                ),
                new ArrayList<Pasillo>(
                        Arrays.asList(
                                new Pasillo(3, 15, new SalaAzar(null)),
                                new Pasillo(0, 22, new SalaCartas(null))
                        )
                )
                );

        //jugador.setFichas(100); // Fichas iniciales del jugador

        jugador.setPosX(posInitialX);
        jugador.setPosY(posInitialY);
        subscribe(jugador);

        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        while (running) {
            ASCIIGeneral.limpiarPantalla();
            interfazPrincipal(jugador, mesas);
            entradaTerminal(scanner, jugador, mesas);
        }

        scanner.close();
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

            jugador.attach(this.cajero);
            jugador.attach(this.puertaSalida);
        }
    }

    @Override
    public void unsubscribe(Jugador jugador) {
        if (!Objects.isNull(jugador)) {
            jugador.detachAll();
        }
    }
}
