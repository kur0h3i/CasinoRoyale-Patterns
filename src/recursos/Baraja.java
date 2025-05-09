
package recursos;

import java.util.Random;

/**
 * Modelo de una baraja estendar de 52 cartas.
 * Permite mezclar, repartir y reiniciar la baraja.
 */
public class Baraja {

    /** Array que contiene las 52 cartas de la baraja */
    private Carta[] cartas;
    /** Numero de cartas aun disponibles para repartir */
    private Integer cartasDisponibles;

    /**
     * Constructor: crea el array de 52 cartas y las mezcla.
     * Genera todas las combinaciones de valor y palo.
     */
    public Baraja() {
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] palos = {"C", "D", "H", "S"}; // C=Clubs, D=Diamonds, H=Hearts, S=Spades
        cartas = new Carta[52];
        Integer index = 0;
        for (String palo : palos) {
            for (String valor : valores) {
                cartas[index++] = new Carta(valor, palo);
            }
        }
        cartasDisponibles = cartas.length;
        mezclar();
    }

    /**
     * Mezcla aleatoriamente el orden de las cartas en el mazo.
     * Utiliza el algoritmo de Fisher-Yates.
     */
    public void mezclar() {
        Random rnd = new Random();
        for (Integer i = cartas.length - 1; i > 0; i--) {
            Integer j = rnd.nextInt(i + 1);
            Carta temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
        cartasDisponibles = cartas.length;
    }

    /**
     * Reparte la siguiente carta del mazo si hay disponibles.
     * Decrementa el contador de cartas.
     *
     * @return la carta repartida, o null si no quedan cartas.
     */
    public Carta repartir() {
        if (cartasDisponibles <= 0) {
            System.out.println("No hay mes cartas en la baraja.");
            return null;
        }
        return cartas[--cartasDisponibles];
    }

    /**
     * @return numero de cartas que quedan por repartir
     */
    public Integer cartasRestantes() {
        return cartasDisponibles;
    }

    /**
     * Reinicia el mazo a 52 cartas y mezcla de nuevo.
     */
    public void reiniciar() {
        cartasDisponibles = cartas.length;
        mezclar();
    }
}
