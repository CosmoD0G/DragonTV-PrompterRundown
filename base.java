import java.awt.Color;

public class base {
    // color constants
    public static final Color CTRL_SCRIPT_INACTIVE_BG_COLOR = new Color(0xF0F0F0);
    public static final Color CTRL_SCRIPT_ACTIVE_BG_COLOR = new Color(0x8AFF82);
    public static final Color CTRL_WARNING_BG_COLOR = new Color(0xEBC334);
    public static final Color CTRL_STANDARD_INACTIVE_BG_COLOR = new Color(0xFFFFFF);
    public static final Color CTRL_STANDARD_ACTIVE_BG_COLOR = new Color(0x8AFF82);

    public static final Color HUD_SCRIPT_INACTIVE_BG_COLOR = new Color(0x000000);
    public static final Color HUD_SCRIPT_ACTIVE_BG_COLOR = new Color(0x8AFF82);
    public static final Color HUD_WARNING_BG_COLOR = new Color(0xEBC334);
    public static final Color HUD_STANDARD_INACTIVE_BG_COLOR = new Color(0x111111);
    public static final Color HUD_STANDARD_ACTIVE_BG_COLOR = new Color(0x8AFF82);
    public static final Color HUD_BORDER_COLOR = new Color(0x222222);



    public static final int HUD_RUNDOWN_FONT_SIZE = 55;
    public static final int HUD_NOTES_FONT_SIZE = 45;
    public static final int HUD_SCRIPT_FONT_SIZE = 100;

    public static final int CRTL_SCROLL_SPEED = 16;

    public static final int WARNING_TIME = 7;

    public static String secondsToMMSS(int seconds) {
        String mm = "";
        String ss = "";
        int totalMin = seconds / 60;
        int sec = seconds % 60;

        if (sec < 10) {
            ss = "0"+String.valueOf(sec);
        } else {
            ss = String.valueOf(sec);
        }

        if (totalMin < 10) {
            mm = "0"+String.valueOf(totalMin);
        } else {
            mm = String.valueOf(totalMin);
        }

        return mm+":"+ss;
    }

    public static void main(String[] args) {

        controller ctrl = new controller();

        Dashboard cp = new Dashboard(ctrl);
        cp.setVisible(true);
        ctrl.setDashboard(cp);

        HUD hud = new HUD(ctrl);
        hud.setVisible(true);
    }
}
