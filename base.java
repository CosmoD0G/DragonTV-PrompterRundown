
class Base {
    public static void main(String[] args) {
        controller ctrl = new controller();

        ControlPanel cp = new ControlPanel(ctrl);
        cp.setVisible(true);

        HUD hud = new HUD();
        hud.setVisible(true);
    }
}
