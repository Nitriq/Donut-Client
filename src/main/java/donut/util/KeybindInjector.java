package donut.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class KeybindInjector {

    public static void registerKeyBind(KeyBinding key) {
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, key);
    }

    public static void unregisterKeybind(KeyBinding key) {
        if (Arrays.asList(Minecraft.getMinecraft().gameSettings.keyBindings).contains(key))
            Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.remove(Minecraft.getMinecraft().gameSettings.keyBindings, Arrays.asList(Minecraft.getMinecraft().gameSettings.keyBindings).indexOf(key));
    }

}