package salas;

import acciones.Pasillo;

public class Casino {
    public static void iniciarCasino() {
        // new SalaPrincipal(SalaRegistro.jugador);
        SalaPrincipal salaPrincipal = SalaPrincipal.getInstance();
        SalaAzar salaAzar = SalaAzar.getInstance();

        // Configurar los pasillos después de crear las salas
        salaPrincipal.getPasillos().add(new Pasillo(3, 15, salaAzar));
        salaAzar.getPasillos().add(new Pasillo(3, 0, salaPrincipal));

        SalaRegistro.jugador.setPosX(SalaPrincipal.getInstance().getPosInitialX());
        SalaRegistro.jugador.setPosY(SalaPrincipal.getInstance().getPosInitialY());
        salaPrincipal.setJugador(SalaRegistro.jugador);

        salaPrincipal.iniciarInterfaz();
    }
}
