package donut.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;

public abstract class CosmeticModelBase extends ModelBase {
    protected final ModelBiped playerModel;

    public CosmeticModelBase(RenderPlayer player) {

        this.playerModel = player.getMainModel();

    }

    public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale);
}
