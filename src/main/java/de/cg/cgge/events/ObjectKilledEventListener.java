package de.cg.cgge.events;

import de.cg.cgge.game.GameObject;

public interface ObjectKilledEventListener extends EventListener{

    void onObjectKilled(GameObject obj, EventMapper mapper);



}
