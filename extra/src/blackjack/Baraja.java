package blackjack;
import java.util.Random;

public class Baraja {

    // Atributos
    private Carta[] cartas; // Arreglo de cartas
    private int cartasDisponibles; // Índice para rastrear las cartas restantes

    // Constructor
    public Baraja() {
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] tipos = {"C", "D", "H", "S"};
        cartas = new Carta[52]; // Total de cartas en una baraja estándar
        int index = 0;

        // Crear todas las cartas
        for (String tipo : tipos) {
            for (String valor : valores) {
                cartas[index++] = new Carta(valor, tipo);
            }
        }

        cartasDisponibles = 52; // Todas las cartas están disponibles inicialmente
        mezclar();
    }

    // Método para mezclar las cartas
    public void mezclar() {
        Random random = new Random();
        for (int i = 0; i < cartas.length; i++) {
            int randomIndex = random.nextInt(cartas.length);
            // Intercambiar cartas[i] y cartas[randomIndex]
            Carta temp = cartas[i];
            cartas[i] = cartas[randomIndex];
            cartas[randomIndex] = temp;
        }
    }

    // Método para repartir una carta
    public Carta repartir() {
        if (cartasDisponibles == 0) {
            System.out.println("No hay más cartas en la baraja.");
            return null;
        }
        return cartas[--cartasDisponibles]; // Retorna la última carta disponible y decrementa
    }

    // Método para obtener el número de cartas restantes
    public int cartasRestantes() {
        return cartasDisponibles;
    }

    // Método para reiniciar la baraja
    public void reiniciar() {
        cartasDisponibles = 52; // Todas las cartas vuelven a estar disponibles
        mezclar();
    }
}
