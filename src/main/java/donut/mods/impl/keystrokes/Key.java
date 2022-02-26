package donut.mods.impl.keystrokes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;

public class Key {

    public static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindLeft, 20, 0, 18, 18);
    public static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindBack, 0, 20, 18, 18);
    public static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindRight, 20, 20, 18, 18);
    public static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindJump, 40, 20, 18, 18);

    public static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindPickBlock, 0, 40, 28, 18);
    public static final Key RMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindDrop, 30, 40, 28, 18);

    public static final Key Jump1 = new Key(EnumChatFormatting.STRIKETHROUGH + "------", Minecraft.getMinecraft().gameSettings.keyBindSneak, 0, 40, 58, 14);
    public static final Key Jump2 = new Key(EnumChatFormatting.STRIKETHROUGH + "------", Minecraft.getMinecraft().gameSettings.keyBindSneak, 0, 60, 58, 14);

    private final String name;
    private final KeyBinding keyBind;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Key(String name, KeyBinding keyBind, int x, int y, int width, int height){
        this.name = name;
        this.keyBind = keyBind;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isDown(){
        return keyBind.isKeyDown();
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
