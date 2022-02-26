package donut.mods;

import donut.Donut;
import donut.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {

    private boolean isEnabled = true;

    protected final Minecraft mc;
    protected final FontRenderer font;
    protected final Donut donut;

    public Mod(){
        this.mc = Minecraft.getMinecraft();
        this.font = this.mc.fontRendererObj;
        this.donut = Donut.getInstance();

        setEnabled(isEnabled);
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;

        if(isEnabled){
            EventManager.register(this);
        }
        else{
            EventManager.unregister(this);
        }
    }

    public boolean isEnabled(){
        return isEnabled;
    }

}
