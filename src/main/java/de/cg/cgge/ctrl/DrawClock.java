package de.cg.cgge.ctrl;

import java.util.ConcurrentModificationException;

import de.cg.cgge.game.GameInstance;

public class DrawClock extends Clock {

    public DrawClock(GameInstance game) {
        super(game);
    }

    /*
     * Thou not optimal, it works for now. This MUST be revised later
     */

    @Override
    protected void clockAction() {
        if (this.game.getDrawer().getWindow() != null) {
            try {
                this.game.getDrawer().getWindow().getDrawPanel().paintImmediately(0,0,game.getWidth(), game.getHeight());
            } catch (ConcurrentModificationException ignored) {}
        }
    }
    


}