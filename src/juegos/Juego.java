// Juego.java

package juegos;

import java.util.InputMismatchException;
// Util
import java.util.Scanner;

// Excepciones
import excep.ExcepcionJugadorSinFichas;

// Jugador
import personas.Jugador;

public abstract class Juego {
    
    // Atributos 
    private Jugador jugador;

    // constructores
    public Juego(Jugador jugador){
        this.jugador = jugador;
    }

    // Todos deben iniciar partida
    public abstract void iniciarPartida() throws ExcepcionJugadorSinFichas;
    
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
}
