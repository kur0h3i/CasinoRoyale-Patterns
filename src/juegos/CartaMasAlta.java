// CartaMasAlta.java
package juegos;

// ASCII
import ascii.ASCIIGeneral;
import ascii.ASCIICartaMasAlta;

import java.util.InputMismatchException;
// Util
import java.util.Scanner;

// Recursos
import recursos.Baraja;
import recursos.Carta;

// Jugador
import personas.Jugador;

// Excepcion
import excep.ExcepcionJugadorSinFichas;

public class CartaMasAlta implements StrategyJuego {

    // Atributos
    private Baraja baraja;
    private ASCIICartaMasAlta interfaz;
    Jugador jugador;
    private int apuesta;

    // Constructor
    public CartaMasAlta(Jugador jugador) {
        this.jugador = jugador;
        this.baraja = new Baraja();
        this.interfaz = new ASCIICartaMasAlta(jugador);
    }

    // Deifinir la apuesta
    public int definirApuesta(Scanner input) {
        System.out.println("¿Cuántas fichas deseas apostar?");
        System.out.println("Tienes " + jugador.getFichas() + " fichas disponibles.");

        int apuesta = 0;

        try {
            apuesta = input.nextInt();
            input.nextLine(); // Limpiar buffer

            if (apuesta <= 0 || apuesta > jugador.getFichas()) {
                System.out.println("Apuesta no válida. Intenta de nuevo.");
                return definirApuesta(input); // Llamada recursiva para pedir una apuesta válida
            }

            jugador.restarFichas(apuesta);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Intenta de nuevo.");
            input.nextLine(); // Limpiar buffer en caso de excepción
            return definirApuesta(input); // Llamada recursiva para repetir el proceso
        }

        return apuesta;
    }


    // Comprobar si hay fichas
    public void comprobarfichas() throws ExcepcionJugadorSinFichas{
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);

        comprobarfichas();

        boolean continuar = true;
        while (continuar) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opciones();

            try {
                int opcion = input.nextInt();
                input.nextLine(); 

                switch (opcion) {
                    case 1:
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        apuesta = definirApuesta(input);
                        jugarRonda(apuesta);
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        interfaz.cheatsheet();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        continuar = false;
                        System.out.println("Saliendo ...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                input.nextLine();
            }
        }
    }

    // Jugar ronda
    private void jugarRonda(int apuesta) {
        baraja.mezclar();

        Carta cartaJugador = baraja.repartir();
        Carta cartaIA = baraja.repartir();

        System.out.println("Tu carta: " + cartaJugador);
        System.out.println("Carta de la IA: " + cartaIA);

        int valorJugador = cartaJugador.getValorNumerico();
        int valorIA = cartaIA.getValorNumerico();

        if (valorJugador > valorIA) {
            System.out.println("¡Has ganado esta ronda con " + cartaJugador + "!");
            jugador.agregarFichas(apuesta * 2);
        } else if (valorJugador < valorIA) {
            System.out.println("La IA gana esta ronda con " + cartaIA + ".");
        } else {
            System.out.println("¡Empate! Ambas cartas son iguales.");
            jugador.agregarFichas(apuesta); 
        }
    }
}