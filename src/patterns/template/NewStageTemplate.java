package patterns.template;

import patterns.observer.Subscription;
import personas.Jugador;

/**
 * Clase abstracta que define el template para el comportamiento de las nuevas etapas
 * del juego. Esta clase se encarga de gestionar la transicion del jugador entre
 * diferentes etapas del juego.
 * Implementa el patron Template Method para definir la estructura de una nueva etapa,
 * dejando que las subclases definan detalles especificos.
 */
public abstract class NewStageTemplate implements Subscription {

    /**
     * Metodo abstracto que debe ser implementado por las clases hijas para definir
     * el comportamiento especifico al ingresar a una nueva etapa del juego.
     *
     * @param jugador el jugador que entra en la nueva etapa
     */
    public abstract void enterNewStage(Jugador jugador);

    /**
     * Metodo abstracto que debe ser implementado por las clases hijas para definir
     * la interfaz de usuario al iniciar una nueva etapa del juego.
     * Este metodo es responsable de configurar la interfaz visual de la nueva etapa.
     */
    public abstract void iniciarInterfaz();
}
