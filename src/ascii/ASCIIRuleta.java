//ASCUIRuleta
package ascii;

//Jugador
import personas.Jugador;

public class ASCIIRuleta {
    
    // Atributos
    Jugador jugador;

    // Metodos
    public ASCIIRuleta (Jugador jugador){
        this.jugador = jugador;
    }

    // Titulo de la Ruleta
    public void titulo(){
        System.out.println("____        _      _        ");
        System.out.println("|  _ \\ _   _| | ___| |_ __ _ ");
        System.out.println("| |_) | | | | |/ _ \\ __/ _` |");
        System.out.println("|  _ <| |_| | |  __/ || (_| |");
        System.out.println("|_| \\_\\\\__,_|_|\\___|\\__\\__,_|");

    }

    // Mesa de la Ruleta
    public void interfazRuleta() {
        System.out.println("_________________________________________________________________________");
        System.out.println("|     | 3 | 6 | 9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | 2 to 1 |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|     | 2 | 5 | 8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | 2 to 1 |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|  0  | 1 | 4 | 7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | 2 to 1 |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|     |       1st 12        |       2nd 12      |         3rd 12        |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|     |  1 to 18  |   PAR   |   RED   |  BLACK  |  IMPAR  |   19 to 36  |");
        System.out.println("|_____|_________________________________________________________________|");
    }

    // CheetSheet de la Ruleta
    public void cheetsheet() {
        System.out.println("__________________________ CHEET SHEET __________________________");
        System.out.println("| Tipo de Apuesta         | Ejemplo        | Pago               |");
        System.out.println("|-------------------------|----------------|--------------------|");
        System.out.println("| Número Individual       | 7              | x35                |");
        System.out.println("| Docena (1st, 2nd, 3rd)  | 1st 12         | x2                 |");
        System.out.println("| Mitad (1-18 o 19-36)    | 1-18           | x1                 |");
        System.out.println("| Rojo / Negro            | Rojo           | x1                 |");
        System.out.println("| Par / Impar             | Par            | x1                 |");
        System.out.println("|_______________________________________________________________|");
    }

    // Opciones de la apuesta de la Ruleta
    public void interfazPartida(){
            jugador.datosUsuarioEnPartida();
            System.out.println("_______________________");
            System.out.println("| Opciones de Apuesta   |");
            System.out.println("| 1. Color (Rojo/Negro) |");
            System.out.println("| 2. Par / Impar        |");
            System.out.println("| 3. Número Individual  |");
            System.out.println("| 4. Docena             |");
            System.out.println("| 5. Mitad              |");
            System.out.println("| 6. Salir              |");
            System.out.println("|_______________________|");
    }

    // Opciones de la Ruleta
    public void opciones(){
        jugador.datosUsuarioEnPartida();
        System.out.println("1. Realizar Apuesta");
        System.out.println("2. Girar Ruleta");
        System.out.println("3. CheetSheet");
        System.out.println("4. Salir");
    }
    
}
