package Application.Window;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final DrawPanel panel;
    private final int BACKGROUND_WIDTH = 1600;
    private final int BACKGROUND_HEIGHT = 800;

    public MainWindow() throws HeadlessException {
        setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        panel = new DrawPanel(this.getWidth(), this.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
