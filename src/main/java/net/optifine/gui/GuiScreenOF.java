package net.optifine.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;

public class GuiScreenOF extends GuiScreen
{
    protected void actionPerformedRightClick(GuiButton button) throws IOException
    {
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (mouseButton == 1)
        {
            GuiButton GuiButton = getSelectedButton(mouseX, mouseY, this.buttonList);

            if (GuiButton != null && GuiButton.enabled)
            {
                GuiButton.playPressSound(this.mc.getSoundHandler());
                this.actionPerformedRightClick(GuiButton);
            }
        }
    }

    public static GuiButton getSelectedButton(int x, int y, List<GuiButton> listButtons)
    {
        for (int i = 0; i < listButtons.size(); ++i)
        {
            GuiButton GuiButton = (GuiButton)listButtons.get(i);

            if (GuiButton.visible)
            {
                int j = GuiVideoSettings.getButtonWidth(GuiButton);
                int k = GuiVideoSettings.getButtonHeight(GuiButton);

                if (x >= GuiButton.xPosition && y >= GuiButton.yPosition && x < GuiButton.xPosition + j && y < GuiButton.yPosition + k)
                {
                    return GuiButton;
                }
            }
        }

        return null;
    }
}
