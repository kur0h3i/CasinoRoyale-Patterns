package personas;

import salas.SalaPrincipal;

/**
 * Interfaz que define los metodos comunes para todos los tipos de jugadores en el casino.
 * Los jugadores, tanto controlados por el usuario como NPCs, deben implementar estos metodos
 * para manejar su posicion, edad y sala en el juego.
 */
public interface JugadorTotal {

    /**
     * Muestra los datos del jugador en el juego de forma resumida.
     * Cada implementacion de esta interfaz debe definir como se muestran los datos.
     */
    public void datosUsuarioEnPartida();

    /**
     * Obtiene la edad del jugador.
     * Este metodo debe ser implementado para devolver la edad del jugador.
     *
     * @return edad del jugador
     */
    public Integer getEdad();

    /**
     * Establece la coordenada X inicial del jugador en el mapa.
     * Cada implementacion debe definir como se establece la posicion.
     *
     * @param posInitialX coordenada X inicial del jugador
     */
    void setPosX(Integer posInitialX);

    /**
     * Establece la coordenada Y inicial del jugador en el mapa.
     * Cada implementacion debe definir como se establece la posicion.
     *
     * @param posInitialY coordenada Y inicial del jugador
     */
    void setPosY(Integer posInitialY);

    /**
     * Establece la sala en la que se encuentra el jugador.
     * Cada implementacion debe definir como se asigna la sala al jugador.
     *
     * @param salaPrincipal instancia de la sala en la que se encuentra el jugador
     */
    void setSala(SalaPrincipal salaPrincipal);
}
