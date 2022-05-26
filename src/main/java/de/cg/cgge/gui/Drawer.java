package de.cg.cgge.gui;

import de.cg.cgge.ctrl.Clock;
import de.cg.cgge.ctrl.DrawClock;
import de.cg.cgge.events.RoomChangeEvent;
import de.cg.cgge.game.*;

public class Drawer {

    private GameInstance game;

    private Room currentRoom = null;  

    private Window win; 

    private long passedFrames = 0L;

    private long startTime = 0L; 

    private DrawClock drawClock; 

    /**
     * Creates a drawer
     * It handles all the visuals with its Window object and the rooms
     * @param game The GameInstance the drawer belongs to
     */
    public Drawer(GameInstance game) {
        this.game = game; 
        currentRoom = new Room(game);  

        drawClock = new DrawClock(game); 
        drawClock.start(); 

    }

    public void initWindow() {
        win = new Window(this); 
    }

    /**
     * Sets the room and starts it's clock
     * WARNING: This is not recommended! It is not save, as the previous room is still handled in background
     * @param room The room to be switched to
     */
    public void setRoom(Room room) {
        game.getEventListeners().forEach(el -> el.onRoomChanged(new RoomChangeEvent(currentRoom, room)));
        this.currentRoom = room;
        
        room.setClock(new Clock(room.getGameInstance()));
        room.getClock().start();
    }

    public Room getRoom() {
        return this.currentRoom;
    }

    public Window getWindow() {
        return this.win;
    }

    public GameInstance getGameInstance() {
        return this.game; 
    }

    /**
     * Changes the room in a save manner;
     * It pauses the previous room and calls a setRoom(room) method
     * @param room The room to be saved to
     */
    public void changeRoomSafely(Room room) {
        currentRoom.getClock().stopClock();
        setRoom(room);
    }

    /**
     * Increases the frame counter to manipulate framerates
     * NOT RECOMMENDED
     */
    public void increasePassedFrames() {
        passedFrames++; 
    }

    /**
     * NOT RECOMMENDED FOR USE
     * @param startTime The time in milliseconds, at which the program started
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime; 
    }

    /**
     * 
     * @return Returns the current framerate based on the starting time and the passed frames
     */
    public float getCurrentFramerate() {
        long currentTime = System.currentTimeMillis()/1000; 
        long passedTime = currentTime - startTime; 
        float avg = (float) passedFrames / (float) passedTime;
        
        if (passedTime >= 60) {
            startTime = System.currentTimeMillis()/1000;
            passedFrames = 0; 
        }

        if (avg >= game.getTargetFramerate()) {
            return (game.getTargetFramerate());
        }

        return (avg);
    }

}