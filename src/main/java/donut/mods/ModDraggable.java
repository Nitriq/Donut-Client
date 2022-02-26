package donut.mods;

import donut.gui.hud.IRenderer;
import donut.gui.hud.ScreenPosition;
import donut.util.FileManager;

import java.io.File;

public abstract class ModDraggable extends Mod implements IRenderer {

    protected ScreenPosition pos;

    public ModDraggable(){
        pos = loadPositionFromFile();
    }

    @Override
    public ScreenPosition load() {
        return pos;
    }

    private File getFolder(){
        File folder = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
        savePositionFromFile();
    }

    private void savePositionFromFile() {
        FileManager.writeJsonToFile(new File(getFolder(), "pos.json"), pos);
    }

    private ScreenPosition loadPositionFromFile() {
        ScreenPosition loaded = FileManager.readfromJson(new File(getFolder(), "pos.json"), ScreenPosition.class);

        if(loaded == null){
            loaded = ScreenPosition.fromRelativePosition(0.5,0.5);
            this.pos = loaded;
            savePositionFromFile();
        }
        return loaded;
    }


    public final int getLineOffset(ScreenPosition pos, int lineNum){
        return pos.getAbsoluteY() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum){
        return (font.FONT_HEIGHT + 3) * lineNum;
    }
}
