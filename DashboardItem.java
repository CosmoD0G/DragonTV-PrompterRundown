import javax.swing.*;
import java.awt.*;

class DashboardItem extends JPanel {
    private JLabel numberLabel;
    private JLabel titleLabel;
    private JLabel notesLabel;
    private JLabel durationLabel;
    private JTextField titleField;
    private JTextArea notesArea;
    private JSpinner durationSpinner;

    private void declareComponents() {
        numberLabel = new JLabel("1");
        numberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        numberLabel.setForeground(new Color(0x454545));
        numberLabel.setOpaque(true);

        titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        notesLabel = new JLabel("Notes:");
        durationLabel = new JLabel("Duration (s):");


        titleField = new JTextField(20);
        notesArea = new JTextArea(5,30);
        durationSpinner = new JSpinner();

        this.setBackground(new Color(0x8AFF82));

        this.add(numberLabel);
        this.add(titleLabel);
        this.add(titleField);
        this.add(notesLabel);
        this.add(notesArea);
        this.add(durationLabel);
        this.add(durationSpinner);
    }

    public DashboardItem() {
        declareComponents();
        
    }

    public DashboardItem(String title, String notes, int duration) {
        declareComponents();
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