package donut.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class IconButton extends GuiButton {

    private final ResourceLocation img;
    private int scale;
    private final Minecraft mc = Minecraft.getMinecraft();


    public IconButton(int buttonId, int x, int y, int scale, ResourceLocation img) {
        super(buttonId, x, y, scale, scale,"no text lol");
        this.img= img;
        this.scale = scale;
    }


    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(visible){
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);


            if(hovered){
                if(scale <= (int) (scale * 1.15)){
                    scale += 5;
                }

                int a = scale;

                drawScaledImage((int) (xPosition / 1.01),(int) (yPosition / 1.01), a, img);
            } else {
                drawScaledImage(xPosition,yPosition, scale, img);
            }
        }
    }

    public ResourceLocation getImage() {
        return img;
    }

    /**
     * Draws scaled image
     * Make sure that you binded texture :)
     * @param x
     * @param y
     * @param scale
     */
    private final void drawScaledImage(int x,int y, int scale, ResourceLocation img){
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(img);
        GlStateManager.enableAlpha();
        Gui.drawModalRectWithCustomSizedTexture(x,y,scale,scale,scale,scale,scale,scale);
        GlStateManager.disableAlpha();
    }
}
