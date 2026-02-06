import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HUDItem extends JPanel{
    private DashboardItem item;

    private JLabel numberLabel;
    private JLabel titleLabel;
    private JLabel durationLabel;
    
    public HUDItem(DashboardItem item) {
        this.item = item;


        // style
        this.setBackground(new Color(0xFFFFFF));
        this.setPreferredSize(new Dimension(100,100));

        // number label
        numberLabel = new JLabel(String.valueOf(item.getItemNumber()));
        numberLabel.setFont(new Font("Arial", Font.BOLD, 26));
        numberLabel.setForeground(new Color(0x454545));
        numberLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        numberLabel.setOpaque(true);

        // title label
        titleLabel = new JLabel(item.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0x454545));
        titleLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        titleLabel.setOpaque(true);

        // duration label
        durationLabel = new JLabel(String.valueOf(item.getDuration()));
        durationLabel.setFont(new Font("Arial", Font.BOLD, 26));
        durationLabel.setForeground(new Color(0x454545));
        durationLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        durationLabel.setOpaque(true);

        if (item.isActive()) {
            this.setBackground(new Color(0x8AFF82)); // active green
        }

        
        

        add(this.numberLabel);
        add(this.titleLabel);
        add(this.durationLabel);
    }
}
