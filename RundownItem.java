public class RundownItem {
    private String title = "";
    private String notes = "";
    private int duration = 0;

    public RundownItem() {

    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public int getDuration() {
        return duration;
    }

    public void setData(String title, String notes, int duration) {
        this.title = title;
        this.notes = notes;
        this.duration = duration;
    }
}