package blackjack;
public class Jugador {

    private Carta[] mano; // Arreglo para guardar las cartas del jugador
    private Integer numeroCartas; // Numero de cartas en la mano
    private String nombre; // Nombre del jugador
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Carta[11]; // Un jugador no puede tener mes de 11 cartas sin pasarse de 21
        this.numeroCartas = 0;
    }

    // Anadir una carta a la mano
    public void recibirCarta(Carta carta) {
        if (numeroCartas < mano.length) {
            mano[numeroCartas++] = carta;
        } else {
            System.out.println("No se pueden recibir mes cartas.");
        }
    }

    // Calcular el puntaje de la mano
    public Integer calcularPuntaje() {
        Integer puntaje = 0;
        Integer ases = 0; // Contador de ases

        for (Integer i = 0; i < numeroCartas; i++) {
            String valor = mano[i].getNumero();

            // Convertir el valor de la carta a puntaje
            if (valor.equals("J") || valor.equals("Q") || valor.equals("K")) {
                puntaje += 10;
            } else if (valor.equals("A")) {
                ases++;
                puntaje += 11; // Contar inicialmente el As como 11
            } else {
                puntaje += Integer.parseInt(valor);
            }
        }

        // Ajustar el valor de los Ases si el puntaje es mayor a 21
        while (puntaje > 21 && ases > 0) {
            puntaje -= 10; // Contar el As como 1 en lugar de 11
            ases--;
        }

        return puntaje;
    }

    // Mostrar las cartas en la mano
    public void mostrarMano() {
        System.out.println(nombre + " tiene:");
        for (Integer i = 0; i < numeroCartas; i++) {
            System.out.println("- " + mano[i]);
        }
        System.out.println("Puntaje: " + calcularPuntaje());
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean estaEnJuego() {
        return calcularPuntaje() <= 21;
    }

    public Carta[] getMano() {
        return mano;
    }
}
