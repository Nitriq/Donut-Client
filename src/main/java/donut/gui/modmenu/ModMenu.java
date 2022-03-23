package donut.gui.modmenu;

import donut.Donut;
import donut.gui.hud.HUDConfigScreen;
import donut.gui.hud.HUDManager;
import donut.gui.hud.ScreenPosition;
import donut.gui.util.DonutButtons;
import donut.gui.util.IconButton;
import donut.util.rendering.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class ModMenu extends GuiScreen {

    @Override
    public void initGui() {
        this.donutButtonsArrayList.add(new DonutButtons("home" ,width/2 - 210, height/2 - 125, 20, 20, 20, 20,"donut/modmenu/home.png"));

        super.initGui();
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks){


        DrawUtil.drawRoundedRect(width/2 - 210, height/2 - 125, width/2 - 190, height/2 + 125, 3 , Donut.INSTANCE.getThemeColor());
        DrawUtil.drawRoundedRect(width /2 - 180, height/2 - 125, width /2 + 220, height/2 + 125, 3, Donut.INSTANCE.getThemeColor());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame(){
        return true;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == Keyboard.KEY_ESCAPE){
            HUDManager.getInstance().openConfigScreen();
        }
    }

    @Override
    protected void actionPerformed(DonutButtons button) throws IOException {

        switch (button.function){
            case "home":

        }

        super.actionPerformed(button);
    }
}
