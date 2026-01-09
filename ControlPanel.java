/*import javax.swing.*;

public class ControlPanel extends JFrame {
    public ControlPanel(controller ctrl) {
        setTitle("Control Panel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        

        // add scroll pane with all of the rundown items
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(container);
        add(scrollPane);

        // button to add new Rundown Controller Item
        ControllerButton addButton = new ControllerButton("Add Item");
        panel.add(addButton);

        addButton.addActionListener(e -> {
            System.out.println("Add Item button clicked");
            container.add(new RundownControllerItem());
        });

        // button to sync rundown items to HUD
        ControllerButton syncButonn = new ControllerButton("Sync to HUD");
        panel.add(syncButonn);

        syncButonn.addActionListener(e -> {
            System.out.println("Sync to HUD button clicked");
            ctrl.syncItemsToHUD();
        });
    }
}
    */

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JFrame {

    public ControlPanel(controller ctrl) {
        setTitle("Control Panel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Top panel (buttons) =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add "Add Item" button
        ControllerButton addButton = new ControllerButton("Add Item");
        topPanel.add(addButton);

        // Add "Sync to HUD" button
        ControllerButton syncButton = new ControllerButton("Sync to HUD");
        topPanel.add(syncButton);

        // Add "Take Next" button
        ControllerButton takeNextButton = new ControllerButton("Take Next");
        topPanel.add(takeNextButton);

        // Add "Go to Top" button
        ControllerButton goToTopButton = new ControllerButton("Go to Top");
        topPanel.add(goToTopButton);

        // Add "Live Toggle" button
        ControllerButton liveToggleButton = new ControllerButton("Live Toggle");
        topPanel.add(liveToggleButton);

        // Add "Play / Pause" button
        ControllerButton playPauseButton = new ControllerButton("Play / Pause");
        topPanel.add(playPauseButton);

        // Add "Reset" button
        ControllerButton resetButton = new ControllerButton("Reset");
        topPanel.add(resetButton);

        // Add "Clear All" button
        ControllerButton clearAllButton = new ControllerButton("Clear All");  
        topPanel.add(clearAllButton);

        // approximate 1/6 of initial frame height
        topPanel.setPreferredSize(new Dimension(500, 60));

        // scrollable container for rundown items
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(container);

        // ===== Add panels to frame =====
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ===== Button actions =====
        addButton.addActionListener(e -> {
            System.out.println("Add Item button clicked");
            container.add(new RundownControllerItem());
            container.revalidate(); // refresh layout
            container.repaint();
        });

        syncButton.addActionListener(e -> {
            System.out.println("Sync to HUD button clicked");
            ctrl.syncItemsToHUD();
        });

        // Show the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

