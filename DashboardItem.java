import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

class DashboardItem extends JPanel {
    private Dashboard dashboard;
    private static int instanceCounter = 0;
    private int itemNumber;
    private JLabel numberLabel;
    private JLabel titleLabel;
    private JLabel notesLabel;
    private JLabel durationLabel;
    private JTextField titleField;
    private JTextArea notesArea;
    private JSpinner durationSpinner;

    public void updateItemNumber(int newNumber) {
        this.itemNumber = newNumber;
        numberLabel.setText(String.valueOf(newNumber));
    }

    public static void setInstanceCounter(int count) {
        instanceCounter = count;
    }

    private void declareComponents() {
        // set item number appropriately
        instanceCounter++;
        itemNumber = instanceCounter;

        // number label
        numberLabel = new JLabel(String.valueOf(itemNumber));
        numberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        numberLabel.setForeground(new Color(0x454545));
        numberLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        numberLabel.setOpaque(true);

        // title label
        titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // notes label
        notesLabel = new JLabel("Notes:");

        // duration label
        durationLabel = new JLabel("Duration (s):");

        // title field
        titleField = new JTextField(20);

        // notes area
        notesArea = new JTextArea(5,30);

        // duration spinner
        durationSpinner = new JSpinner();

        // remove button
        JButton removeButton = new JButton("X");
        removeButton.addActionListener(e -> {
            Container parent = this.getParent();
            if (parent != null) {
                parent.remove(this);
                dashboard.reassignNumbers();
                parent.revalidate();
                parent.repaint();
            }
        });

        // take to program button
        JButton toProgramButton = new JButton("Go");




        toProgramButton.addActionListener(e -> {
            System.out.println("Go to program button clicked for item " + itemNumber);
        });

        // layout settings
        this.setBackground(new Color(0x8AFF82));

        // add components in order
        this.add(numberLabel);
        this.add(titleLabel);
        this.add(titleField);
        this.add(notesLabel);
        this.add(notesArea);
        this.add(durationLabel);
        this.add(durationSpinner);
        this.add(removeButton);
        this.add(toProgramButton);
    }

    public DashboardItem(Dashboard dashboard) {
        this.dashboard = dashboard;
        declareComponents();
        
    }

    public DashboardItem(String title, String notes, int duration, Dashboard dashboard) {
        this(dashboard);
        titleField.setText(title);
        notesArea.setText(notes);
        durationSpinner.setValue(duration);
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getNotes() {
        return notesArea.getText();
    }

    public int getDuration() {
        return (Integer) durationSpinner.getValue();
    }

    

}