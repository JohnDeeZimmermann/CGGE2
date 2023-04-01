package de.cg.cgge.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.cg.cgge.events.Event;
import de.cg.cgge.events.EventListener;
import de.cg.cgge.events.EventMapper;
import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;
import de.cg.cgge.gui.Drawer;
import de.cg.cgge.gui.Window;
import de.cg.cgge.io.KeyManager;

public class GameInstance {

    private String title = "GAME";

    private int framerate = 60;
    private int width = 1280;
    private int height = 720;
    private boolean isTaskbarActive = false;
    private boolean isVisible = true;

    private FileContents configContents;

    private EventMapper eventMapper;

    private Drawer drawer;

    /**
     * Basic constructor to setup the game
     * Creates own drawer instance and launches the window
     */
    public GameInstance() {
        this(""); 
    }

    /**
     * Constructor to setup the game, with additional config files
     * Creates own drawer instance and launches the window
     * @param config The config; It's loaded as a GameFile
     */
    public GameInstance(String config) {

        //Load config
        if (!config.equals("")) {
            try {
                GameFile gf = new GameFile(config);
                gf.loadToMemory(); 

                FileContents fc = gf.getContents();
                this.configContents = fc;

                //Assign data from file to variable
                if (fc.getFromKeyword("title") != null)
                    title = fc.getFromKeyword("title");
                if (fc.getFromKeyword("width") != null)
                    width = Integer.parseInt(fc.getFromKeyword("width"));
                if (fc.getFromKeyword("height") != null)
                    height = Integer.parseInt(fc.getFromKeyword("height"));
                if (fc.getFromKeyword("framerate") != null)
                    framerate = Integer.parseInt(fc.getFromKeyword("framerate"));
                if (fc.getFromKeyword("taskbar") != null)
                    isTaskbarActive = Boolean.parseBoolean(fc.getFromKeyword("taskbar"));
                if (fc.getFromKeyword("visible") != null)
                    isVisible = Boolean.parseBoolean(fc.getFromKeyword("visible"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Init game instance
        drawer = new Drawer(this);
        drawer.initWindow();
        drawer.getRoom().getClock().start();

        eventMapper = new EventMapper(this);

    }

    public int getHeight() {
        return height;
    }

    /**
     * Sets the target height. It does not affect the actual window size
     * @param height Height value
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    /**
     * Sets the target width. It does not affect the actual window size
     * @param width Width value
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Changes the width of getDrawer().getWindow()
     * @param width New Width
     */
    public void changeWidth(int width) {
        drawer.getWindow().setSize(width, height);
        this.width = width;
    }

    /**
     * Changes the height of getDrawer().getWindow()
     * @param height New Height
     */
    public void changeHeight(int height) {
        drawer.getWindow().setSize(width, height);
        this.height = height;
    }

    /**
     * 
     * @return The target framerate; It does not return the actual framerate
     */
    public int getTargetFramerate() {
        return framerate;
    }

    /**
     * Link to getDrawer().getCurrentFramerate();
     * @return Current framerate
     */
    public float getCurrentFramerate() {
        return drawer.getCurrentFramerate();
    }
    
    public String getTitle() {
        return title;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    /**
     * Link to getDrawer().getWindow()
     * @return Window instance
     */
    public Window getWindow() {
        return drawer.getWindow();
    }

    /**
     * Shortcut to gameInstance.getDrawer().getRoom();
     * @return Room instance
     */
    public Room getRoom() {
        return drawer.getRoom();
    }

    /**
     * Changes the room in a save manner;
     * It pauses the previous room and calls a drawer.setRoom(room) method
     * Shortcut to drawer.changeRoomSafely(Room);
     * @param room The room to be changed
     */
    public void changeRoomSafely(Room room) {
        drawer.changeRoomSafely(room);
    }

    /**
     * Whether task bar is active or not
     * @return Taskbar
     */
    public boolean isTaskbarActive() {
        return isTaskbarActive;
    }


    /**
     * Whether the game is visible or not
     * @return Whether the window is visible or not
     */
    public boolean isVisible() {
        return isVisible;
    }

    public FileContents getConfigContents() {
        return configContents;
    }

    public KeyManager getKeyManager() {
        return drawer.getWindow().getKeyManger();
    }

    public EventMapper getEventMapper() {
        return eventMapper;
    }
}