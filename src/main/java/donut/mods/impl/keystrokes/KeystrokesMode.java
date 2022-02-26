package donut.mods.impl.keystrokes;

public  enum KeystrokesMode {

    WASD(Key.W, Key.A, Key.S, Key.D),
    WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
    WASD_JUMP(Key.W, Key.A, Key.S, Key.D, Key.Jump1),
    WASD_JUMP_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, Key.Jump2);

    private final Key[] keys;
    private int width = 0;
    private int height = 0;

    private KeystrokesMode(Key... keysIn){
        this.keys = keysIn;

        for(Key key : keys){
            this.width = Math.max(this.width, key.getX() + key.getWidth());
            this.height = Math.max(this.height, key.getY() + key.getHeight());

        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Key[] getKeys() {
        return keys;
    }
}
