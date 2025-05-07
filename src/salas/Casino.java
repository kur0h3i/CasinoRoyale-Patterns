
package salas;

import acciones.Pasillo;

/**
 * Clase de inicio que configura las salas del casino y corredores entre ellas.
 * Punto de entrada para iniciar la experiencia del casino.
 * Se encarga de:
 *  1. Obtener instancias singleton de las salas (principal, azar, cartas).
 *  2. Configurar los pasillos que conectan las salas.
 *  3. Establecer la posición y sala inicial del jugador registrado.
 *  4. Lanzar la interfaz ASCII de la sala principal.
 */
public class Casino {

    /**
     * Inicializa el casino creando y conectando las salas,
     * y posicionando al jugador en la sala principal.
     */
    public static void iniciarCasino() {
        // Obtener instancias singleton de cada sala
        SalaPrincipal salaPrincipal = SalaPrincipal.getInstance();
        SalaAzar salaAzar         = SalaAzar.getInstance();
        SalaCartas salaCartas     = SalaCartas.getInstance();

        // Configurar pasillos entre salas:
        // Desde sala principal a sala de azar
        salaPrincipal.getPasillos().add(
                new Pasillo(3, 15, salaAzar)
        );
        // Desde sala principal a sala de cartas
        salaPrincipal.getPasillos().add(
                new Pasillo(21, 0, salaCartas)
        );
        // Desde sala de azar de vuelta a sala principal
        salaAzar.getPasillos().add(
                new Pasillo(3, 0, salaPrincipal)
        );
        // Desde sala de cartas de vuelta a sala principal
        salaCartas.getPasillos().add(
                new Pasillo(11, 15, salaPrincipal)
        );

        // Posicionar al jugador registrado en la entrada de la sala principal
        SalaRegistro.jugador.setPosX(
                salaPrincipal.getPosInitialX()
        );
        SalaRegistro.jugador.setPosY(
                salaPrincipal.getPosInitialY()
        );
        SalaRegistro.jugador.setSala(salaPrincipal);
        salaPrincipal.setJugador(SalaRegistro.jugador);

        // Iniciar la interfaz ASCII de la sala principal
        salaPrincipal.iniciarInterfaz();
    }
}
