import javax.swing.*;

class DashboardItem extends JPanel {
    private JTextField titleField;
    private JTextField notesField;
    private JSpinner durationSpinner;

    public DashboardItem() {
        titleField = new JTextField(20);
        notesField = new JTextField(20);
        durationSpinner = new JSpinner();
        this.add(titleField);
        this.add(notesField);
        this.add(durationSpinner);
    }

    public DashboardItem(String title, String notes, int duration) {
        titleField = new JTextField(title, 20);
        notesField = new JTextField(notes, 20);
        durationSpinner = new JSpinner();
        durationSpinner.setValue(duration);
        this.add(titleField);
        this.add(notesField);
        this.add(durationSpinner);
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getNotes() {
        return notesField.getText();
    }

    public int getDuration() {
        return (Integer) durationSpinner.getValue();
    }

    

}