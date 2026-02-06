import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import javax.swing.Timer;

public class controller {
    private Dashboard dashboard;
    private HUD hud;
    private ArrayList<HUDItem> rundown_panels;
    private ArrayList<DashboardItem> controller_items;
    private int current_index = -1;
    private boolean is_live = false;
    private boolean is_playing = false;
    private File csvFile;
    private int seconds_remaining = 0;
    private boolean separate_timer_mode = false;
    private boolean countdown_auto = false;
    private ScriptFrame scriptFrame;


    // set the HUD reference
    public void setHUD(HUD hud) {
        this.hud = hud;
    }

    // Set the dashboard reference
    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    // removes a specific DashboardItem from the controller's list
    public void removeControllerItem(DashboardItem item) {
        controller_items.remove(item);
    }

    // clears all DashboardItems from the controller's list
    public void clearControllerItems() {
        controller_items.clear();
    }
    
    // Take a specific item to program by index
    public void take(int target) {
        current_index = target;
        int i = 0;
        for (DashboardItem item : controller_items) {
            if (i == current_index) {
                item.setActive(true);
                System.out.println("Taking item " + i);
                if (item.isScript()) {
                    scriptFrame.displayScript(item.getNotesText());
                } else {
                    scriptFrame.displayScript("");
                }
            } else {
                item.setActive(false);
                System.out.println("Deactivating item " + i);
            }
            i++;
        }
    }

    // Advance the countdown by one second
    public void advanceSecond() {
        if (is_playing) {
            controller_items.get(current_index).advanceSecond();
        }
        syncItemsToHUD();
    }

    // Add a new DashboardItem to the controller's list
    public void addItem(DashboardItem item) {
        controller_items.add(item);
    }

    // update the HUD items to reflect the current controller items
    public void syncItemsToHUD() {
        rundown_panels.clear();
        for (DashboardItem item : controller_items) {
            HUDItem hudItem = new HUDItem(item);
            rundown_panels.add(hudItem);
        }
        hud.setRundown(rundown_panels, current_index+1);
    }

    // takes next rundown item
    public void goToNext() {
        if (current_index + 1 < controller_items.size()) {
            current_index++;
            take(current_index);
            System.out.println("Advancing to next item: " + current_index);
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
        take(0);
    }

    public void toggleLive() {
        is_live = !is_live;
    }

    public void togglePlayPause() {
        is_playing = !is_playing;
    }

    public void toggleAutomatic() {
        countdown_auto = !countdown_auto;
    }

    public void clearRundown(boolean permission) {
        if (permission) {
            rundown_panels.clear();
            controller_items.clear();
            current_index = -1;
        }
    }


    // ===================================
    // =========== CSV Loading ===========
    // ===================================

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
                    System.out.println("Reading line " + line_num + ": " + line);
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
                    controller_items.add(new DashboardItem(title, notes, duration, dashboard));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Loaded " + controller_items.size() + " items from CSV");
    }

    // ======= CSV Saving =======

    public ArrayList<DashboardItem> getControllerItems() {
        return controller_items;
    }

    public boolean isCountdownAuto() {
        return countdown_auto;
    }

    public boolean isPlaying() {
        return is_playing;
    }

    public controller() {
        rundown_panels = new ArrayList<>();
        controller_items = new ArrayList<>();
        scriptFrame = new ScriptFrame(this);

        Timer timer = new Timer(1000, e -> advanceSecond());
        timer.start();
    }

    public void setScriptFrame(ScriptFrame scriptFrame) {
        this.scriptFrame = scriptFrame;
    }   
}
