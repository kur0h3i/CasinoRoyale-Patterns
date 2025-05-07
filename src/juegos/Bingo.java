
package juegos;

// Interfaces ASCII para limpiar pantalla y mostrar el cartón de bingo
import ascii.ASCIIGeneral;
import ascii.ASCIIBingo;

// Utilidades de colección, aleatoriedad y entrada de usuario
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

// Excepciones personalizadas para control de fichas
import excep.ExcepcionJugadorSinFichas;

// Representación del jugador
import personas.Jugador;

/**
 * Clase Bingo => Estrategia de juego que implementa la interfaz StrategyJuego.
 * Permite a un jugador apostar fichas, generar cartones de bingo y
 * extraer números de forma aleatoria hasta que alguien consigue bingo.
 */
public class Bingo implements StrategyJuego {

    /** Número de filas por cartón */
    private static final int FILAS = 4;
    /** Número de columnas por cartón */
    private static final int COLUMNAS = 6;
    /** Estructura para almacenar múltiples cartones [jugador][fila][columna] */
    private String[][][] cartones;
    /** Números aún no extraídos (1–75) */
    private HashSet<Integer> numerosDisponibles;
    /** Array de jugadores en la partida */
    private Jugador[] jugadores;
    /** Número total de fichas en juego (bote) */
    private int bote;
    /** Apuesta inicial de fichas</code> */
    private int apuesta;
    /** Jugador principal que inicia la partida */
    private Jugador jugador;
    /** Interfaz ASCII específica para Bingo */
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
     * Inicia la partida de bingo: pide número de jugadores y apuesta,
     * genera cartones y comienza el sorteo de números.
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

        System.out.print("¿Cuántos jugadores participarán? : ");
        int numJugadores = input.nextInt();
        input.nextLine();

        System.out.print("¿Cuántas fichas deseas apostar? : ");
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
        for (int i = 1; i < numJugadores; i++) {
            jugadores[i] = new Jugador("Jugador " + (i + 1), 18, 0.0);
        }

        System.out.println("¡Apuesta aceptada! El bote es de " + bote + " fichas.");
        ASCIIGeneral.esperarTecla();

        generarCartones(numJugadores);
        jugarBingo(numJugadores);
    }

    /**
     * Genera aleatoriamente los cartones de cada jugador.
     * Marca casillas con "X" o con número aleatorio (sin repetir).
     * 
     * @param numJugadores número total de participantes
     */
    private void generarCartones(int numJugadores) {
        Random rand = new Random();
        // Inicializar números disponibles 1–75
        for (int n = 1; n <= 75; n++) {
            numerosDisponibles.add(n);
        }
        // Rellenar cada cartón
        for (int j = 0; j < numJugadores; j++) {
            for (int i = 0; i < FILAS; i++) {
                for (int k = 0; k < COLUMNAS; k++) {
                    if (rand.nextBoolean()) {
                        cartones[j][i][k] = "X";
                    } else {
                        int num;
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
        System.out.println("Tu cartón:");
        imprimirCarton(cartones[0]);
    }

    /**
     * Bucle principal de extracción de números y comprobación de bingo.
     * 
     * @param numJugadores número total de participantes
     */
    private void jugarBingo(int numJugadores) {
        Random rand = new Random();
        while (true) {
            // Reiniciar números si se agotan
            if (numerosDisponibles.isEmpty()) {
                for (int n = 1; n <= 75; n++)
                    numerosDisponibles.add(n);
            }
            int numero = rand.nextInt(75) + 1;
            if (!numerosDisponibles.remove(numero))
                continue;

            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            System.out.println("Número extraído: " + numero);

            // Tachar en cartón principal y mostrar
            tacharNumero(cartones[0], numero);
            System.out.println("Tu cartón actualizado:");
            imprimirCarton(cartones[0]);

            // Comprobar bingo principal
            if (esBingo(cartones[0])) {
                System.out.println("¡Bingo! Has ganado el bote de " + bote + " fichas.");
                jugador.agregarFichas(bote);
                ASCIIGeneral.esperarTecla();
                return;
            }
            // Comprobar bingo en otros jugadores
            for (int i = 1; i < numJugadores; i++) {
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
     * Imprime por pantalla un cartón de bingo.
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
     * Reemplaza en el cartón el número extraído por "X".
     * 
     * @param carton cartón a modificar
     * @param numero número sorteado a tachar
     */
    private void tacharNumero(String[][] carton, int numero) {
        String s = String.valueOf(numero);
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (carton[i][j].equals(s))
                    carton[i][j] = "X";
            }
        }
    }

    /**
     * Comprueba si un cartón está completamente tachado (bingo).
     * 
     * @param carton cartón a evaluar
     * @return true si todas las casillas son "X"
     */
    private boolean esBingo(String[][] carton) {
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
