package patterns.decorator;

public class FichasTestMain {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Luis", 10);

        System.out.println(jugador);
        jugador.agregarFichas(5);
        System.out.println(jugador);
        jugador.quitarFichas(3);
        System.out.println(jugador);
        jugador.quitarFichas(3);
        System.out.println(jugador);
        jugador.agregarFichas(5);
        System.out.println(jugador);
    }
}