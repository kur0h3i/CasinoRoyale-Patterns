package personas;

public class JugadorPrincipal extends Jugador {
	 // Constructor
    public JugadorPrincipal(String nombre, int edad, double dinero) {
        super(nombre, edad, dinero);
    }

    @Override
    public void datosUsuarioEnPartida() {
        System.out.println("----------------------------");
        System.out.println("Jugador Principal: " + this.getName());
        System.out.println("Fichas : " + this.getFichas());
        System.out.println("----------------------------");
    }
}
