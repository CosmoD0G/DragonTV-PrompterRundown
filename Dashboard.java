import javax.swing.*;

import java.awt.*;
import java.io.File;

public class Dashboard extends JFrame {

    private controller ctrl;
    private JPanel container;

    public void reassignNumbers() {
        Component[] components = container.getComponents();
        int j = 0;
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof DashboardItem) {
                DashboardItem item = (DashboardItem) components[i];
                item.updateItemNumber(i + 1);
                j++;
            }
        }
        DashboardItem.setInstanceCounter(j);
    }

    // Clear the rundown with confirmation dialog
    private boolean clear() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the rundown?\nThis action cannot be undone.", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                System.out.println("User confirmed clearing the rundown");
                ctrl.clearRundown(result == JOptionPane.YES_OPTION);

                // update UI
                container.removeAll();
                reassignNumbers();
                container.revalidate();
                container.repaint();
                return true;
            } else {
                System.out.println("User canceled clearing the rundown");
                return false;
            }
    }

    public void addToDashboard(DashboardItem item) {

        // REQUIRED for BoxLayout
        item.setAlignmentX(Component.LEFT_ALIGNMENT);
        item.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, item.getPreferredSize().height)
        );

        container.add(item);
        container.revalidate();
        container.repaint();
        
    }

    public Dashboard(controller ctrl) {
        this.ctrl = ctrl;
        setTitle("Control Panel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Top panel (buttons) =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

         // approximate 1/6 of initial frame height
        topPanel.setPreferredSize(new Dimension(500, 60));

        // scrollable container for rundown items
        
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(container);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        

        
        // Add "Add Item" button
        ControllerButton addButton = new ControllerButton("Add Item");
        topPanel.add(addButton);

        addButton.addActionListener(e -> {
            System.out.println("Add Item button clicked");

            DashboardItem newItem = new DashboardItem(this);
            ctrl.addItem(newItem);

            addToDashboard(newItem);
        });


        // Add "Sync to HUD" button
        ControllerButton syncButton = new ControllerButton("Sync to HUD");
        topPanel.add(syncButton);

        syncButton.addActionListener(e -> {
            System.out.println("Sync to HUD button clicked");
            ctrl.syncItemsToHUD();
        });

        // Add "Take Next" button
        ControllerButton takeNextButton = new ControllerButton("Take Next");
        topPanel.add(takeNextButton);

        takeNextButton.addActionListener(e -> {
            System.out.println("Take Next button clicked");
            ctrl.goToNext();
        });

        // Add "Return to Previous" button
        ControllerButton returnPrevButton = new ControllerButton("Return to Previous");
        topPanel.add(returnPrevButton);

        returnPrevButton.addActionListener(e -> {
            System.out.println("Return to Previous button clicked");
            ctrl.goToPrevious();
        });

        // Add "Go to Top" button
        ControllerButton goToTopButton = new ControllerButton("Go to Top");
        topPanel.add(goToTopButton);

        goToTopButton.addActionListener(e -> {
            System.out.println("Go to Top button clicked");
            // Implement go to top functionality if needed
        });

        // Add "Live Toggle" button
        ControllerButton liveToggleButton = new ControllerButton("Live Toggle");
        topPanel.add(liveToggleButton);

        liveToggleButton.addActionListener(e -> {
            System.out.println("Live Toggle button clicked");
            ctrl.toggleLive();
        });

        // Add "Play / Pause" button
        ControllerButton playPauseButton = new ControllerButton("Play / Pause");
        topPanel.add(playPauseButton);

        playPauseButton.addActionListener(e -> {
            System.out.println("Play / Pause button clicked");
            ctrl.togglePlayPause();
        });


        // Add "Reset" button
        ControllerButton resetButton = new ControllerButton("Reset");
        topPanel.add(resetButton);

        // Add "Clear All" button
        ControllerButton clearAllButton = new ControllerButton("Clear All");  
        topPanel.add(clearAllButton);

        clearAllButton.addActionListener(e -> {
            System.out.println("Clear All button clicked");
            clear();
        });

        // Add "Import from CSV" button
        ControllerButton importCSVButton = new ControllerButton("Import from CSV");
        topPanel.add(importCSVButton);

        importCSVButton.addActionListener(e -> {
            File csvFile = FileManager.chooseCsvFile(this);
            ctrl.setFile(csvFile);
            if (csvFile != null) {
                System.out.println("Selected CSV: " + csvFile.getAbsolutePath());
                if (clear()) {
                    ctrl.loadFromFile();
                    for (DashboardItem item : ctrl.getControllerItems()) {
                        addToDashboard(item);
                    }
                }
                
            }
        });

        // Add "Export to CSV" button
        ControllerButton exportCSVButton = new ControllerButton("Export to CSV");
        topPanel.add(exportCSVButton);

        // Add Live Indicator
        JLabel liveIndicator = new JLabel("● LIVE");
        liveIndicator.setForeground(Color.RED);
        topPanel.add(liveIndicator);

        JLabel main_timer = new JLabel("00:00:00");
        main_timer.setFont(new Font("Monospaced", Font.BOLD, 16));
        topPanel.add(main_timer);


        

        // Show the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

