import javax.swing.*;

public class HUDItem extends JPanel{
    private String title = "";
    private String notes = "";
    private int duration = 0;

    private JLabel titleLabel;
    private JLabel durationLabel;
    
    public HUDItem(String title, String notes, int duration) {
        this.title = title;
        this.notes = notes;
        this.duration = duration;

        this.titleLabel = new JLabel(title);
        this.durationLabel = new JLabel(String.valueOf(duration));

        add(this.titleLabel);
        add(this.durationLabel);
    }
}
