package blackjack;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BlackJackUI ui = new BlackJackUI();
            ui.setVisible(true);
        });
    }
}
