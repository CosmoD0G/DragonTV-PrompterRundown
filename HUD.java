import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class HUD extends JFrame {

    private JPanel leftPanel = new JPanel();

    public void setRundown(ArrayList<HUDItem> rundowns) {
        // Clear existing items
        leftPanel.removeAll();


        // Add each HUDItem to the HUD
        for (HUDItem item : rundowns) {
            leftPanel.add(item);
        }

        // Refresh the HUD display
        leftPanel.revalidate();
        leftPanel.repaint();
    }


    public HUD(controller ctrl) {
        ctrl.setHUD(this);
        setTitle("HUD");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0x1F1F1F));


        // Main layout: split left / right
        this.setLayout(new GridLayout(1, 2));

        // LEFT PANEL (takes full left half)
        
        leftPanel.setBackground(Color.BLUE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // RIGHT CONTAINER (split top / bottom)
        JPanel rightContainer = new JPanel(new GridLayout(2, 1));

        JPanel topRightPanel = new JPanel();
        topRightPanel.setBackground(Color.RED);

        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.GRAY); // optional

        rightContainer.add(topRightPanel);
        rightContainer.add(bottomRightPanel);

        // Add to frame
        this.add(leftPanel);
        this.add(rightContainer);

        this.setVisible(true);






        setVisible(true);
    }




}

