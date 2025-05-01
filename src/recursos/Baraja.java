// Baraja.java
package recursos;

// Util
import java.util.Random;

public class Baraja {

    // Atributos
    private Carta[] cartas; 
    private int cartasDisponibles; 

    // Constructor
    public Baraja() {
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] tipos = {"C", "D", "H", "S"};
        cartas = new Carta[52]; // Total de cartas en una baraja estándar son 52
        int index = 0;

        // Crear todas las cartas
        for (String tipo : tipos) {
            for (String valor : valores) {
                cartas[index++] = new Carta(valor, tipo);
            }
        }

        cartasDisponibles = 52;
        mezclar();
    }

    // Mezclar las cartas de la baraja
    public void mezclar() {
        Random random = new Random();
        for (int i = 0; i < cartas.length; i++) {
            int randomIndex = random.nextInt(cartas.length);
            Carta temp = cartas[i];
            cartas[i] = cartas[randomIndex];
            cartas[randomIndex] = temp;
        }
    }

    // Repartir cartas de la baraja
    public Carta repartir() {
        if (cartasDisponibles == 0) {
            System.out.println("No hay más cartas en la baraja.");
            return null;
        }
        return cartas[--cartasDisponibles];
    }

    // Numeero de cartas que quedan en la baraja
    public int cartasRestantes() {
        return cartasDisponibles;
    }

    // Reiniciar la baraja
    public void reiniciar() {
        cartasDisponibles = 52; 
        mezclar();
    }
}
