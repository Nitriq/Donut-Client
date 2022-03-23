package donut.mods.impl.keystrokes;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class Keystrokes extends ModDraggable {

    public Keystrokes(){
        super("Keystrokes");
    }

    public boolean round = true;
    public boolean box = false;

    private KeystrokesMode mode = KeystrokesMode.WASD_JUMP_MOUSE;

    public void setMode(KeystrokesMode mode) {
        this.mode = mode;
    }

    @Override
    public int getWidth() {
        return mode.getWidth();
    }

    @Override
    public int getHeight() {
        return mode.getHeight();
    }

    @Override
    public void render(ScreenPosition pos) {
        GL11.glPushMatrix();



        for(Key key : mode.getKeys()) {

            int textWidth = font.getStringWidth(key.getName());

            if(round) {
                DrawUtil.drawRoundedRect(
                        pos.getAbsoluteX() + key.getX(),
                        pos.getAbsoluteY() + key.getY(),
                        pos.getAbsoluteX() + key.getX() + key.getWidth(),
                        pos.getAbsoluteY() + key.getY() + key.getHeight(),
                        2.0F,
                        key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 150).getRGB()
                );
                DrawUtil.drawRoundedOutline(
                        pos.getAbsoluteX() + key.getX(),
                        pos.getAbsoluteY() + key.getY(),
                        pos.getAbsoluteX() + key.getX() + key.getWidth(),
                        pos.getAbsoluteY() + key.getY() + key.getHeight(),
                        2.0F,
                        2.0f,
                        key.isDown() ? new Color(0, 0, 0, 150).getRGB() : new Color(255, 255, 255, 102).getRGB()
                );
            }else if(box){
                Gui.drawRect(
                        pos.getAbsoluteX() + key.getX(),
                        pos.getAbsoluteY() + key.getY(),
                        pos.getAbsoluteX() + key.getX() + getWidth(),
                        pos.getAbsoluteY() + key.getY() + getHeight(),
                        key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0,0,0,102).getRGB()
                );
            }

            font.drawString(key.getName(),
                    pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
                    pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4,
                    key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
            );

        }



        GL11.glPopMatrix();
    }
}
