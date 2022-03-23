package donut.gui.util;

import java.awt.*;

import donut.util.UnicodeFontRenderer;
import donut.util.anim.Animation;
import donut.util.rendering.DrawUtil;
import donut.util.rendering.FontUtils;
import donut.util.rendering.GLUtils;
import donut.util.rendering.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.newdawn.slick.UnicodeFont;

public class DonutButtons extends Gui{

    protected int width;
    protected int height;
    protected int textureWidth;
    protected int textureHeight;
    protected int xPosition;
    protected int yPosition;
    public String function;
    protected ResourceLocation imageLocation;
    protected String displayString;
    private boolean isImage;
    int fade;
    int bkfade;


    float widthT = this.width;
    float heightT = this.height;
    float Twidth = this.textureWidth;
    float Theight = this.textureHeight;


    private boolean hovered;

    public DonutButtons(String buttonFunction, int x, int y, int widthIn, int heightIn,int textureWidthIn, int textureHeightIn, String location)
    {
        this.displayString = "";
        this.function = buttonFunction;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;
        this.imageLocation = new ResourceLocation(location);
        isImage = true;
    }

    public DonutButtons(String buttonFunction, int x, int y, int widthIn, int heightIn, String buttonString)
    {
        this.imageLocation = null;
        this.function = buttonFunction;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonString;
        isImage = false;
    }


    public void drawButton(Minecraft mc, int mouseX, int mouseY) {

        FontRenderer fontrenderer = mc.fontRendererObj;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        int i = this.getHoverState(this.hovered);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        if(!isImage) {

            if(!hovered) {
                this.bkfade = 100;
            }
            else {
                if(this.bkfade <= 30) {
                    return;
                }
                if(this.bkfade != 150) {
                    this.bkfade += 10;
                }
            }

            int bkColor = new Color(0,0,0, bkfade).getRGB();

            DrawUtil.drawRoundedRect((float)this.xPosition, (float)this.yPosition, (float)this.xPosition + (float)this.width, (float)this.yPosition + (float)this.height, 3.0F, (int) bkColor);
            this.mouseDragged(mc, mouseX, mouseY);


            if(!hovered) {
                    this.fade = 90;
            }
            else {
                if(this.fade <= 30) {
                    return;
                }
                if(this.fade != 200) {
                    this.fade += 5;
                }
            }

            int b = this.hovered ? new Color(51,200,250, this.fade).getRGB() : new Color(255,255,255,255).getRGB();


            DrawUtil.drawRoundedOutline((float)this.xPosition, (float)this.yPosition, (float)this.xPosition + (float)this.width, (float)this.yPosition + (float)this.height, 3.0F, 2 , b);

            this.drawCenteredString(fontrenderer,displayString, (int) (this.xPosition + this.width / 2 + 0.7f), this.yPosition + (this.height - 8) / 2,  b );
        }else if(isImage) {
            this.mouseDragged(mc, mouseX, mouseY);

            if(this.hovered) {
                widthT = width + 2;
                heightT = height + 2;
                
            }else{
                widthT = width;
                heightT = height;
            }
            mc.getTextureManager().bindTexture(imageLocation);
            GuiUtils.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, 0, 0, (int) this.widthT, (int) this.heightT, this.textureWidth, this.textureHeight);
        }
    }



    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver()
    {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY)
    {
    }


    public int getButtonWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    protected int getHoverState(boolean mouseOver)
    {
        int i = 0;
        if (mouseOver)
        {
            i = 1;
        }

        return i;
    }

    public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }
}

