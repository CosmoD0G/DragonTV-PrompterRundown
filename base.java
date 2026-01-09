
class Base {
    public static void main(String[] args) {
        controller ctrl = new controller();

        Dashboard cp = new Dashboard(ctrl);
        cp.setVisible(true);

        HUD hud = new HUD();
        hud.setVisible(true);
    }
}
