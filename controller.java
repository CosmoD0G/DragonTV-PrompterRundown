import java.util.*;

public class controller {
    private ArrayList<RundownHUDItem> rundown_panels;
    private ArrayList<RundownControllerItem> controller_items;


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



    public controller() {
        rundown_panels = new ArrayList<>();
        controller_items = new ArrayList<>();
    }   
}
