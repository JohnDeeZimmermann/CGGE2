package de.cg.cgge.events;

import de.cg.cgge.game.Room;

public class RoomChangeEvent extends Event{

    private Room from, to;

    public RoomChangeEvent(Room from, Room to)
    {
        this.from = from;
        this.to = to;
    }

    public Room getFrom() {
        return from;
    }

    public Room getTo() {
        return to;
    }
}
