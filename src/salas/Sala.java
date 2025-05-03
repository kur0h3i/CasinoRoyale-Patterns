package salas;

import acciones.Mesa;
import personas.Jugador;

import java.util.ArrayList;

import static recursos.MensajesEstaticos.instructions;
import static recursos.MensajesEstaticos.playerUI;

public class Sala {

    private Character[][] mapa;
    private Jugador jugador;

    public Sala(Jugador jugador, Character[][] mapa) {
        this.jugador = jugador;
        this.mapa = mapa;
    }

    private void interfazPrincipal(Jugador jugador, ArrayList<Mesa> mesas){
        // Interfaz del jugador
        playerUI(jugador);

        // Mostrar el mapa del casino
        mostrarMapa();

        // Instrucciones de control
        instructions();

        // Interacción con mesas // TODO

    }

    // Mostrar mapa del casino (Jugador P)
    private void mostrarMapa() {
        for (Integer i = 0; i < mapa.length; i++) {
            for (Integer j = 0; j < mapa[i].length; j++) {
                if (i == jugador.getPosY() && j == jugador.getPosX()) {
                    System.out.print("P ");
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }



    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    // TODO: Lo Requiero esto debido a las multiples salas que vaya a haber

}
