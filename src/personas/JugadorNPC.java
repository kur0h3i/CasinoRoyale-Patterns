package personas;

public class JugadorNPC extends Jugador {
    // Constructor para crear un NPC
    public JugadorNPC(String nombre, int edad, double dinero) {
        super(nombre, edad, dinero);
    }

    @Override
    public void datosUsuarioEnPartida() {
        // Mostrar datos específicos de un NPC
        System.out.println("----------------------------");
        System.out.println("NPC: " + this.getName());
        System.out.println("Fichas : " + this.getFichas());
        System.out.println("----------------------------");
    }
}
