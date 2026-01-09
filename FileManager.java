import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileManager {

    public static File chooseCsvFile(JFrame parent) {
        JFileChooser chooser = new JFileChooser();

        // Only allow CSV files
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(
            new FileNameExtensionFilter("CSV Files (*.csv)", "csv")
        );

        int result = chooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }

        return null; // user cancelled
    }
}
