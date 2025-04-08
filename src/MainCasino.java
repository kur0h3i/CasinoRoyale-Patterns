// IO
import java.io.IOException;

// Estructura Casino
import estructuraCasino.*;

// Excepcion
import excep.ExcepcionJugadorMenorEdad;
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;

public class MainCasino {
    public static void main(String[] args) throws IOException, ExcepcionJugadorSinFichas, ExcepcionJugadorMenorEdad, ExcepcionJugadorSinDinero {
        SalaRegistro registro = new SalaRegistro();
        if (registro.mayorEdad()){
            new SalaPrincipal(SalaRegistro.jugador);
        }
    }
}
