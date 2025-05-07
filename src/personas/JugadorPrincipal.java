
package personas;

/**
 * Extensión de Jugador que representa al usuario principal del casino.
 * Se utiliza para interactuar con menús y lógica diseñada específicamente para el jugador real.
 */
public class JugadorPrincipal extends Jugador {

    /**
     * Constructor de JugadorPrincipal.
     *
     * @param nombre nombre del jugador principal
     * @param edad edad del jugador
     * @param dinero saldo inicial en euros
     */
    public JugadorPrincipal(String nombre, int edad, double dinero) {
        super(nombre, edad, dinero);
    }

    /**
     * Muestra los datos del jugador principal en el juego de forma destacada.
     */
    @Override
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("Jugador Principal: " + this.getName());
        System.out.println("Fichas : " + this.getFichas());
        System.out.println("----------------------------");
    }
}
