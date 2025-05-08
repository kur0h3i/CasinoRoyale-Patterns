
package personas;

import salas.SalaPrincipal;

/**
 * Extension de Jugador que representa al usuario principal del casino.
 * Se utiliza para interactuar con menus y logica diseñada especificamente para el jugador real.
 */
public interface JugadorTotal {
    public void datosUsuarioEnPartida();

    public Integer getEdad();

    void setPosX(Integer posInitialX);

    void setPosY(Integer posInitialY);

    void setSala(SalaPrincipal salaPrincipal);
}
