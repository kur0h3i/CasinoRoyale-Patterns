

package personas;

import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinFichas;
import items.Items;
import java.util.Scanner;
import patterns.observer.PullPushModelObservable;
import patterns.observer.PullPushModelObserver;
import salas.Sala;

/**
 * Extension de Jugador que representa un personaje no controlado por el usuario.
 * Se utiliza para simular participantes controlados por la IA en el casino.
 */
public class JugadorNPC extends Jugador {

    /**
     * Constructor de JugadorNPC.
     *
     * @param nombre nombre del NPC
     * @param edad edad del NPC
     * @param dinero saldo inicial en euros
     */
    public JugadorNPC(String nombre, int edad, double dinero) {
        super(nombre, edad, dinero);
    }

    /**
     * Muestra los datos del NPC en el juego de forma resumida.
     * Se sobreescribe para identificar al personaje como NPC.
     */
    @Override
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("NPC: " + getName());
        System.out.println("Fichas: " + getFichas());
        System.out.println("----------------------------");
    }
}
