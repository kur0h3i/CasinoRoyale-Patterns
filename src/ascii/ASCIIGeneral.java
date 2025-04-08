//ASCIIGeneral

package ascii;

// Util
import java.util.Scanner;

public class ASCIIGeneral {
    
    // Esperar una tecla para continuar
    public static void esperarTecla() {
        Scanner input = new Scanner(System.in);
        System.out.println("Presiona Enter para continuar...");
        input.nextLine();
    }
    
    // Limpiar la terminal
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
