// Bingo.java
package juegos;

// ASCII
import ascii.ASCIIGeneral;
import ascii.ASCIIBingo;

// Util
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

// Excepciones
import excep.ExcepcionJugadorSinFichas;

// Jugador
import personas.Jugador;


public class Bingo extends Juego {

    // Atributos
    private final int FILAS = 4;
    private final int COLUMNAS = 6;
    private String[][][] cartones;
    // Usar HashSet para no tener numeros repetidos 
    private HashSet<Integer> numerosDisponibles;
    private Jugador[] jugadores;
    private int bote;
    private int apuesta;
    private Jugador jugador;
    private ASCIIBingo interfaz;

    // Constructor
    public Bingo(Jugador jugador) {
        super(jugador);
        this.jugador = jugador;
        numerosDisponibles = new HashSet<>();
        interfaz = new ASCIIBingo();
    }

    // Metodos
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

        bote = apuesta * numJugadores;
        jugadores = new Jugador[numJugadores];
        cartones = new String[numJugadores][FILAS][COLUMNAS];

        jugadores[0] = jugador;
        jugador.restarFichas(apuesta);

        for (int i = 1; i < numJugadores; i++) {
            jugadores[i] = new Jugador("Jugador " + i, 18, 0);
        }

        System.out.println("¡Apuesta aceptada! El bote es de " + bote + " fichas.");
        ASCIIGeneral.esperarTecla();

        generarCartones(numJugadores);
        jugarBingo(numJugadores);
    }

    // Generar Cartones
    private void generarCartones(int numJugadores) {
        Random random = new Random();

        // Numeros disponibles
        for (int i = 1; i <= 75; i++) {
            numerosDisponibles.add(i);
        }

        // Rellenar el carton de bingo
        for (int k = 0; k < numJugadores; k++) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (random.nextInt(100) < 50) { 
                        cartones[k][i][j] = "X";
                    } else {
                        int numero;
                        do {
                            numero = random.nextInt(75) + 1;
                        } while (!numerosDisponibles.contains(numero));
                        cartones[k][i][j] = String.valueOf(numero);
                    }
                }
            }
        }

        ASCIIGeneral.limpiarPantalla();
        System.out.println("Tu cartón:");
        // Carton del jugador Principal
        imprimirCarton(cartones[0]); 
    }

    // Mostrar el carton
    private void imprimirCarton(String[][] carton) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(carton[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Juego 
    private void jugarBingo(int numJugadores) {
        Random random = new Random();
    
        System.out.println("Comienza el Bingo! Bote total: " + bote + " fichas.");
    
        while (true) {
            long startTime = System.currentTimeMillis(); // Tiempo de inicio de la iteración
            
            if (numerosDisponibles.isEmpty()) {
                for (int i = 1; i <= 75; i++) {
                    numerosDisponibles.add(i); 
                }
            }
    
            int numero = random.nextInt(75) + 1;
    
            if (!numerosDisponibles.contains(numero)) {
                continue; // Evitar números repetidos
            }
    
            numerosDisponibles.remove(numero);
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            System.out.println("Número extraído: " + numero);
    
            tacharNumero(cartones[0], numero);
            System.out.println("Tu cartón actualizado:");
            imprimirCarton(cartones[0]);
    
            if (esBingo(cartones[0])) {
                System.out.println("¡Bingo! Has ganado el bote de " + bote + " fichas.");
                jugador.agregarFichas(bote);
                ASCIIGeneral.esperarTecla();
                return;
            }
    
            for (int k = 1; k < numJugadores; k++) {
                tacharNumero(cartones[k], numero);
                if (esBingo(cartones[k])) {
                    System.out.println("¡Bingo! El ganador es " + jugadores[k].getName() + " y se lleva el bote de " + bote + " fichas.");
                    jugadores[k].agregarFichas(bote);
                    ASCIIGeneral.esperarTecla();
                    return;
                }
            }
    
            long elapsedTime = System.currentTimeMillis() - startTime;
    
            // Intervalo de 1 Segundo
            long sleepTime = 1000 - elapsedTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Error en la pausa: " + e.getMessage());
                }
            }
        }
    }
    
    // Tacha un numero en el carton
    private void tacharNumero(String[][] carton, int numero) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (carton[i][j].equals(String.valueOf(numero))) {
                    carton[i][j] = "X";
                }
            }
        }
    }

    // Comprueba si es bingo
    private boolean esBingo(String[][] carton) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!carton[i][j].equals("X")) {
                    return false;
                }
            }
        }
        return true;
    }
}
