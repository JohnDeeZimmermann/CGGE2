package de.cg.cgge.events.base;

import de.cg.cgge.events.EventMapper;
import de.cg.cgge.events.WindowResizeEvent;
import de.cg.cgge.events.WindowResizeEventListener;
import de.cg.cgge.game.GameInstance;

public class DefaultWindowResizeEventListener implements WindowResizeEventListener {
    @Override
    public void onWindowResized(WindowResizeEvent e, EventMapper mapper) {
        GameInstance game = mapper.getGame();
        game.changeWidth(e.getNewWidth());
        game.changeHeight(e.getNewHeight());
    }
}
