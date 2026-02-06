import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void saveToCSV(ArrayList<DashboardItem> items) {
        JFileChooser fileChooser = new JFileChooser();

        // Optional: only allow CSV files
        fileChooser.setDialogTitle("Save CSV File");
        fileChooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv")
        );

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Ensure .csv extension
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                for (DashboardItem i : items) {
                    String line = (i.getTitle()+","+i.getNotesText()+","+i.getDuration()+"\n");
                    writer.write(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
