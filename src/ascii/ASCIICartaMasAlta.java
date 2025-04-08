// ASCIICartaMasAlta.java

package ascii;

// Jugador
import personas.Jugador;

public class ASCIICartaMasAlta {
    
    // Atributos
    Jugador jugador;

    // Constructor
    public ASCIICartaMasAlta(Jugador jugador){
        this.jugador = jugador;
    }

    // Titulo de la Carta Mas Alta
    public void titulo() {
        System.err.println("  ____           _           _    _ _        ");
        System.err.println(" / ___|__ _ _ __| |_ __ _   / \\  | | |_ __ _ ");
        System.err.println("| |   / _` | '__| __/ _` | / _ \\ | | __/ _` |");
        System.err.println("| |__| (_| | |  | || (_| |/ ___ \\| | || (_| |");
        System.err.println(" \\____\\__,_|_|   \\__\\__,_/_/   \\_\\_|\\__\\__,_|");
    }

    // CheetSheet de la Carta Mas Alta
    public void cheatsheet() {
        System.out.println("____________________________ CHEATSHEET _____________________________");
        System.out.println("| Resultado                  | Pago                                 |");
        System.out.println("|----------------------------|--------------------------------------|");
        System.out.println("| Tu carta > Carta IA        | Ganas el doble de tu apuesta         |");
        System.out.println("| Tu carta < Carta IA        | Pierdes tu apuesta                   |");
        System.out.println("| Tu carta == Carta IA       | Recuperas tu apuesta                 |");
        System.out.println("|___________________________________________________________________|");
    }

    // Opciones de la Carta Mas Alta
    public void opciones() {
        jugador.datosUsuarioEnPartida();
        System.out.println("1. Apostar y jugar");
        System.out.println("2. Ver cheatsheet");
        System.out.println("3. Salir");
    }
}

