package de.cg.cgge.game;

import java.util.ArrayList;

public class ObjectManager {

    private long totalObjects = 0L;

    private ArrayList<GameObject> objects = new ArrayList<>();
    private ArrayList<GameObject> toAdd = new ArrayList<>(); 
    private ArrayList<GameObject> toRemove = new ArrayList<>(); 

    public void addObject(GameObject obj) {
        toAdd.add(obj);
    }

    public void removeObject(GameObject obj) {
        toRemove.add(obj);
    }

    public void updateObjects() {
        if (!toAdd.isEmpty()) {
            for (GameObject obj : toAdd) {
                obj.setID(totalObjects);
                obj.create();
                obj.initGraphics(obj.room.getGameInstance().getResolution());
                obj.room.getGameInstance().getEventMapper().onObjectAdded(obj);
                objects.add(obj);
                totalObjects++;
            }

            toAdd.clear();
            
        }

        if (!toRemove.isEmpty()) {
            for (GameObject obj : toRemove) {
                obj.room.getGameInstance().getEventMapper().onObjectKilled(obj);
                objects.remove(obj);
            }

            toRemove.clear();
        }
    }

    /**
     * @return Returns all objects of the object manager
     */
    public ArrayList<GameObject> getObjects() {
        return this.objects;
    }


}