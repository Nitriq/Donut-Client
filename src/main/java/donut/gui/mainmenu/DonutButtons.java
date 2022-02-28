package donut.gui.mainmenu;

import java.awt.*;

import donut.util.UnicodeFontRenderer;
import donut.util.anim.Animation;
import donut.util.rendering.DrawUtil;
import donut.util.rendering.FontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.StringUtils;
import org.newdawn.slick.UnicodeFont;

public class DonutButtons extends GuiButton {


    public int alpha = 0;


    public boolean ThreadRunning = false;

    Minecraft mc = Minecraft.getMinecraft();

    public DonutButtons(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        int j;
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            DrawUtil.drawRoundedOutline(xPosition, yPosition, xPosition + width, yPosition + height, 4, 4);
            DrawUtil.drawRoundedRect(xPosition, yPosition, xPosition + width, yPosition + height, 4, 0xAF353535);

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);


            this.mouseDragged(mc, mouseX, mouseY);
            int fade;
            j = new Color(224, 224, 224, 255).getRGB();

            if (!this.enabled) {
                j = new Color(160, 160, 160, 255).getRGB();
            } else if (this.hovered) {

                fade = (int) new Animation(3, 50, 200).getValue();

                j = new Color(85, 170, 255, fade).getRGB();

            }

            this.drawCenteredString(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);

        }
    }

    public void drawCenteredString(String text, int x, int y, int color) {
        mc.fontRendererObj.drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, color);
    }


}

