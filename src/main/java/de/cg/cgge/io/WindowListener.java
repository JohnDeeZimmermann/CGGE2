package de.cg.cgge.io;

import java.awt.event.*;

import de.cg.cgge.events.WindowResizeEvent;
import de.cg.cgge.game.GameInstance;
import de.cg.cgge.gui.Window;

public class WindowListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
        Window win = (Window) e.getComponent();

        int w = win.getWidth();
        int h = win.getHeight();

        GameInstance game = win.getDrawer().getGameInstance();
        
        win.getDrawPanel().setSize(w, h);

        game.setWidth(w);
        game.setHeight(h);

        game.getEventListeners()
                .forEach(el -> el.onWindowResized(new WindowResizeEvent(win.getX(), win.getY(), w, h)));

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        

    }

    @Override
    public void componentShown(ComponentEvent e) {
        

    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }

}