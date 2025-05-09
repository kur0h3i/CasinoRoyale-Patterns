// ASCIICajero.java

package ascii;

import java.io.Serial;
import java.io.Serializable;

// Jugador
import personas.Jugador;

public class ASCIICajero implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    // Atributos
    Jugador jugador;

    // Constructor
    public ASCIICajero(Jugador jugador) {
        this.jugador = jugador;
    }

    // Metodos
    // Titulo del cajero
    public void titulo() {
        System.out.println("  ____       _                ");
        System.out.println(" / ___|__ _ (_) ___ _ __ ___  ");
        System.out.println("| |   / _` || |/ _ \\ '__/ _ \\ ");
        System.out.println("| |__| (_| || |  __/ | | (_) |");
        System.out.println(" \\____\\__,_|/ |\\___|_|  \\___/ ");
        System.out.println("          |__/                ");
    }

    // Opciones del cajero
    public void opcioes() {
        System.out.println("------------------------------");
        System.out.println(jugador);
        System.out.println("\n-----------------------------");
        System.out.println("1. Dinero --> Fichas");
        System.out.println("2. Fichas --> Dinero");
        System.out.println("3. Salir");
    }

}
