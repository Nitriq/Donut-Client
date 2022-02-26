package donut.discord;

import donut.util.Logger;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {

    private boolean isRunning;
    private long created = 0;

    public void start() {
        this.created = System.currentTimeMillis();

        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {

            @Override
            public void apply(DiscordUser user) {
                Logger.info("Welcome " + user.username + "#" + user.discriminator + ".");
                update("Booting up....", "");
            }

        }).build();

        DiscordRPC.discordInitialize("890094520342024203", handlers, true);

        new Thread("Discord RPC Callback") {

            @Override
            public void run() {
                while(isRunning) {
                    DiscordRPC.discordRunCallbacks();
                }
            }

        }.start();
    }

    public void shutdown() {
        isRunning = false;
        DiscordRPC.discordShutdown();
    }

    public void update(String firstline, String secondline) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondline);
        b.setBigImage("large", "");
        b.setDetails(firstline);
        b.setStartTimestamps(created);

        DiscordRPC.discordUpdatePresence(b.build());
    }


}
