import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;

class DashboardItem extends JPanel {

    private Dashboard dashboard;
    private static int instanceCounter = 0;

    private int itemNumber;
    private boolean active = false;
    private int stored_time = 0;

    // if an item is a script, it will appear as the classic telemprompter read mode
    private boolean isScript = false;
    private double scrollAmount = 0.0;

    // UI components
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

    public void resetDuration() {
        durationSpinner.setValue(stored_time);
        System.out.println("Duration reset to stored time: " + stored_time);
    }

    public void advanceSecond() {
        int currentDuration = (Integer) durationSpinner.getValue();
        if (currentDuration > 0) {
            durationSpinner.setValue(currentDuration - 1);
            if (getDuration() <= base.WARNING_TIME) {
                this.setBackground(base.CTRL_WARNING_BG_COLOR);
            }
            this.revalidate();
            this.repaint();
        } else if (currentDuration == 0 && dashboard.getController().isCountdownAuto()) {
            dashboard.getController().goToNext();
        }
    }

    public void setActive(boolean isActive) {
        if (isActive) {
            active = true;
            if (isScript) {
                this.setBackground(base.CTRL_SCRIPT_ACTIVE_BG_COLOR);
            } else {
                this.setBackground(base.CTRL_STANDARD_ACTIVE_BG_COLOR);
            }
        } else {
            active = false;
            if (isScript) {
                this.setBackground(base.CTRL_SCRIPT_INACTIVE_BG_COLOR); // dark gray
            } else {
                this.setBackground(base.CTRL_STANDARD_INACTIVE_BG_COLOR); // white
            }
        }
    }

    // set up the look of the DashboardItem
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
        notesArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);

        // duration spinner
        durationSpinner = new JSpinner(new SpinnerNumberModel(60, 1, 3600, 1));
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) durationSpinner.getEditor();

        JFormattedTextField textField = editor.getTextField();

        textField.addPropertyChangeListener("value", evt -> {
            if (!dashboard.getController().isPlaying()) {
                System.out.println("User changed spinner value");
                stored_time = (Integer) durationSpinner.getValue();
                dashboard.updateTimeIndicator();
            }
            
        });

        // remove button
        JButton removeButton = new JButton("X");
        removeButton.addActionListener(e -> {
            Container parent = this.getParent();
            if (parent != null) {
                parent.remove(this);
                dashboard.getController().removeControllerItem(this);
                dashboard.reassignNumbers();
                parent.revalidate();
                parent.repaint();
            }
        });

        // take to program button
        JButton toProgramButton = new JButton("Go");
        toProgramButton.addActionListener(e -> {
            dashboard.getController().take(itemNumber - 1);
            System.out.println("Go to program button clicked for item " + itemNumber);
        });

        // script checkbox
        JCheckBox checkBox = new JCheckBox("Script");
        checkBox.addActionListener(e -> {
            isScript = checkBox.isSelected();
            if (isScript) {
                System.out.println("Item " + itemNumber + " set to Script mode");
                setBackground(base.CTRL_SCRIPT_INACTIVE_BG_COLOR); // light yellow for scripts
            } else {
                System.out.println("Item " + itemNumber + " set to Standard mode");
                setBackground(base.CTRL_STANDARD_INACTIVE_BG_COLOR); // white for standard
            }
        });




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
        this.add(checkBox);
    }

    // constructors

    public DashboardItem(Dashboard dashboard) {
        this.dashboard = dashboard;
        declareComponents();
        this.setActive(false);
        stored_time = (Integer) durationSpinner.getValue();
        
    }

    public DashboardItem(String title, String notes, int duration, Dashboard dashboard) {
        this(dashboard);
        titleField.setText(title);
        notesArea.setText(notes);
        durationSpinner.setValue(duration);
        stored_time = (Integer) durationSpinner.getValue();
    }

    // getters

    public String getTitle() {
        return titleField.getText();
    }

    public String getNotes() {
        return notesArea.getText();
    }

    public int getDuration() {
        return (Integer) durationSpinner.getValue();
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public Object getNotesText() {
        return notesArea.getText();
    }

    public boolean isScript() {
        return isScript;
    }

    public boolean isActive() {
        return active;
    }



    

}