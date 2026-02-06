import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class ScriptFrame extends JFrame {
    public ScriptFrame(controller ctrl) {
        ctrl.setScriptFrame(this);
        setTitle("Script Frame");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0x1F1F1F));

        // Main layout
        this.setLayout(new BorderLayout());

        JTextArea scriptArea = new JTextArea();
        scriptArea.setBackground(new Color(0x2E2E2E));
        scriptArea.setForeground(Color.WHITE);
        scriptArea.setFont(new Font("Arial", Font.PLAIN, 100));
        scriptArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        scriptArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        scriptArea.setLineWrap(true);
        scriptArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(scriptArea);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void displayScript(Object notesText) {
        if (notesText != null) {
            JTextArea scriptArea = (JTextArea) ((JScrollPane) this.getContentPane().getComponent(0)).getViewport().getView();
            scriptArea.setText(notesText.toString());
            scriptArea.setCaretPosition(0); // scroll to top
        }
        this.repaint();
        this.revalidate();

    }
}
