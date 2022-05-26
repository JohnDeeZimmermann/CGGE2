package de.cg.cgge.events;

import de.cg.cgge.game.GameObject;

import java.awt.event.MouseEvent;

public class EventListener {

    /*
        TODO:
        - Add more events
        - Add EventListeners to the GameInstance
        - Make the core call each of the events
     */

    public EventListener(){}

    public void onWindowResized(WindowResizeEvent e) {}

    public void onObjectAdded(GameObject obj) {}

    public void onObjectKilled(GameObject obj) {}

    public void onRoomChanged(RoomChangeEvent e) {}

    public void onMousePressed(MouseEvent e) {}

    public void onMouseClicked(MouseEvent e) {}

    public void onMouseReleased(MouseEvent e) {}

    public void onMouseExit(MouseEvent e) {}

    public void onMouseEnter(MouseEvent e) {}


}
