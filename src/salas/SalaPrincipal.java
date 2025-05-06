// SalaPrincipal.java

package salas;

// Util
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

// Aciones Casino
import acciones.Mesa;
import acciones.Pasillo;
import acciones.PuertaSalida;
import acciones.Cajero;
import acciones.Fenrir;

// Excepciones

//Juegos

// Jugador
import mapas.SalaPrincipalMapa;
import personas.Jugador;

// ASCII


public class SalaPrincipal extends Sala {

    static SalaPrincipal salaPrincipal; // SINGLETON LAZY

    public static SalaPrincipal getInstance(Jugador jugador) {
        if (Objects.isNull(salaPrincipal)) {
            salaPrincipal = new SalaPrincipal(jugador);
        }
        return salaPrincipal;
    }

    public static SalaPrincipal getInstance() {
        return getInstance(null);
    }

    // private final Fenrir fenrir = new Fenrir(); // TODO
    private final Cajero cajero = new Cajero(2, 7);
    private final PuertaSalida puertaSalida = new PuertaSalida(4, 0);
    private final Fenrir fenrir = new Fenrir(12, 5);
    public SalaPrincipal(Jugador jugador) {
        super(
                jugador,
                SalaPrincipalMapa.mapaSalaPrincipal,
                // Mesas disponibles (agregar las mesas a la lista)
                new ArrayList<Mesa>(),
                new ArrayList<Pasillo>(), // IMPOSIBLE DE INSTANCIAR VARIOS PASILLOS E INTERCONECTAR POR ACA, MIGRANDO ESTO A MAIN (StackOverflowException)
                SalaPrincipalMapa.posXInicial,
                SalaPrincipalMapa.posYInicial
                );

        //jugador.setFichas(100); // Fichas iniciales del jugador

        subscribe(jugador);
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
            jugador.attach(this.fenrir);
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
        return "Principal";
    }
}
