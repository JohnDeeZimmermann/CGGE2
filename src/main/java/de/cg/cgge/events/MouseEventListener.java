package de.cg.cgge.events;

import java.awt.event.MouseEvent;

public interface MouseEventListener extends EventListener {

    public void onMousePressed(MouseEvent e, EventMapper mapper);

    public void onMouseClicked(MouseEvent e, EventMapper mapper);

    public void onMouseReleased(MouseEvent e, EventMapper mapper);

    public void onMouseExit(MouseEvent e, EventMapper mapper);

    public void onMouseEnter(MouseEvent e, EventMapper mapper);

}
