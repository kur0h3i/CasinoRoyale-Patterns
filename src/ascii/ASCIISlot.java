// ASCIISlot.java
package ascii;

import java.io.Serial;
import java.io.Serializable;

// Jugador
import personas.Jugador;

public class ASCIISlot implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Atributos
    Jugador jugador;

    // constructor
    public ASCIISlot(Jugador jugador) {
        this.jugador = jugador;
    }

    // Titulo de la Slot
    public void titulo() {
        System.out.println(" ____  _       _   ");
        System.out.println("/ ___|| | ___ | |_ ");
        System.out.println("\\___ \\| |/ _ \\| __|");
        System.out.println(" ___) | | (_) | |_ ");
        System.out.println("|____/|_|\\___/ \\__|");
    }

    // CheetSheet de la Slot
    public void cheetsheet() {
        System.out.println("_____________  CHEET SHEET _____________");
        System.out.println("| X X X --> Jackpot! x10 tu apuesta    |");
        System.out.println("| ! ! ! --> Mega premio! x7 tu apuesta |");
        System.out.println("| ? ? ? --> Buen premio! x5 tu apuesta |");
        System.out.println("| * * * --> Premio base! x3 tu apuesta |");
        System.out.println("| Dos simbolos iguales --> Recuperar   |");
        System.out.println("|______________________________________|");
    }

    // Opciones de la Slot
    public void opcioes() {
        jugador.datosUsuarioEnPartida();
        System.out.println("1. Realizar Apuesta");
        System.out.println("2. CheetSheet");
        System.out.println("3. Salir");
    }

    // Mostrar el resultados de la Slot
    public void mostrarResultados(String simbolo1, String simbolo2, String simbolo3) {
        System.out.println("_____________");
        System.out.println("|    SLOT   |");
        System.out.println("|___________|");
        System.out.println("| " + simbolo1 + " | " + simbolo2 + " | " + simbolo3 + " |");
        System.out.println("|___________|");
    }
}
