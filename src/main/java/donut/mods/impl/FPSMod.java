package donut.mods.impl;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;

public class FPSMod extends ModDraggable {

    public FPSMod(){
        super("FPS");
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("FPS: 9999");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        DrawUtil.drawRoundedRect(pos.getAbsoluteX() - 2 , pos.getAbsoluteY() - 2 , pos.getAbsoluteX() + font.getStringWidth("FPS: " + mc.getDebugFPS()) + 2, pos.getAbsoluteY() + getHeight() + 2, 3,  0x90000000);
        font.drawStringWithShadow("FPS: " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("FPS: 9999", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }
}
