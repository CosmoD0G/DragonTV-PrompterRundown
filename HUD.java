import java.awt.Color;

import javax.swing.*;

public class HUD extends JFrame {
    public HUD() {
        setTitle("HUD");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0x1F1F1F));
        setVisible(true);
    }
}

