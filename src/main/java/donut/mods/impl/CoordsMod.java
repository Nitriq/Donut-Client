package donut.mods.impl;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class CoordsMod extends ModDraggable {

    public CoordsMod(){
        super("Coords");
    }

    private final Minecraft mc = Minecraft.getMinecraft();
    private String biome;

    @Override
    public int getWidth() {
        return font.getStringWidth(getCoordsPlaceholder());
    }

    @Override
    public int getHeight() {
        return (font.FONT_HEIGHT * 5) -2;
    }

    @Override
    public void render(ScreenPosition pos) {
        DrawUtil.drawRoundedRect(pos.getAbsoluteX() - 2 , pos.getAbsoluteY() - 2 , pos.getAbsoluteX() + getWidth() + 2, pos.getAbsoluteY() + getHeight() + 2, 3,  0x90000000);
        font.drawStringWithShadow(String.format("X: %.0f", mc.getRenderViewEntity().posX), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        font.drawStringWithShadow(String.format("Y: %.0f", mc.getRenderViewEntity().posY), pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT + 2, -1);
        font.drawStringWithShadow(String.format("Z: %.0f", mc.getRenderViewEntity().posZ), pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 2) + 4, -1);

        Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(new BlockPos(Minecraft.getMinecraft().thePlayer));

        this.biome = chunk.getBiome(new BlockPos(Minecraft.getMinecraft().thePlayer), this.mc.theWorld.getWorldChunkManager()).biomeName;
        font.drawStringWithShadow("Biome: " + biome, pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 3) + 6, -1);
    }

    private String getCoordsPlaceholder() {
        if(font.getStringWidth("Biome: " + biome) > font.getStringWidth("X: 1000")) {
            return ("Biome: " + biome);
        }else{
            return "X: 1000";
        }
    }


    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("X: 999", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        font.drawStringWithShadow("Y: 999", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT + 2, -1);
        font.drawStringWithShadow("X: 999", pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 2) + 4, -1);
        font.drawStringWithShadow("Biome: Plains", pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 3) + 6, -1);
    }
}
