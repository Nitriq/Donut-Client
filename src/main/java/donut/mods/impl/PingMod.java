package donut.mods.impl;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;
import net.minecraft.client.Minecraft;

public class PingMod extends ModDraggable {

    @Override
    public int getWidth() {
        return font.getStringWidth("Ping: 999ms");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        DrawUtil.drawRoundedRect(pos.getAbsoluteX() - 2 , pos.getAbsoluteY() - 2 , pos.getAbsoluteX() + font.getStringWidth("Ping: " +  (Minecraft.getMinecraft().isSingleplayer() ? ("-1") : Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime()) + "ms") + 2, pos.getAbsoluteY() + getHeight() + 2, 3,  0x90000000);
        font.drawStringWithShadow("Ping: " + (Minecraft.getMinecraft().isSingleplayer() ? ("-1") : Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + "ms"), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawString("Ping: 999ms" , pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }
}
