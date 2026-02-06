import java.awt.*;
import java.util.ArrayList;
import java.lang.reflect.Array;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HUD extends JFrame {
    private final int MAX_ITEMS_ON_SCREEN = 8;
    private JPanel leftPanel = new JPanel();
    private JLabel notesLabel = new JLabel("Awaiting active rundown element...");
    private JPanel topRightPanel = new JPanel();
    private JLabel placeholderLabel = new JLabel("Awaiting rundown items...");
    JLabel liveIndicator = new JLabel("LIVE");

    public void setRundown(ArrayList<HUDItem> rundowns, int activeElement) {
        leftPanel.removeAll();

        if (rundowns.size() == 0) {
            leftPanel.add(placeholderLabel);
            leftPanel.add(Box.createVerticalGlue());
            leftPanel.revalidate();
            leftPanel.repaint();
            return;
        }

        int endOfScreenLimit = rundowns.size()-MAX_ITEMS_ON_SCREEN-1;
        for (HUDItem item : rundowns) {
            int thisItemNum = item.getDashboardItem().getItemNumber();
            if (rundowns.size() > MAX_ITEMS_ON_SCREEN) {
                if (activeElement > endOfScreenLimit && thisItemNum >= endOfScreenLimit) {
                    leftPanel.add(item);
                } else if (thisItemNum >= activeElement && thisItemNum <= activeElement + MAX_ITEMS_ON_SCREEN) {
                    leftPanel.add(item);
                }
            } else {
                leftPanel.add(item);
            }
            
            item.setAlignmentX(Component.LEFT_ALIGNMENT); // important
            if (item.getDashboardItem().isActive()) {
                notesLabel.setText(item.getDashboardItem().getNotes());
            }

            item.setMaximumSize(new Dimension(Integer.MAX_VALUE, item.getPreferredSize().height));
        }

        leftPanel.add(Box.createVerticalGlue()); 

        leftPanel.revalidate();
        leftPanel.repaint();
}

    public void setLive(boolean isLive) {
        if (isLive) {
            liveIndicator.setForeground(new Color(0xFF0000));
        } else {
            liveIndicator.setForeground(new Color(0x333333));
        }
    }

    public HUD(controller ctrl) {
        ctrl.setHUD(this);
        setTitle("HUD");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0x1F1F1F));


        // Main layout: split left / right
        this.setLayout(new GridLayout(1, 2));

        // LEFT PANEL (takes full left half)
        
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        placeholderLabel.setFont(new Font("Arial", Font.BOLD, 24));
        placeholderLabel.setForeground(new Color(0xAAAAAA));
        leftPanel.add(placeholderLabel);

        // Time Indicator
        JLabel timeIndicator = new JLabel("mm:ss");
        timeIndicator.setFont(new Font("Arial", Font.BOLD, base.HUD_NOTES_FONT_SIZE));
        timeIndicator.setForeground(new Color(0xFFFFFF));

        // Live Indicator
        liveIndicator.setFont(new Font("Arial", Font.BOLD, base.HUD_NOTES_FONT_SIZE));
        liveIndicator.setForeground(new Color(0x333333));

        // RIGHT CONTAINER (split top / bottom)
        JPanel rightContainer = new JPanel(new BorderLayout());


        notesLabel.setFont(new Font("Arial", Font.BOLD, base.HUD_NOTES_FONT_SIZE));
        notesLabel.setForeground(new Color(0xFFFFFF));
        notesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notesLabel.setVerticalAlignment(SwingConstants.TOP);

        topRightPanel.setBackground(new Color(0x555555));

        topRightPanel.setLayout(new BorderLayout());
        topRightPanel.add(notesLabel, BorderLayout.CENTER);


        // RIGHT CORNER PANEL
        JPanel notesHeader = new JPanel();
        notesHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
        notesHeader.setBackground(new Color(0x222222));
        notesHeader.add(timeIndicator);
        notesHeader.add(liveIndicator);
        
        







        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.GRAY); // optional

        rightContainer.add(notesHeader, BorderLayout.NORTH);
        rightContainer.add(topRightPanel, BorderLayout.CENTER);
        rightContainer.add(bottomRightPanel, BorderLayout.SOUTH);

        // Add to frame
        this.add(leftPanel);
        this.add(rightContainer);

        this.setVisible(true);






        setVisible(true);
    }




}

