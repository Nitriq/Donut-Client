package donut.gui.hud;

import donut.gui.util.DonutButtons;
import donut.gui.modmenu.ModMenu;
import donut.util.rendering.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class HUDConfigScreen extends GuiScreen {

    //For Smooth Dragging
    private int smX, smY;

    private boolean dragged = false;

    protected boolean hovered;

    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();

    private Optional<IRenderer> selectedRenderer = Optional.empty();

    private int prevx, prevy;

    int fade;
    int bkfade;

    @Override
    public void initGui(){
        this.donutButtonsArrayList.add(new DonutButtons("Mods", this.width/2 - 50, this.height/2, 100, 25, "Mods"));
    }


    HUDConfigScreen(HUDManager api){

        Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();

        for(IRenderer ren : registeredRenderers){
            if(!ren.isEnabled()){
                continue;
            }

            ScreenPosition pos = ren.load();

            if(pos == null){
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }

            adjustBounds(ren, pos);
            this.renderers.put(ren, pos);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        final float zBackup = this.zLevel;
        this.zLevel = 200;

        drawHollowRect(0, 0, this.width - 1, this.height - 1, new Color(51, 200, 150).getRGB());

        for (IRenderer renderer : renderers.keySet()) {
            ScreenPosition pos = renderers.get(renderer);


            renderer.renderDummy(pos);

            //Starting of smooth dragging

            int absoluteX = pos.getAbsoluteX();
            int absoluteY = pos.getAbsoluteY();

            this.hovered = mouseX >= absoluteX - 2 && mouseX <= absoluteX - 2  + renderer.getWidth() + 2 && mouseY >= absoluteY - 2 && mouseY <= absoluteY - 2 + renderer.getHeight() + 2;


            if(this.hovered){
                if(this.fade != 250 && this.bkfade != 150){
                    this.fade += 10;
                    this.bkfade += 10;
                }
            }else{
                this.fade = 30;
                this.bkfade = 30;
            }



            Color a = new Color(51 ,200, 255, this.fade);
            Color b = new Color(0,0,0, this.bkfade);

            DrawUtil.drawRect(absoluteX - 2, absoluteY - 2, pos.getAbsoluteX() + renderer.getWidth(), pos.getAbsoluteY() + renderer.getHeight(), b.getRGB());
            DrawUtil.drawHollowRect(absoluteX - 2, absoluteY - 2, renderer.getWidth() + 2, renderer.getHeight() + 2, 1, a.getRGB());

            if (this.hovered) {

                if (dragged) {
                    pos.setAbsolute(pos.getAbsoluteX() + mouseX - this.prevx, pos.getAbsoluteY() + mouseY - this.prevy);

                    adjustBounds(renderer, pos);

                    this.prevx = mouseX;
                    this.prevy = mouseY;

                }

                //Ending of smooth dragging
            }

        }



        this.smX = mouseX;
        this.smY = mouseY;

        this.zLevel = zBackup;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public static void drawHollowRect(int x, int y, int w, int h, int color) {

        DrawUtil.drawHorizontalLine(x, x + w, y, color);
        DrawUtil.drawHorizontalLine(x, x + w, y + h, color);

        DrawUtil.drawVerticalLine(x, y + h, y, color);
        DrawUtil.drawVerticalLine(x + w, y + h, y, color);

    }


    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == Keyboard.KEY_ESCAPE || keyCode == mc.gameSettings.HUD.getKeyCode()){
            renderers.entrySet().forEach((entry) -> {
                entry.getKey().save(entry.getValue());
            });

            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {
        if(selectedRenderer.isPresent()){
            moveSelectedRenderBy(x - prevx, y - prevy);
        }

        this.prevx = x;
        this.prevy = y;
    }

    private void moveSelectedRenderBy(int offsetx, int offsety) {
        IRenderer renderer = selectedRenderer.get();
        ScreenPosition pos = renderers.get(renderer);

        pos.setAbsolute(pos.getAbsoluteX() + offsetx, pos.getAbsoluteY() + offsety);

        adjustBounds(renderer, pos);

    }

    @Override
    public void onGuiClosed() {
        for(IRenderer ren : renderers.keySet()){
            ren.save(renderers.get(ren));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private void adjustBounds(IRenderer renderer, ScreenPosition pos) {

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = sr.getScaledWidth();
        int screenHeight = sr.getScaledHeight();

        int absoluteX = Math.max(3, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth() - 3, 0)));
        int absoluteY = Math.max(3, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight() - 3, 0)));

        pos.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
        this.prevx = x;
        this.prevy = y;

        // NEEDED FOR SMOOTH DRAGGING
        dragged = true;

        loadMouseOver(x,y);
        super.mouseClicked(x, y, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        // NEEDED FOR SMOOTH DRAGGING
        dragged = false;

        super.mouseReleased(mouseX, mouseY, state);
    }

    private void loadMouseOver(int x, int y) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRenderer> {

        private int mouseX, mouseY;

        public MouseOverFinder(int x, int y){
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public boolean test(IRenderer renderer) {
            ScreenPosition pos = renderers.get(renderer);

            int absoluteX = pos.getAbsoluteX();
            int absoluteY = pos.getAbsoluteY();

            if(mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()){
                if(mouseY > absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
                    return true;
                }
            }

            return false;
        }
    }
    @Override
    protected void actionPerformed(DonutButtons buttons) throws IOException {
        switch (buttons.function) {
            case "Mods":
                this.mc.displayGuiScreen(new ModMenu());
        }
    }
}
