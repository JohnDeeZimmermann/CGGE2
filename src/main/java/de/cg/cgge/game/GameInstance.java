package de.cg.cgge.game;

import java.io.IOException;

import de.cg.cgge.events.EventMapper;
import de.cg.cgge.events.base.DefaultWindowResizeEventListener;
import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;
import de.cg.cgge.gui.Drawer;
import de.cg.cgge.gui.GraphicsSettings;
import de.cg.cgge.gui.Resolution;
import de.cg.cgge.gui.Window;
import de.cg.cgge.io.KeyManager;

public class GameInstance {

    private String title = "GAME";

    private int framerate = 60;
    private Resolution resolution = new Resolution(1280, 720);
    private boolean isTaskbarActive = false;
    private boolean isVisible = true;
    private GraphicsSettings graphicsSettings;

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
     * Constructor to set up the game, with additional config files
     * Creates own drawer instance and launches the window
     * @param configPath The config; It's loaded as a GameFile
     */
    public GameInstance(String configPath) {

        //Load config
        if (!configPath.isEmpty()) {
            try {
                GameFile gf = new GameFile(configPath);
                gf.loadToMemory(); 

                FileContents fc = gf.getContents();
                this.configContents = fc;

                //Assign data from file to variable
                if (fc.getFromKeyword("title") != null)
                    title = fc.getFromKeyword("title");
                if (fc.getFromKeyword("width") != null)
                    setWidth(Integer.parseInt(fc.getFromKeyword("width")));
                if (fc.getFromKeyword("height") != null)
                    setHeight(Integer.parseInt(fc.getFromKeyword("height")));
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

        this.eventMapper = new EventMapper(this);

        //Default events
        this.eventMapper.addEventListener(new DefaultWindowResizeEventListener());

        graphicsSettings = new GraphicsSettings(this);
    }


    /**
     * Use getResolution() instead.
     * @return The height of the game window
     */
    @Deprecated
    public int getHeight() {
        return resolution.getHeight();
    }

    /**
     * Sets the target height. It does not affect the actual window size
     * @param height Height value
     */
    public void setHeight(int height) {
        this.resolution = new Resolution(resolution.getWidth(), height);
    }

    /**
     * Use getResolution() instead.
     * @return The width of the game window
     */
    @Deprecated
    public int getWidth() {
        return resolution.getWidth();
    }
    /**
     * Sets the target width. It does not affect the actual window size
     * @param width Width value
     */
    public void setWidth(int width) {
        this.resolution = new Resolution(width, resolution.getHeight());
    }

    /**
     * Changes the width of getDrawer().getWindow()
     * @param width New Width
     */
    public void changeWidth(int width) {
        drawer.getWindow().setSize(width, resolution.getHeight());
        drawer.getWindow().getDrawPanel().setSize(width, resolution.getHeight());
        setWidth(width);
    }

    /**
     * Changes the height of getDrawer().getWindow()
     * @param height New Height
     */
    public void changeHeight(int height) {
        drawer.getWindow().setSize(resolution.getWidth(), height);
        drawer.getWindow().getDrawPanel().setSize(resolution.getWidth(), height);
        setHeight(height);
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

    public GraphicsSettings getGraphicsSettings() {
        return graphicsSettings;
    }

    public Resolution getResolution() {
        return resolution;
    }
}