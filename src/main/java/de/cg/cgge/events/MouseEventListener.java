package de.cg.cgge.events;

import java.awt.event.MouseEvent;

public interface MouseEventListener extends EventListener {

    void onMousePressed(MouseEvent e, EventMapper mapper);

    void onMouseClicked(MouseEvent e, EventMapper mapper);

    void onMouseReleased(MouseEvent e, EventMapper mapper);

    void onMouseExit(MouseEvent e, EventMapper mapper);

    void onMouseEnter(MouseEvent e, EventMapper mapper);

}
