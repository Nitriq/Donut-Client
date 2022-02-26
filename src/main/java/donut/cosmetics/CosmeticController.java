package donut.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticController {

    private static Minecraft mc = Minecraft.getMinecraft();

    //TODO: Redo with database
    public static boolean shouldRenderWitchHat(AbstractClientPlayer player){
        return mc.getSession().getUsername() == "Theju1";
    }

}
