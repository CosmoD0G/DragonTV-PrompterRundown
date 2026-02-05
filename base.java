
class Base {
    public static void main(String[] args) {
        controller ctrl = new controller();

        Dashboard cp = new Dashboard(ctrl);
        cp.setVisible(true);
        ctrl.setDashboard(cp);

        HUD hud = new HUD(ctrl);
        hud.setVisible(true);
    }
}
