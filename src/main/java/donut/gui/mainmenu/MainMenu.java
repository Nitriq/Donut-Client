package donut.gui.mainmenu;

import donut.gui.util.DonutButtons;
import net.minecraft.client.gui.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class MainMenu extends GuiScreen {

    @Override
    public void initGui() {
        this.donutButtonsArrayList.add(new DonutButtons("Singleplayer", this.width / 2 - 75, this.height / 2 - 16, 150, 20, "Singleplayer")); //Singleplayer
        this.donutButtonsArrayList.add(new DonutButtons("Multiplayer", this.width / 2 - 75, this.height / 2 + 10, 150, 20, "Multiplayer")); //Multiplayer
        this.donutButtonsArrayList.add(new DonutButtons("Options", this.width / 2 - 75, this.height / 2 + 36, 150, 20, "Options")); //Settings
        this.donutButtonsArrayList.add(new DonutButtons("close", this.width - 23, 6, 18, 18, EnumChatFormatting.BOLD + "" ));//Exit
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
        Gui.drawModalRectWithCustomSizedTexture(width - 21, 6, 0, 0, 18, 18, 16, 17);
    }

    @Override
    protected void actionPerformed(final DonutButtons button) throws IOException {
        switch (button.function) {
            case "Singleplayer": {
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            }
            case "Multiplayer": {
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            }
            case "Options": {
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            }
            case "close": {
                this.mc.shutdown();
                break;
            }
        }
        super.actionPerformed(button);
    }
}
