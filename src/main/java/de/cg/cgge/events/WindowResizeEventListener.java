package de.cg.cgge.events;

import jdk.jfr.Event;

public interface WindowResizeEventListener extends EventListener {

    void onWindowResized(WindowResizeEvent e, EventMapper mapper);

}
