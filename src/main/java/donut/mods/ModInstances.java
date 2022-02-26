package donut.mods;

import donut.gui.hud.HUDManager;
import donut.mods.impl.*;
import donut.mods.impl.keystrokes.Keystrokes;
import donut.mods.impl.togglesprintsneak.ToggleSprintSneak;

public class ModInstances {

    private static Sub sub;

    private static ArmorHUD armor;

    private static FPSMod fpsMod;

    private static PingMod pingMod;

    private static CoordsMod coordsMod;

    private static Keystrokes keystrokes;

    private static ToggleSprintSneak toggle;

    private static CPSMod cpsMod;

    public static void register(HUDManager api){
        sub = new Sub();
        api.register(sub);

        armor = new ArmorHUD();
        api.register(armor);

        fpsMod = new FPSMod();
        api.register(fpsMod);

        pingMod = new PingMod();
        api.register(pingMod);

        coordsMod = new CoordsMod();
        api.register(coordsMod);

        keystrokes = new Keystrokes();
        api.register(keystrokes);

        toggle = new ToggleSprintSneak();
        api.register(toggle);

        cpsMod = new CPSMod();
        api.register(cpsMod);
    }

    public static Sub getSub() {
        return sub;
    }

    public static ToggleSprintSneak getToggle(){
        return toggle;
    }
}
