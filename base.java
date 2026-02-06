import java.awt.Color;

public class base {
    // color constants
    public static final Color SCRIPT_INACTIVE_BG_COLOR = new Color(0xF0F0F0);
    public static final Color SCRIPT_ACTIVE_BG_COLOR = new Color(0x8AFF82);
    public static final Color STANDARD_INACTIVE_BG_COLOR = new Color(0xFFFFFF);
    public static final Color STANDARD_ACTIVE_BG_COLOR = new Color(0x8AFF82);

    public static void main(String[] args) {

        controller ctrl = new controller();

        Dashboard cp = new Dashboard(ctrl);
        cp.setVisible(true);
        ctrl.setDashboard(cp);

        HUD hud = new HUD(ctrl);
        hud.setVisible(true);
    }
}
