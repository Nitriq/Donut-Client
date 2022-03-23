package donut.mods.impl.togglesprintsneak;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;

public class ToggleSprintSneak extends ModDraggable {

    public ToggleSprintSneak(){
        super("ToggleSprintSneak");
    }

    //settings
    public boolean flyBoost = true;
    public boolean sneakEnabled = false;
    public boolean sprintenabled = false;
    public float flyBoostFactor = 4;
    public int keyHoldTicks = 7;

    @Override
    public int getWidth() {
        return font.getStringWidth(mc.thePlayer.movementInput.displayText());
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        DrawUtil.drawRoundedRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, pos.getAbsoluteX() + font.getStringWidth(mc.thePlayer.movementInput.displayText()) + 2, pos.getAbsoluteY() + getHeight() + 2, 3, 0x90000000);
        font.drawStringWithShadow(mc.thePlayer.movementInput.displayText(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("[Sprinting (Key Held)]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }
}
