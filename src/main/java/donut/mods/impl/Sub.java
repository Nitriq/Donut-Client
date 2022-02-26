package donut.mods.impl;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;

public class Sub extends ModDraggable {

    @Override
    public int getWidth() {
        return font.getStringWidth("Sub to ThejuKushi!");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        DrawUtil.drawRoundedRect(pos.getAbsoluteX() - 2 , pos.getAbsoluteY() - 2 , pos.getAbsoluteX() + font.getStringWidth("Sub to ThejuKushi!") + 2, pos.getAbsoluteY() + getHeight() + 2, 3,  0x90000000);
        font.drawStringWithShadow("Sub to ThejuKushi!", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }


    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Sub to ThejuKushi!", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

}
