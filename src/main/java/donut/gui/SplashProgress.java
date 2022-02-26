package donut.gui;

import donut.util.UnicodeFontRenderer;
import donut.util.anim.Animation;
import donut.util.rendering.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SplashProgress {

    //Max amount of progress
    private static final int DEFAULT_MAX = 8;

    // Current progress
    private static int PROGRESS;

    // Background texture
    private static ResourceLocation splash;

    // Texture manager
    private static TextureManager ctm;

    // Font renderer
    private static UnicodeFontRenderer roboto;

    public static final SplashProgress INSTANCE = new SplashProgress();
    public static final SplashProgress getInstance() {
        return INSTANCE;
    }

    private Framebuffer frameBuffer;


    public static void update() {

        if (Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null) return;
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    public static void setProgress(int givenProgress) {
        PROGRESS = givenProgress;
        update();
    }

    public static void drawSplash(TextureManager tm) {
        // Initialize the texture manager if null
        if (ctm == null) ctm = tm;

        // Get the users screen width and height to apply
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());

        // Create the scale factor
        int scaleFactor = scaledresolution.getScaleFactor();

        // Bind the width and height to the framebuffer
        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * scaleFactor,
                scaledresolution.getScaledHeight() * scaleFactor, true);
        framebuffer.bindFramebuffer(false);

        // Create the projected image to be rendered
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        // Initialize the splash texture
        if (splash == null) splash = new ResourceLocation("donut/splash.png");

        // Bind the texture
        tm.bindTexture(splash);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        // Draw the image
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 1920, 1080);

        loading();


        // Unbind the width and height as it's no longer needed
        framebuffer.unbindFramebuffer();

        // Render the previously used frame buffer
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * scaleFactor, scaledresolution.getScaledHeight() * scaleFactor);

        // Update the texture to enable alpha drawing
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);

        // Update the users screen
        Minecraft.getMinecraft().updateDisplay();
    }

    public static void loading(){

        if (Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null)
            return;

        // Declare the font to be used
        if (roboto == null) roboto = UnicodeFontRenderer.getFontOnPC("Raleway", 20);

        // Get the users screen width and height to apply
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        float width = 240.0f;
        float height = 12f;

        float x = (float)sr.getScaledWidth() / 2.0f - 120f;
        float y = (float)sr.getScaledHeight() - 30f;

        float nProgress = PROGRESS;
        float calc = (nProgress / DEFAULT_MAX) * width;

        // Draw the transparent bar before the green bar
        DrawUtil.drawRoundedRect(x, y, x + width, y + height, 7.0f,  new Color(255, 255, 255, 255).getRGB());

        // Render the progress bar
        DrawUtil.drawRoundedRect(x, y, x + calc, y + height, 7.0f, new Color(0, 140, 255, 255).getRGB());
    }

}