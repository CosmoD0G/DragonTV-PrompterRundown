import javax.swing.*;

class RundownControllerItem extends JPanel {
    private JTextField titleField;
    private JTextField notesField;
    private JSpinner durationSpinner;

    public RundownControllerItem() {
        titleField = new JTextField(20);
        notesField = new JTextField(20);
        durationSpinner = new JSpinner();
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