
import javax.swing.SwingUtilities;

public class main{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BloomFilterLibraryGUI gui = new BloomFilterLibraryGUI(1000, 3);  // 1000 bits y 3 funciones hash
            gui.setVisible(true);
        });
    }
}