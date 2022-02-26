package donut.util.rendering;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import static org.lwjgl.opengl.GL11.*;

public class DrawUtil {

    /**
     * Draw a 1 pixel wide horizontal line. Args: x1, x2, y, color
     */
    public static void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

    /**
     * Draw a 1 pixel wide vertical line. Args : x, y1, y2, color
     */
    public static void drawVerticalLine(int x, int startY, int endY, int color)
    {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }

    public static void drawRect(float left, float top, float right, float bottom, final int color) {
        if (left < right) {
            final float i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final float j = top;
            top = bottom;
            bottom = j;
        }
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GuiUtils.setGlColor(color);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double)left, (double)bottom, 0.0).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0.0).endVertex();
        worldrenderer.pos((double)right, (double)top, 0.0).endVertex();
        worldrenderer.pos((double)left, (double)top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawColoredRect(final float x, final float y, final float x2, final float y2, final int color, final int color2) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        GuiUtils.setGlColor(color);
        worldRenderer.pos((double)x, (double)y2, 0.0).endVertex();
        GuiUtils.setGlColor(color2);
        worldRenderer.pos((double)x2, (double)y2, 0.0).endVertex();
        worldRenderer.pos((double)x2, (double)y, 0.0).endVertex();
        GuiUtils.setGlColor(color);
        worldRenderer.pos((double)x, (double)y, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void drawGradientRect(final int left, final int top, final int right, final int bottom, final int coltl, final int coltr, final int colbl, final int colbr, final int zLevel) {
        drawGradientRect(left, top, right, (float)bottom, coltl, coltr, colbl, colbr, zLevel);
    }

    public static void drawGradientRect(final float left, final float top, final float right, final float bottom, final int coltl, final int coltr, final int colbl, final int colbr, final int zLevel) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer buffer = tessellator.getWorldRenderer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos((double)right, (double)top, (double)zLevel).color((coltr & 0xFF0000) >> 16, (coltr & 0xFF00) >> 8, coltr & 0xFF, (coltr & 0xFF000000) >>> 24).endVertex();
        buffer.pos((double)left, (double)top, (double)zLevel).color((coltl & 0xFF0000) >> 16, (coltl & 0xFF00) >> 8, coltl & 0xFF, (coltl & 0xFF000000) >>> 24).endVertex();
        buffer.pos((double)left, (double)bottom, (double)zLevel).color((colbl & 0xFF0000) >> 16, (colbl & 0xFF00) >> 8, colbl & 0xFF, (colbl & 0xFF000000) >>> 24).endVertex();
        buffer.pos((double)right, (double)bottom, (double)zLevel).color((colbr & 0xFF0000) >> 16, (colbr & 0xFF00) >> 8, colbr & 0xFF, (colbr & 0xFF000000) >>> 24).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawRectOutline(final float left, final float top, final float right, final float bottom, final float thickness, final int color) {
        final float width = 0.55f + 2;
        drawRect(left - width, top - width, right + width, top, color);
        drawRect(right, top, right + width, bottom, color);
        drawRect(left - width, bottom, right + width, bottom + width, color);
        drawRect(left - width, top, left, bottom, color);
    }

    public static void drawRoundedRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float radius, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedRect(paramInt1, paramInt2, paramInt3, paramInt4, radius);
    }

    public static void drawRoundedRect(float paramInt1, float paramInt2, float paramInt3, float paramInt4, float radius, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedRect(paramInt1, paramInt2, paramInt3, paramInt4, radius);
    }

    public static void drawRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
        int i = 18;
        float f1 = 90.0F / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glBegin(5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(paramFloat1, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(paramFloat3, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(6);
        float f2 = paramFloat3 - paramFloat5;
        float f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);
        int j;
        for (j = 0; j <= i; j++) {
            float f4 = j * f1;
            GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= i; j++) {
            float f4 = j * f1;
            GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= i; j++) {
            float f4 = j * f1;
            GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = paramFloat3 - paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= i; j++) {
            float f4 = j * f1;
            GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }

    public static void drawRoundedOutline(final int x, final int y, final int x2, final int y2, final float radius, final float width, final int color) {
        GuiUtils.setGlColor(color);
        drawRoundedOutline(x, y, x2, y2, radius, width);
    }

    public static void drawRoundedOutline(final float x, final float y, final float x2, final float y2, final float radius, final float width, final int color) {
        GuiUtils.setGlColor(color);
        drawRoundedOutline(x, y, x2, y2, radius, width);
    }

    public static void drawRoundedOutline(final float x, final float y, final float x2, final float y2, final float radius, final float width) {
        final int i = 18;
        final int j = 90 / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(2848);
        if (width != 1.0f) {
            GL11.glLineWidth(width);
        }
        GL11.glBegin(3);
        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x2 - radius, y);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x2, y + radius);
        GL11.glVertex2f(x2, y2 - radius);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x2 - radius, y2 - 0.1f);
        GL11.glVertex2f(x + radius, y2 - 0.1f);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x + 0.1f, y2 - radius);
        GL11.glVertex2f(x + 0.1f, y + radius);
        GL11.glEnd();
        float f1 = x2 - radius;
        float f2 = y + radius;
        GL11.glBegin(3);
        for (int k = 0; k <= i; ++k) {
            final int m = 90 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        f1 = x2 - radius;
        f2 = y2 - radius;
        GL11.glBegin(3);
        for (int k = 0; k <= i; ++k) {
            final int m = k * j + 270;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; ++k) {
            final int m = k * j + 90;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; ++k) {
            final int m = 270 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (width != 1.0f) {
            GL11.glLineWidth(1.0f);
        }
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }

    public static void drawRoundedOutlineGradient(final float x, final float y, final float x2, final float y2, final float radius, final float width, final int color, final int color2) {
        final int i = 18;
        final int j = 90 / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        if (width != 1.0f) {
            GL11.glLineWidth(width);
        }
        GuiUtils.setGlColor(color);
        GL11.glShadeModel(7425);
        GL11.glBegin(3);
        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x2 - radius, y);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x2, y + radius);
        GuiUtils.setGlColor(color2);
        GL11.glVertex2f(x2, y2 - radius);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x2 - radius, y2 - 0.1f);
        GL11.glVertex2f(x + radius, y2 - 0.1f);
        GL11.glEnd();
        GL11.glBegin(3);
        GuiUtils.setGlColor(color2);
        GL11.glVertex2f(x + 0.1f, y2 - radius);
        GuiUtils.setGlColor(color);
        GL11.glVertex2f(x + 0.1f, y + radius);
        GL11.glEnd();
        float f1 = x2 - radius;
        float f2 = y + radius;
        GuiUtils.setGlColor(color);
        GL11.glBegin(3);
        for (int k = 0; k <= i; ++k) {
            final int m = 90 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        f1 = x2 - radius;
        f2 = y2 - radius;
        GuiUtils.setGlColor(color2);
        GL11.glBegin(3);
        for (int k = 0; k <= i; ++k) {
            final int m = k * j + 270;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        GuiUtils.setGlColor(color2);
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; ++k) {
            final int m = k * j + 90;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        GuiUtils.setGlColor(color);
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; ++k) {
            final int m = 270 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        if (width != 1.0f) {
            GL11.glLineWidth(1.0f);
        }
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }

    public static void drawHollowRect(float startX, float startY, float width, float height, float lineWidth, int color) {
        float endX = startX + width, endY = startY + height;

        drawRect(startX, startY, endX, startY + lineWidth, color);
        drawRect(startX, endY, endX + lineWidth, endY + lineWidth, color);
        drawRect(startX, startY, startX + lineWidth, endY, color);
        drawRect(endX, startY, endX + lineWidth, endY, color);
    }



}
