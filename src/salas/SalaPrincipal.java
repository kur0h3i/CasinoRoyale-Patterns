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

/**
 * Clase unica donde aparecera el jugador, heredado de la clase padre Sala.
 * Existen elementos en esta sala como el Cajero, Puerta de Salida y el bar Fenrir
 * @see salas.Sala
 * @author Luis Fidel Blanco Grau
 * @version 1.0
 * */
public class SalaPrincipal extends Sala {

    /**
     * Elemento Singleton
     * */
    static SalaPrincipal salaPrincipal; // SINGLETON LAZY

    /**
     * Metodo de invocacion por Singleton
     * @param jugador Personaje para que este presente en la sala, si es null, se actualizara mediante getters y setters.
     */
    public static SalaPrincipal getInstance(Jugador jugador) {
        if (Objects.isNull(salaPrincipal)) {
            salaPrincipal = new SalaPrincipal(jugador);
        }
        return salaPrincipal;
    }
    /**
     * Metodo de invocacion por Singleton
     */
    public static SalaPrincipal getInstance() {
        return getInstance(null);
    }

    /**
     * Elemento Cajero que aparecera en la sala Principal
     * @see Cajero
     * */
    private final Cajero cajero = new Cajero(2, 7);
    /**
     * Elemento Cajero que aparecera en la sala Principal
     * @see PuertaSalida
     * */
    private final PuertaSalida puertaSalida = new PuertaSalida(4, 0);
    /**
     * Elemento Cajero que aparecera en la sala Principal
     * @see Fenrir
     */
    private final Fenrir fenrir = new Fenrir(12, 5);

    /**
     * Constructor de Sala Principal
     * Se almacena los datos en la clase padre. Ejecuta una funcion para subscribirse el jugador los observadores.
     * @param jugador Personaje para que este presente en la sala, si es null, se actualizara mediante getters y setters
     * */
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
