package donut.mods.impl;

import donut.gui.hud.ScreenPosition;
import donut.mods.ModDraggable;
import donut.util.rendering.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ArmorHUD extends ModDraggable {

    Minecraft mc = Minecraft.getMinecraft();

    @Override
    public int getWidth() {
        return 17;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void render(ScreenPosition pos) {
        DrawUtil.drawRoundedRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, pos.getAbsoluteX() + getWidth() + 2, pos.getAbsoluteY() + getHeight() + 2, 3, 0x90000000);
        for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++){
            ItemStack itemStack = this.mc.thePlayer.inventory.armorInventory[i];
            ItemStack itemStack1 = this.mc.thePlayer.inventory.armorItemInSlot(i);
            renderItemStack(pos, i, itemStack);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
        renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
        renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
        renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
    }

    private void renderItemStack(ScreenPosition pos, int i, ItemStack itemStack) {
        if(itemStack == null){
            return;
        }

        GL11.glPushMatrix();
        int yAdd = (-16 * i) + 48;

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
        GL11.glPopMatrix();
    }
}
