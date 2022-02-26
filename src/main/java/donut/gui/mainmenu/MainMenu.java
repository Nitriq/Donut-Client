package donut.gui.mainmenu;

import net.minecraft.client.gui.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.awt.Color;
import java.io.IOException;

public class MainMenu extends GuiScreen {

    @Override
    public void initGui() {
        this.buttonList.add(new DonutButtons(1, this.width / 2 - 75, this.height / 2 - 16, 150, 20, "Singleplayer")); //Singleplayer
        this.buttonList.add(new DonutButtons(2, this.width / 2 - 75, this.height / 2 + 10, 150, 20, "Multiplayer")); //Multiplayer
        this.buttonList.add(new DonutButtons(3, this.width / 2 - 75, this.height / 2 + 36, 150, 20, "Options")); //Settings
        this.buttonList.add(new DonutButtons(4, this.width - 23, 6, 18, 20, EnumChatFormatting.BOLD + ""));//Exit

        super.initGui();
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(new ResourceLocation("donut/mainmenu.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0f, 0.0f, this.width + 20, this.height + 20, (float)(this.width + 21), (float)(this.height + 20));
        final String s1 = "Copyright " + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "M" + EnumChatFormatting.RESET + "ojang. Do not Disturb! ";
        this.drawString(this.fontRendererObj, s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
        mc.getTextureManager().bindTexture(new ResourceLocation("donut/exit.png"));
        Gui.drawModalRectWithCustomSizedTexture(width - 21, 6, 0, 0, 18, 19, 16, 17);
    }

    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            }
            case 2: {
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            }
            case 3: {
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            }
            case 4: {
                this.mc.shutdown();
                break;
            }
        }
        super.actionPerformed(button);
    }
}
