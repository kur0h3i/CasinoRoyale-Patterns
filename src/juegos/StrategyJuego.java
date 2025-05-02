package juegos;

import java.util.InputMismatchException;
// Util
import java.util.Scanner;

// Excepciones
import excep.ExcepcionJugadorSinFichas;

// Jugador
import personas.Jugador;

public interface StrategyJuego {

    void iniciarPartida() ;

    int definirApuesta();

    void comprobarfichas();

}
