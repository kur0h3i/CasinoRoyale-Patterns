package salas;

import acciones.Mesa;
import acciones.Pasillo;
import ascii.ASCIIGeneral;
import patterns.observer.Subscription;
import personas.Jugador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Mensajes
import static recursos.MensajesEstaticos.*;

public abstract class Sala implements Subscription {

    private Character[][] mapa;
    private Jugador jugador;
    protected ArrayList<Mesa> mesas;
    protected ArrayList<Pasillo> pasillos;
    private Integer posInitialX, posInitialY;
    protected Integer posLastTimeSeenX, posLastTimeSeenY;

    Sala(Jugador jugador, Character[][] mapa) {
        this(jugador, mapa, new ArrayList<Mesa>());
    }

    Sala(Jugador jugador, Character[][] mapa, ArrayList<Mesa> mesas) {
        this(jugador, mapa, mesas, new ArrayList<Pasillo>());
    }

    Sala(Jugador jugador, Character[][] mapa,
                  ArrayList<Mesa> mesas, ArrayList<Pasillo> pasillos) {
        this(jugador, mapa, mesas, pasillos, 0, 0);
    }

    Sala(Jugador jugador, Character[][] mapa,
         ArrayList<Mesa> mesas, ArrayList<Pasillo> pasillos,
         Integer posInitialX, Integer posInitialY) {
        this.jugador = jugador;
        this.mapa = mapa;
        this.mesas = mesas;
        this.pasillos = pasillos;
        this.posInitialX = posInitialX;
        this.posInitialY = posInitialY;

        this.posLastTimeSeenX = posInitialX;
        this.posLastTimeSeenY = posInitialY;

        if (jugador != null) { // Para iniciar de forma natural la posicion del jugador en las primeras instancias del programa
            if (jugador.getPosX() == null || jugador.getPosY() == null) {
                jugador.setPosX(this.posInitialX);
                jugador.setPosY(this.posInitialY);
            }
        }

    }

    protected void interfazPrincipal(Jugador jugador, ArrayList<Mesa> mesas){
        // Interfaz del jugador
        playerUI(jugador);

        // Mostrar el mapa del casino
        mostrarMapa();

        // Instrucciones de control
        instructions();

        // Interacción con mesas // TODO

    }

    // Mover jugador en el mapa
    // TODO: SUGERENCIA 1
    protected void moverJugador(Integer dx, Integer dy) {
        Integer nuevaPosX = jugador.getPosX() + dx;
        Integer nuevaPosY = jugador.getPosY() + dy;

        // Comprobar que la nueva posición esté dentro de los límites
        if (nuevaPosX >= 0 && nuevaPosX < mapa[0].length && nuevaPosY >= 0 && nuevaPosY < mapa.length
                && mapa[nuevaPosY][nuevaPosX] == ' ') {
            jugador.move(nuevaPosX, nuevaPosY);
        }
    }

    // Mostrar mapa del casino (Jugador P)
    private void mostrarMapa() {
        for (Integer i = 0; i < mapa.length; i++) {
            for (Integer j = 0; j < mapa[i].length; j++) {
                if (i == jugador.getPosY() && j == jugador.getPosX()) {
                    System.out.print("P ");
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Manejo de la Terminal
    protected void entradaTerminal(Scanner scanner, Jugador jugador, ArrayList<Mesa> mesas) {
        Boolean validInput = false;

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

    public void iniciarInterfaz() {
        Boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            ASCIIGeneral.limpiarPantalla();
            interfazPrincipal(jugador, mesas);
            entradaTerminal(scanner, jugador, mesas);
        }

        scanner.close();
    }



    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        subscribe(jugador);
    }

    public Integer getPosInitialX() {
        return posInitialX;
    }

    public Integer getPosInitialY() {
        return posInitialY;
    }

    public ArrayList<Pasillo> getPasillos() {
        return pasillos;
    }
}
