package de.cg.cgge.events;

import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.GameObject;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {

    private List<EventListener> eventListeners;
    private GameInstance game;

    public EventMapper(GameInstance game) {
        this.game = game;
        this.eventListeners = new ArrayList<>();
    }

    public void addEventListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public void removeEventListener(EventListener listener) {
        eventListeners.remove(listener);
    }

    public void onWindowResize(WindowResizeEvent e) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof WindowResizeEventListener) {
                ((WindowResizeEventListener) listener).onWindowResized(e, this);
            }
        }
    }

    public void onObjectAdded(GameObject obj) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof ObjectAddedEventListener) {
                ((ObjectAddedEventListener) listener).onObjectAdded(obj, this);
            }
        }
    }

    public void onObjectKilled(GameObject obj) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof ObjectKilledEventListener) {
                ((ObjectKilledEventListener) listener).onObjectKilled(obj, this);
            }
        }
    }

    public void onMouseClicked(MouseEvent e) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof MouseEventListener) {
                ((MouseEventListener) listener).onMouseClicked(e, this);
            }
        }
    }

    public void onMousePressed(MouseEvent e) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof MouseEventListener) {
                ((MouseEventListener) listener).onMousePressed(e, this);
            }
        }
    }

    public void onMouseReleased(MouseEvent e) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof MouseEventListener) {
                ((MouseEventListener) listener).onMouseReleased(e, this);
            }
        }
    }

    public void onMouseEnter(MouseEvent e) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof MouseEventListener) {
                ((MouseEventListener) listener).onMouseEnter(e, this);
            }
        }
    }

    public void onMouseExit(MouseEvent e) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof MouseEventListener) {
                ((MouseEventListener) listener).onMouseExit(e, this);
            }
        }
    }

    public void onRoomChange(RoomChangeEvent e) {
        for (EventListener listener : eventListeners) {
            if (listener instanceof RoomChangeEventListener) {
                ((RoomChangeEventListener) listener).onRoomChanged(e, this);
            }
        }
    }

    public GameInstance getGame() {
        return game;
    }
}
