package personas;

public interface Jugador {
    void move(Integer x, Integer y); // Método para mover al jugador

    void interacting(); // Método para interactuar con el entorno

    void datosUsuarioEnPartida();

    Integer getFichas();

    void setFichas(Double i);

    void setDinero(Double i);

    String getName();

    Double getDinero();

    Object getPosX();

    Object getPosY();

    boolean getInteract();
}
