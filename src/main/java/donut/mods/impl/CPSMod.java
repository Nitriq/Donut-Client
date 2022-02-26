package donut.mods.impl;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public class CPSMod extends ModDraggable {

    private List<Long> clicks = new ArrayList<Long>();
    private boolean wasPressed;
    private long lastPressed;

    private List<Long> rightClicks = new ArrayList<Long>();
    private long lastPressed2;
    private boolean wasPressed2;

    @Override
    public int getWidth() {
        return font.getStringWidth(getCPS() + " : " + getRCPS() + " CPS");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        final boolean pressed = Mouse.isButtonDown(0);
        final boolean rpressed = Mouse.isButtonDown(1);

        if (pressed != this.wasPressed){
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = pressed;
            if(pressed){
                this.clicks.add(this.lastPressed);
            }
        }

        if(rpressed != this.wasPressed2)
        {
            this.lastPressed2 = System.currentTimeMillis();
            this.wasPressed2 = rpressed;
            if(rpressed)
            {
                this.rightClicks.add(this.lastPressed2);
            }
        }

        DrawUtil.drawRoundedRect(pos.getAbsoluteX() - 2 , pos.getAbsoluteY() - 2 , pos.getAbsoluteX() + font.getStringWidth(getCPS() + " : " + getRCPS() + " CPS") + 2, pos.getAbsoluteY() + getHeight() + 2, 3,  0x90000000);
        font.drawStringWithShadow(getCPS() + " : " + getRCPS() + " CPS", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("9 : 9 CPS", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    private int getCPS(){

        final long time = System.currentTimeMillis();
        this.clicks.removeIf(aLong -> aLong + 1000 < time);
        return this.clicks.size();

    }

    private int getRCPS()
    {
        final long time2 = System.currentTimeMillis();
        this.rightClicks.removeIf(aLong2 -> aLong2 + 1000 < time2);
        return this.rightClicks.size();
    }
}
