// SalaPrincipal.java

package salas;

// Util
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Aciones Casino
import acciones.Mesa;
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

// Mensajes
import static recursos.MensajesEstaticos.*;


public class SalaPrincipal {

    char[][] mapa = SalaPrincipalMapa.mapaSalaPrincipal;
    // int posX = MapaCasino.posX;
    // int posY = MapaCasino.posY;
    Jugador jugador;

    public SalaPrincipal(Jugador jugador) throws ExcepcionJugadorSinFichas, ExcepcionJugadorSinDinero {
        Scanner scanner = new Scanner(System.in);
        this.jugador = jugador;

        //jugador.setFichas(100); // Fichas iniciales del jugador

        // Mesas disponibles (agregar las mesas a la lista)
        ArrayList<Mesa> mesas = new ArrayList<>();
        // Update the Mesa constructor to accept a generic Juego type
        mesas.add(new Mesa("Ruleta", 1, 9, 4));
        mesas.add(new Mesa("Bingo", 1, 14, 11));
        mesas.add(new Mesa("Slot", 1, 25, 11));
        mesas.add(new Mesa("Dados", 1, 23, 4));
        mesas.add(new Mesa("Carta Mas Alta", 1, 37, 4));

        for (Mesa mesa : mesas) {
            jugador.attach(mesa);
        }

        jugador.attach(new Cajero(2, 7));
        jugador.attach(new PuertaSalida(4, 0));

        boolean running = true;
        while (running) {
            ASCIIGeneral.limpiarPantalla();
            interfazPrincipal(jugador, mesas);
            entradaTerminal(scanner, jugador, mesas);
        }

        scanner.close();
    }


    public void interfazPrincipal(Jugador jugador, ArrayList<Mesa> mesas){
        // Interfaz del jugador
        playerUI(jugador);

        // Mostrar el mapa del casino
        mostrarMapa();

        // Instrucciones de control
        instructions();

        // Interacción con mesas // TODO

    }

    // Mostrar mapa del casino (Jugador P)
    public void mostrarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == jugador.getPosY() && j == jugador.getPosX()) {
                    System.out.print("P "); 
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Mover jugador en el mapa
    // TODO: SUGERENCIA 1
    public void moverJugador(int dx, int dy) {
        Integer nuevaPosX = jugador.getPosX() + dx;
        Integer nuevaPosY = jugador.getPosY() + dy;

        // Comprobar que la nueva posición esté dentro de los límites
        if (nuevaPosX >= 0 && nuevaPosX < mapa[0].length && nuevaPosY >= 0 && nuevaPosY < mapa.length
                && mapa[nuevaPosY][nuevaPosX] == ' ') {
            jugador.move(nuevaPosX, nuevaPosY);
        }
    }

    // Manejo de la Terminal
    public void entradaTerminal(Scanner scanner, Jugador jugador, ArrayList<Mesa> mesas) throws ExcepcionJugadorSinFichas, ExcepcionJugadorSinDinero {
        boolean validInput = false;
        
        while (!validInput) {
            
            try {
                String input = scanner.nextLine().toLowerCase();
                validInput = true;
                switch (input) {
                    case "w":
                        moverJugador(0, -1);
                        break;
                    case "s":
                        moverJugador(0, 1);
                        break;
                    case "a":
                        moverJugador(-1, 0);
                        break;
                    case "d":
                        moverJugador(1, 0);
                        break;
                    case "e":
                        System.out.println("He pulsado la tecla E!");
                        jugador.interacting();
                        break;
                    default:
                        validInput = false;  
                        badCommand();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un comando válido.");
                scanner.nextLine(); 
                validInput = false;  
            }
        }
    }
}
