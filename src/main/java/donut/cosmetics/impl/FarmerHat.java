package donut.cosmetics.impl;

import java.io.IOException;

import donut.cosmetics.CosmeticBase;
import donut.cosmetics.CosmeticModelBase;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class FarmerHat extends CosmeticBase {
    private final Hat HatModel;

    public FarmerHat(RenderPlayer renderPlayer) {
        super(renderPlayer);
        this.HatModel = new Hat(renderPlayer);
    }

    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        GL11.glPushMatrix();
        if(player.isSneaking()) {
            GlStateManager.translate(0, 0.262, -0);
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("donut/cosmetics/hay_block_top.png"));

        GlStateManager.rotate(netHeadYaw, 0, 1, 0);
        GlStateManager.rotate(headPitch, 1, 0, 0);
        GlStateManager.translate(0, -1.95, 0);
        this.HatModel.render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glPopMatrix();
    }
    public class Hat extends CosmeticModelBase {

        private final ModelRenderer bb_main;

        public Hat(RenderPlayer player) {
            super(player);
            int textureWidth = 16;
            int textureHeight = 16;

            bb_main = new ModelRenderer(this);
            bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
            bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -1F, -8.0F, 16, 1, 16, 0.0F, false));
            bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -2.0F, -6.0F, 12, 1, 12, 0.0F, false));
            bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -3.0F, -4.0F, 8, 1, 8, 0.0F, false));
            bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, -4.0F, -2.0F, 4, 2, 4, 0.0F, false));
            bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -0.5F, -5.1F, -0.5F, 1, 2, 1, 0.0F, false));



        }

        @Override
        public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            bb_main.render(scale);

            GlStateManager.pushMatrix();

            GlStateManager.popMatrix();
        }



        private void setRotation(ModelRenderer model, float x, float y, float z)
        {
            model.rotateAngleX = x;
            model.rotateAngleY = y;
            model.rotateAngleZ = z;
        }
    }
}
