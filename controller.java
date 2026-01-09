import java.util.*;

public class controller {
    private ArrayList<RundownHUDItem> rundown_panels;
    private ArrayList<RundownControllerItem> controller_items;
    private int current_index = -1;
    private boolean is_live = false;
    private boolean is_playing = false;

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
    
    public controller() {
        rundown_panels = new ArrayList<>();
        controller_items = new ArrayList<>();
    }   
}
