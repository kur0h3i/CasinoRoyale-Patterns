
package juegos;

// Interfaces ASCII para limpiar pantalla y mostrar la slot machine
import ascii.ASCIIGeneral;
import ascii.ASCIISlot;

// Utilidades de entrada y control de errores
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Excepción personalizada para falta de fichas
import excep.ExcepcionJugadorSinFichas;

// Representación del jugador
import personas.Jugador;

/**
 * Clase Slot => Estrategia de juego que implementa StrategyJuego.
 * El jugador apuesta fichas y gira una máquina tragamonedas de tres símbolos:
 * - Tres símbolos iguales: premio multiplicado según el símbolo
 * - Dos símbolos iguales: recupera la apuesta
 * - Ninguna coincidencia: pierde la apuesta
 */
public class Slot implements StrategyJuego {

    /** Apuesta actual en fichas */
    private Integer apuesta;
    /** Jugador participante en la partida */
    private Jugador jugador;
    /** Interfaz ASCII específica para Slot */
    private ASCIISlot interfaz;

    /**
     * Constructor: asigna el jugador e inicializa la interfaz ASCII.
     * 
     * @param jugador instancia de Jugador que jugará la partida
     */
    public Slot(Jugador jugador) {
        this.jugador = jugador;
        this.interfaz = new ASCIISlot(jugador);
    }

    /**
     * Comprueba que el jugador tenga al menos una ficha antes de iniciar partida.
     * 
     * @throws ExcepcionJugadorSinFichas si no hay fichas suficientes
     */
    private void comprobarFichas() throws ExcepcionJugadorSinFichas {
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    /**
     * Ejecuta el flujo principal del juego Slot:
     * 1. Verificar fichas.
     * 2. Mostrar menú de juego (apostar, ver tabla de premios, salir).
     * 
     * @throws ExcepcionJugadorSinFichas si no hay fichas disponibles
     */
    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);
        // Verifica que el jugador tenga fichas para jugar
        comprobarFichas();
        boolean continuar = true;
        while (continuar) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opcioes();
            try {
                int opcion = input.nextInt();
                input.nextLine(); // limpiar buffer
                switch (opcion) {
                    case 1:
                        // Apostar y girar la slot
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        apuesta = definirApuesta(input);
                        jugarSlot();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        // Mostrar tabla de premios
                        ASCIIGeneral.limpiarPantalla();
                        interfaz.titulo();
                        interfaz.cheetsheet();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        // Salir del juego
                        continuar = false;
                        System.out.println("Gracias por jugar a Slot. ¡Hasta la próxima!");
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

    /**
     * Solicita al usuario la apuesta en fichas y valida la entrada.
     * 
     * @param input Scanner para leer la entrada del usuario
     * @return cantidad de fichas apostadas
     */
    private Integer definirApuesta(Scanner input) {
        System.out.println("¿Cuántas fichas deseas apostar?");
        System.out.println("Tienes " + jugador.getFichas() + " fichas disponibles.");
        Integer ap = 0;
        try {
            ap = input.nextInt();
            input.nextLine(); // limpiar buffer
            if (ap <= 0 || ap > jugador.getFichas()) {
                System.out.println("Apuesta no válida. Intenta de nuevo.");
                return definirApuesta(input);
            }
            // Descontar fichas
            jugador.restarFichas(ap);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Intenta de nuevo.");
            input.nextLine();
            return definirApuesta(input);
        }
        return ap;
    }

    /**
     * Gira la slot machine generando tres símbolos aleatorios,
     * muestra el resultado y ajusta fichas según combinaciones.
     */
    private void jugarSlot() {
        Random random = new Random();
        String[] simbolos = { "X", "!", "?", "*" };
        String s1 = simbolos[random.nextInt(simbolos.length)];
        String s2 = simbolos[random.nextInt(simbolos.length)];
        String s3 = simbolos[random.nextInt(simbolos.length)];
        interfaz.mostrarResultados(s1, s2, s3);
        if (s1.equals(s2) && s2.equals(s3)) {
            int premio = calcularPremio(s1);
            System.out.println("¡Felicidades! Ganaste " + premio + " fichas.");
            jugador.agregarFichas(premio);
        } else if (s1.equals(s2) || s2.equals(s3) || s1.equals(s3)) {
            System.out.println("¡Has recuperado tu apuesta!");
            jugador.agregarFichas(apuesta);
        } else {
            System.out.println("Lo siento, no ganaste esta vez.");
        }
    }

    /**
     * Calcula el premio según el símbolo triplete.
     * 
     * @param simbolo símbolo ganador
     * @return cantidad de fichas a otorgar
     */
    private Integer calcularPremio(String simbolo) {
        switch (simbolo) {
            case "X":
                return apuesta * 10; // Jackpot
            case "!":
                return apuesta * 7; // Mega premio
            case "?":
                return apuesta * 5; // Buen premio
            case "*":
                return apuesta * 3; // Premio base
            default:
                return 0;
        }
    }

    /**
     * Representación textual de la estrategia para mostrar en menús.
     * 
     * @return "Slots"
     */
    @Override
    public String toString() {
        return "Slots";
    }
}
