package vallegrande.edu.pe.trabajopoo;

import vallegrande.edu.pe.trabajopoo.view.FrmSeller;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new FrmSeller().setVisible(true);
        });
    }
}