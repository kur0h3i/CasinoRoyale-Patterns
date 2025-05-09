// ASCIBingo.java

package ascii;

import java.io.Serial;
import java.io.Serializable;

public class ASCIIBingo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Titulo del bingo
    public void titulo() {
        System.out.println(" ____  _                   ");
        System.out.println("| __ )(_)_ __   __ _  ___  ");
        System.out.println("|  _ \\| | '_ \\ / _` |/ _ \\ ");
        System.out.println("| |_) | | | | | (_| | (_) |");
        System.out.println("|____/|_|_| |_|\\__, |\\___/ ");
        System.out.println("               |___/       ");
    }

}
