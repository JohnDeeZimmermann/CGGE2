package de.cg.cgge.events;

public interface RoomChangeEventListener extends EventListener {

    void onRoomChanged(RoomChangeEvent e, EventMapper mapper);

}
