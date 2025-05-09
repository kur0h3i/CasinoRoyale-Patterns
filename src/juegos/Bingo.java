
package juegos;

// Interfaces ASCII para limpiar pantalla y mostrar el carton de bingo
import ascii.ASCIIGeneral;
import ascii.ASCIIBingo;

import java.io.Serial;
import java.io.Serializable;
// Utilidades de coleccion, aleatoriedad y entrada de usuario
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

// Excepciones personalizadas para control de fichas
import excep.ExcepcionJugadorSinFichas;

// Representacion del jugador
import personas.Jugador;

/**
 * Estrategia de juego que implementa la interfaz StrategyJuego.
 * Permite a un jugador apostar fichas, generar cartones de bingo y
 * extraer numeros de forma aleatoria hasta que alguien consigue bingo.
 */
public class Bingo implements StrategyJuego, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Numero de filas por carton */
    private static final Integer FILAS = 4;
    /** Numero de columnas por carton */
    private static final Integer COLUMNAS = 6;
    /** Estructura para almacenar multiples cartones [jugador][fila][columna] */
    private String[][][] cartones;
    /** Numeros aun no extraidos (1–75) */
    private HashSet<Integer> numerosDisponibles;
    /** Array de jugadores en la partida */
    private Jugador[] jugadores;
    /** Numero total de fichas en juego (bote) */
    private Integer bote;
    /** Apuesta inicial de fichas</code> */
    private Integer apuesta;
    /** Jugador principal que inicia la partida */
    private Jugador jugador;
    /** Interfaz ASCII especifica para Bingo */
    private ASCIIBingo interfaz;

    /**
     * Constructor: inicializa la partida de bingo para un jugador.
     * 
     * @param jugador objeto Jugador que juega
     */
    public Bingo(Jugador jugador) {
        this.jugador = jugador;
        this.numerosDisponibles = new HashSet<>();
        this.interfaz = new ASCIIBingo();
    }

    /**
     * Inicia la partida de bingo: pide numero de jugadores y apuesta,
     * genera cartones y comienza el sorteo de numeros.
     * 
     * @throws ExcepcionJugadorSinFichas si no hay fichas suficientes para la
     *                                   apuesta
     */
    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);
        ASCIIGeneral.limpiarPantalla();
        interfaz.titulo();
        System.out.println("Bienvenido al Bingo!");

        System.out.print("¿Cuentos jugadores participaren? : ");
        Integer numJugadores = input.nextInt();
        input.nextLine();

        System.out.print("¿Cuentas fichas deseas apostar? : ");
        apuesta = input.nextInt();
        input.nextLine();

        if (apuesta > jugador.getFichas()) {
            throw new ExcepcionJugadorSinFichas("No tienes suficientes fichas para la apuesta inicial.");
        }

        // Restar fichas del jugador principal y calcular bote
        jugador.restarFichas(apuesta);
        bote = apuesta * numJugadores;

        // Crear arrays para jugadores y cartones
        jugadores = new Jugador[numJugadores];
        cartones = new String[numJugadores][FILAS][COLUMNAS];
        jugadores[0] = jugador;
        // Jugadores adicionales con fichas iniciales cero
        for (Integer i = 1; i < numJugadores; i++) {
            jugadores[i] = new Jugador("Jugador " + (i + 1), 18, 0.0);
        }

        System.out.println("¡Apuesta aceptada! El bote es de " + bote + " fichas.");
        ASCIIGeneral.esperarTecla();

        generarCartones(numJugadores);
        jugarBingo(numJugadores);
    }

    /**
     * Genera aleatoriamente los cartones de cada jugador.
     * Marca casillas con "X" o con numero aleatorio (sin repetir).
     * 
     * @param numJugadores numero total de participantes
     */
    private void generarCartones(Integer numJugadores) {
        Random rand = new Random();
        // Inicializar numeros disponibles 1–75
        for (Integer n = 1; n <= 75; n++) {
            numerosDisponibles.add(n);
        }
        // Rellenar cada carton
        for (Integer j = 0; j < numJugadores; j++) {
            for (Integer i = 0; i < FILAS; i++) {
                for (Integer k = 0; k < COLUMNAS; k++) {
                    if (rand.nextBoolean()) {
                        cartones[j][i][k] = "X";
                    } else {
                        Integer num;
                        do {
                            num = rand.nextInt(75) + 1;
                        } while (!numerosDisponibles.contains(num));
                        numerosDisponibles.remove(num);
                        cartones[j][i][k] = String.valueOf(num);
                    }
                }
            }
        }
        ASCIIGeneral.limpiarPantalla();
        System.out.println("Tu carton:");
        imprimirCarton(cartones[0]);
    }

    /**
     * Bucle principal de extraccion de numeros y comprobacion de bingo.
     * 
     * @param numJugadores numero total de participantes
     */
    private void jugarBingo(Integer numJugadores) {
        Random rand = new Random();
        while (true) {
            // Reiniciar numeros si se agotan
            if (numerosDisponibles.isEmpty()) {
                for (Integer n = 1; n <= 75; n++)
                    numerosDisponibles.add(n);
            }
            Integer numero = rand.nextInt(75) + 1;
            if (!numerosDisponibles.remove(numero))
                continue;

            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            System.out.println("Numero extraido: " + numero);

            // Tachar en carton principal y mostrar
            tacharNumero(cartones[0], numero);
            System.out.println("Tu carton actualizado:");
            imprimirCarton(cartones[0]);

            // Comprobar bingo principal
            if (esBingo(cartones[0])) {
                System.out.println("¡Bingo! Has ganado el bote de " + bote + " fichas.");
                jugador.agregarFichas(bote);
                ASCIIGeneral.esperarTecla();
                return;
            }
            // Comprobar bingo en otros jugadores
            for (Integer i = 1; i < numJugadores; i++) {
                tacharNumero(cartones[i], numero);
                if (esBingo(cartones[i])) {
                    System.out.println(
                            "¡Bingo! El ganador es " + jugadores[i].getName() + " y se lleva " + bote + " fichas.");
                    jugadores[i].agregarFichas(bote);
                    ASCIIGeneral.esperarTecla();
                    return;
                }
            }
            // Pausa de 1 segundo entre llamadas
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * Imprime por pantalla un carton de bingo.
     * 
     * @param carton matriz [FILAS][COLUMNAS] de String
     */
    private void imprimirCarton(String[][] carton) {
        for (String[] fila : carton) {
            for (String celda : fila) {
                System.out.print(celda + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Reemplaza en el carton el numero extraido por "X".
     * 
     * @param carton carton a modificar
     * @param numero numero sorteado a tachar
     */
    private void tacharNumero(String[][] carton, Integer numero) {
        String s = String.valueOf(numero);
        for (Integer i = 0; i < FILAS; i++) {
            for (Integer j = 0; j < COLUMNAS; j++) {
                if (carton[i][j].equals(s))
                    carton[i][j] = "X";
            }
        }
    }

    /**
     * Comprueba si un carton este completamente tachado (bingo).
     * 
     * @param carton carton a evaluar
     * @return true si todas las casillas son "X"
     */
    private Boolean esBingo(String[][] carton) {
        for (String[] fila : carton) {
            for (String celda : fila) {
                if (!celda.equals("X"))
                    return false;
            }
        }
        return true;
    }

    /**
     * Retorna la cadena identificadora de la estrategia (para display).
     */
    @Override
    public String toString() {
        return "Bingo";
    }
}
