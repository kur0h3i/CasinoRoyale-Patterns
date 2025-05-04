// IO
import java.io.IOException;

// Estructura Casino
import acciones.Pasillo;
import salas.*;

// Excepcion
import excep.ExcepcionJugadorMenorEdad;
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;

public class MainCasino {
    public static void main(String[] args) throws IOException, ExcepcionJugadorSinFichas, ExcepcionJugadorMenorEdad, ExcepcionJugadorSinDinero {
        SalaRegistro registro = new SalaRegistro();
        if (registro.mayorEdad()){
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
}
