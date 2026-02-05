import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HUDItem extends JPanel{
    private int itemNumber = 0;
    private String title = "";
    private String notes = "";
    private int duration = 0;

    private JLabel numberLabel;
    private JLabel titleLabel;
    private JLabel durationLabel;
    
    public HUDItem(int num, String title, String notes, int duration) {
        this.itemNumber = num;
        this.title = title;
        this.notes = notes;
        this.duration = duration;

        // number label
        numberLabel = new JLabel(String.valueOf(itemNumber));
        numberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        numberLabel.setForeground(new Color(0x454545));
        numberLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        numberLabel.setOpaque(true);

        // title label
        titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // duration label
        durationLabel = new JLabel(String.valueOf(duration));

        this.titleLabel = new JLabel(title);
        this.durationLabel = new JLabel(String.valueOf(duration));

        add(this.numberLabel);
        add(this.titleLabel);
        add(this.durationLabel);
    }
}
