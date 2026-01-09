import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class controller {
    private ArrayList<RundownHUDItem> rundown_panels;
    private ArrayList<RundownControllerItem> controller_items;
    private int current_index = -1;
    private boolean is_live = false;
    private boolean is_playing = false;
    private File csvFile;

    public void syncItemsToHUD() {
        rundown_panels.clear();
        for (RundownControllerItem item : controller_items) {
            String title = item.getTitle();
            String notes = item.getNotes();
            int duration = item.getDuration();
            RundownHUDItem hudItem = new RundownHUDItem(title, notes, duration);
            rundown_panels.add(hudItem);
        }
    }

    public void goToNext() {
        if (current_index + 1 < controller_items.size()) {
            current_index++;
        } else {
            current_index = -1;
            System.out.println("End of rundown reached");
        }
    }

    public void goToPrevious() {
        if (current_index - 1 >= 0) {
            current_index--;
        } else {
            current_index = -1;
            System.out.println("Start of rundown reached");
        }
    }

    public void goToTop() {
        current_index = -1;
    }

    public void toggleLive() {
        is_live = !is_live;
    }

    public void togglePlayPause() {
        is_playing = !is_playing;
    }

    public void clearRundown(boolean permission) {
        if (permission) {
            rundown_panels.clear();
            controller_items.clear();
            current_index = -1;
        }
    }

    public void setFile(File file) {
        csvFile = file;
    }

    public void loadFromFile() {
        if (csvFile != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line = "";
                int line_num = 0;
                while ((line = br.readLine()) != null) {
                    line_num++;
                    if (line_num == 1) {
                        continue; // skip header
                    }
                    String[] parts = line.split(",");
                    if (parts.length < 1) {
                        System.out.println("Invalid line at " + line_num + ": " + line);
                        continue;
                    }
                    String title = parts[0].trim();
                    String notes = "";
                    int duration = 0;
                    if (parts.length > 1) {
                        notes = parts[1].trim();
                    } else {
                        notes = "";
                    }
                    if (parts.length > 2) {
                        duration = Integer.parseInt(parts[2].trim());
                    } else {
                        duration = 0;
                    }
                    controller_items.add(new RundownControllerItem(title, notes, duration));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public controller() {
        rundown_panels = new ArrayList<>();
        controller_items = new ArrayList<>();
    }   
}
