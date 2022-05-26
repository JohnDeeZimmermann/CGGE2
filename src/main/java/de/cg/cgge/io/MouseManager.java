package de.cg.cgge.io;

import java.awt.event.*;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.gui.Drawer;

public class MouseManager implements MouseListener {

    private Drawer drawer; 

    public MouseManager(Drawer drawer) {
        this.drawer = drawer; 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.mouseClicked(e);
        } 

        drawer.getGameInstance().getEventListeners().forEach(eventListener -> eventListener.onMouseClicked(e));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.mousePressed(e);
        }

        drawer.getGameInstance().getEventListeners().forEach(eventListener -> eventListener.onMousePressed(e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.mouseReleased(e);
        }

        drawer.getGameInstance().getEventListeners().forEach(eventListener -> eventListener.onMouseReleased(e));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        drawer.getGameInstance().getEventListeners().forEach(eventListener -> eventListener.onMouseEnter(e));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        drawer.getGameInstance().getEventListeners().forEach(eventListener -> eventListener.onMouseExit(e));
    }

}