import java.awt.*;
import java.util.ArrayList;
import java.lang.reflect.Array;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HUD extends JFrame {
    private final int MAX_ITEMS_ON_SCREEN = 10;
    private JPanel leftPanel = new JPanel();
    private JLabel notesLabel = new JLabel("Notes / Additional Info");
    private JPanel topRightPanel = new JPanel();
    private JLabel placeholderLabel = new JLabel("Awaiting rundown items...");

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

        // RIGHT CONTAINER (split top / bottom)
        JPanel rightContainer = new JPanel(new GridLayout(2, 1));

        notesLabel.setFont(new Font("Arial", Font.BOLD, 28));
        notesLabel.setForeground(new Color(0xFFFFFF));
        notesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notesLabel.setVerticalAlignment(SwingConstants.CENTER);
        topRightPanel.setBackground(new Color(0x555555));

        topRightPanel.setLayout(new BorderLayout());
        topRightPanel.add(notesLabel, BorderLayout.CENTER);








        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.GRAY); // optional

        rightContainer.add(topRightPanel);
        rightContainer.add(bottomRightPanel);

        // Add to frame
        this.add(leftPanel);
        this.add(rightContainer);

        this.setVisible(true);






        setVisible(true);
    }




}

