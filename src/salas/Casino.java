package salas;

import acciones.Pasillo;

public class Casino {
    public static void iniciarCasino() {
        SalaPrincipal salaPrincipal = SalaPrincipal.getInstance();
        SalaAzar salaAzar = SalaAzar.getInstance();

        // Configurar los pasillos después de crear las salas
        salaPrincipal.getPasillos().add(new Pasillo(3, 15, salaAzar));
        salaAzar.getPasillos().add(new Pasillo(3, 0, salaPrincipal));

        SalaRegistro.jugador.setPosX(salaPrincipal.getPosInitialX());
        SalaRegistro.jugador.setPosY(salaPrincipal.getPosInitialY());
        SalaRegistro.jugador.setSala(salaPrincipal);
        salaPrincipal.setJugador(SalaRegistro.jugador);

        salaPrincipal.iniciarInterfaz();
    }
}
