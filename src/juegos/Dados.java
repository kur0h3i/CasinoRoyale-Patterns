// Dados.java
package juegos;

// ASCII
import ascii.ASCIIGeneral;
import ascii.ASCIIDados;

import java.util.InputMismatchException;
// Util
import java.util.Random;
import java.util.Scanner;

// Excepcion
import excep.ExcepcionJugadorSinFichas;

// Jugador
import personas.Jugador;

public class Dados implements StrategyJuego {

    // Atributos
    private Integer apuesta;
    private Jugador jugador;
    private ASCIIDados interfaz;

    // Constructor
    public Dados(Jugador jugador) {
        this.jugador = jugador;
        this.interfaz = new ASCIIDados(jugador);
    }

    // Deifinir la apuesta
    public Integer definirApuesta(Scanner input) {
        System.out.println("¿Cuántas fichas deseas apostar?");
        System.out.println("Tienes " + jugador.getFichas() + " fichas disponibles.");

        Integer apuesta = 0;

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

        Boolean continuar = true;
        while (continuar) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opciones();

            try {
                Integer opcion = input.nextInt();
                input.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        apuesta = definirApuesta(input);
                        jugarDados(input);
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        interfaz.cheatsheet();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        continuar = false;
                        System.out.println("Saliendo...");
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

    // Jugar a los dados
    private void jugarDados(Scanner input) {
        Random random = new Random();
        Integer resultado = tirarDados(random);

        if (resultado == 7 || resultado == 11) {
            System.out.println("¡Has sacado " + resultado + "! ¡Ganaste!");
            jugador.agregarFichas(apuesta * 2);
        } else if (resultado == 2 || resultado == 3 || resultado == 12) {
            System.out.println("¡Has sacado " + resultado + "! ¡Perdiste!");
        } else {
            System.out.println("Has sacado " + resultado + ". Este es tu Punto.");
            jugarPunto(input, resultado, random);
        }
    }

    // Jugar el Punto
    private void jugarPunto(Scanner input, Integer punto, Random random) {
        System.out.println("Tira los dados nuevamente para intentar obtener tu Punto: " + punto + ".");
        System.out.println("Si sacas un 7 antes del Punto, pierdes.");

        while (true) {
            System.out.println("Presiona Enter para tirar los dados...");
            input.nextLine();

            Integer resultado = tirarDados(random);

            if (resultado == punto) {
                System.out.println("¡Has sacado tu Punto! ¡Ganaste!");
                jugador.agregarFichas(apuesta * 2);
                break;
            } else if (resultado == 7) {
                System.out.println("¡Has sacado un 7! ¡Perdiste!");
                break;
            } else {
                System.out.println("Has sacado " + resultado + ". Sigue intentando.");
            }
        }
    }

    // Tirar los Dados
    private Integer tirarDados(Random random) {
        Integer dado1 = random.nextInt(6) + 1;
        Integer dado2 = random.nextInt(6) + 1;
        Integer suma = dado1 + dado2;

        interfaz.mostrarResultadoDados(dado1, dado2, suma);
        return suma;
    }

    public String toString() {
        return "Dados";
    }

}