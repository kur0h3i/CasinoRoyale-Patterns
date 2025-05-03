// Bingo.java
package juegos;

// ASCII
import ascii.ASCIIGeneral;
import ascii.ASCIIBingo;

// Util
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Excepciones
import excep.ExcepcionJugadorSinFichas;

// Jugador
import personas.Jugador;


public class Bingo implements StrategyJuego {

    // Atributos
    private final Integer FILAS = 4;
    private final Integer COLUMNAS = 6;
    private String[][][] cartones;
    // Usar HashSet para no tener numeros repetidos 
    private HashSet<Integer> numerosDisponibles;
    private Jugador[] jugadores;
    private Integer bote;
    private Integer apuesta;
    private Jugador jugador;
    private ASCIIBingo interfaz;

    // Constructor
    public Bingo(Jugador jugador) {
        this.jugador = jugador;
        numerosDisponibles = new HashSet<>();
        interfaz = new ASCIIBingo();
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

    // Metodos
    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);

        ASCIIGeneral.limpiarPantalla();
        interfaz.titulo();

        System.out.println("Bienvenido al Bingo!");
        System.out.print("¿Cuántos jugadores participarán? : ");
        Integer numJugadores = input.nextInt();
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

        for (Integer i = 1; i < numJugadores; i++) {
            jugadores[i] = new Jugador("Jugador " + i, 18, 0);
        }

        System.out.println("¡Apuesta aceptada! El bote es de " + bote + " fichas.");
        ASCIIGeneral.esperarTecla();

        generarCartones(numJugadores);
        jugarBingo(numJugadores);
    }

    // Generar Cartones
    private void generarCartones(Integer numJugadores) {
        Random random = new Random();

        // Numeros disponibles
        for (Integer i = 1; i <= 75; i++) {
            numerosDisponibles.add(i);
        }

        // Rellenar el carton de bingo
        for (Integer k = 0; k < numJugadores; k++) {
            for (Integer i = 0; i < FILAS; i++) {
                for (Integer j = 0; j < COLUMNAS; j++) {
                    if (random.nextInt(100) < 50) { 
                        cartones[k][i][j] = "X";
                    } else {
                        Integer numero;
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
        for (Integer i = 0; i < FILAS; i++) {
            for (Integer j = 0; j < COLUMNAS; j++) {
                System.out.print(carton[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Juego 
    private void jugarBingo(Integer numJugadores) {
        Random random = new Random();
    
        System.out.println("Comienza el Bingo! Bote total: " + bote + " fichas.");
    
        while (true) {
            Long startTime = System.currentTimeMillis(); // Tiempo de inicio de la iteración
            
            if (numerosDisponibles.isEmpty()) {
                for (Integer i = 1; i <= 75; i++) {
                    numerosDisponibles.add(i); 
                }
            }
    
            Integer numero = random.nextInt(75) + 1;
    
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
    
            for (Integer k = 1; k < numJugadores; k++) {
                tacharNumero(cartones[k], numero);
                if (esBingo(cartones[k])) {
                    System.out.println("¡Bingo! El ganador es " + jugadores[k].getName() + " y se lleva el bote de " + bote + " fichas.");
                    jugadores[k].agregarFichas(bote);
                    ASCIIGeneral.esperarTecla();
                    return;
                }
            }
    
            Long elapsedTime = System.currentTimeMillis() - startTime;
    
            // Intervalo de 1 Segundo
            Long sleepTime = 1000 - elapsedTime;
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
    private void tacharNumero(String[][] carton, Integer numero) {
        for (Integer i = 0; i < FILAS; i++) {
            for (Integer j = 0; j < COLUMNAS; j++) {
                if (carton[i][j].equals(String.valueOf(numero))) {
                    carton[i][j] = "X";
                }
            }
        }
    }

    // Comprueba si es bingo
    private Boolean esBingo(String[][] carton) {
        for (Integer i = 0; i < FILAS; i++) {
            for (Integer j = 0; j < COLUMNAS; j++) {
                if (!carton[i][j].equals("X")) {
                    return false;
                }
            }
        }
        return true;
    }
}
