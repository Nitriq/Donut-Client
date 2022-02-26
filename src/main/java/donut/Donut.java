package donut;

import donut.cosmetics.CosmeticController;
import donut.event.EventManager;
import donut.event.EventTarget;
import donut.event.impl.ClientTickEvent;
import donut.gui.SplashProgress;
import donut.discord.DiscordRP;
import donut.gui.hud.HUDManager;
import donut.mods.ModInstances;
import donut.util.AntiCheat;
import donut.util.FileManager;
import donut.util.Logger;
import donut.util.SessionChanger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Donut {

	public String NAME = "Donut Client", VERSION = "1.0", AUTHOR = "Theju", NAMEVER = NAME + " " + VERSION;
	public static final Donut INSTANCE = new Donut();
	public static final Donut getInstance() {
		return INSTANCE;
	}
	public Minecraft mc = Minecraft.getMinecraft();
	private DiscordRP discordRP = new DiscordRP();
	private HUDManager hudManager;

	public void init()
	{
		AntiCheat.removeCheats();
		FileManager.init();
		discordRP.start();
		SplashProgress.setProgress(0);

		Logger.info("Starting " + NAMEVER+ " by " + AUTHOR);

		EventManager.register(this);
	}

	public void start(){
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
		SessionChanger.getInstance().setUserOffline("Theju1");
		discordRP.start();
	}
	
	public void shutdown() 
	{
		Logger.info("Shutting Down " + NAMEVER);
		discordRP.shutdown();

		EventManager.unregister(this);
	}

	public  DiscordRP getDiscordRP(){
		return discordRP;
	}

	@EventTarget
	public void onTick(ClientTickEvent e){
		if(mc.gameSettings.HUD.isPressed()){
			hudManager.openConfigScreen();
		}
	}

	public int getThemeColor(){
		return new Color(51,153,255, 150).getRGB();
	}

}
