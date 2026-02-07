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
        if (item.getDuration() <= base.WARNING_TIME) {
            this.setBackground(base.HUD_WARNING_BG_COLOR);
        } else {
            this.setBackground(new Color(0x000000));
        }
        this.setPreferredSize(new Dimension(100,100));

        // number label
        numberLabel = new JLabel(String.valueOf(item.getItemNumber()));
        numberLabel.setFont(new Font("Arial", Font.BOLD, base.HUD_RUNDOWN_FONT_SIZE-8));
        numberLabel.setForeground(new Color(0xFFFFFF));
        numberLabel.setBackground(base.HUD_BORDER_COLOR);
        numberLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        numberLabel.setOpaque(true);

        // title label
        titleLabel = new JLabel(item.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, base.HUD_RUNDOWN_FONT_SIZE));
        titleLabel.setForeground(new Color(0xFFFFFF));
        titleLabel.setBackground(base.HUD_BORDER_COLOR);
        titleLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        titleLabel.setOpaque(true);

        // duration label
        durationLabel = new JLabel(String.valueOf(item.getDuration()));
        durationLabel.setFont(new Font("Arial", Font.BOLD, base.HUD_RUNDOWN_FONT_SIZE));
        durationLabel.setForeground(new Color(0xFFFFFF));
        durationLabel.setBackground(base.HUD_BORDER_COLOR);
        durationLabel.setBorder(new EmptyBorder(8, 12, 8, 12));
        durationLabel.setOpaque(true);

        if (item.isActive()) {
            this.setBackground(new Color(0x8AFF82)); // active green
            if (item.getDuration() <= base.WARNING_TIME) {
                this.setBackground(base.CTRL_WARNING_BG_COLOR);
            }
        } else {
            this.setBackground(base.HUD_STANDARD_INACTIVE_BG_COLOR);
        }

        
        

        add(this.numberLabel);
        add(this.titleLabel);
        add(this.durationLabel);
    }

    public DashboardItem getDashboardItem() {
        return item;
    }
}
