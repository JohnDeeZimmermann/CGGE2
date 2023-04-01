package de.cg.cgge.events;

import de.cg.cgge.game.GameObject;

public interface ObjectAddedEventListener extends EventListener {

    void onObjectAdded(GameObject obj, EventMapper mapper);

}
