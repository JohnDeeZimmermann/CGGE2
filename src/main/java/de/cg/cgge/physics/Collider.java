package de.cg.cgge.physics;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.PhysicalGameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.physics.shapes.CollisionShape;

public class Collider {

    protected int lastCollision = 0;
    protected Room room;

    protected PhysicalGameObject tester;

    /**
     * Creates a collider, which is necessary for Collision detection
     * It is NOT of instance de.cg.cgge.physics.Physics
     * @param room The current game room
     * @param tester The object, that tests for collisions
     */
    public Collider(Room room, PhysicalGameObject tester) {
        this.room = room;
        this.tester = tester; 
    } 

    /**
        Checks for collision with objects that are solid
        @param shape The shape that is used as collision mask (Usually the GameObject's CollisionShape)
        @return Whether there is a collision or not
    */
    public boolean checkSolidCollision(CollisionShape shape) {

        for (int i = 0; i<room.getObjectManager().getObjects().size(); i++) {
            GameObject obj = room.getObjectManager().getObjects().get(i);
            if (!(obj instanceof PhysicalGameObject)) continue;
            PhysicalGameObject pobj = (PhysicalGameObject) obj;

            if (obj.isSolid() && obj != tester && shape.isIntersecting(pobj.getCollisionShape()))
            {
                lastCollision = i;
                return true;
            }

        }

        return false;
    }


    /**
     Checks for collision with any physical objects
     @param shape The shape that is used as collision mask (Usually the GameObject's CollisionShape)
     @return Whether there is a collision or not
     */
    public boolean checkCollision(CollisionShape shape) {
        for (int i = 0; i<room.getObjectManager().getObjects().size(); i++) {
            GameObject obj = room.getObjectManager().getObjects().get(i);
            if (!(obj instanceof PhysicalGameObject)) continue;
            PhysicalGameObject pobj = (PhysicalGameObject) obj;

            if (obj != tester && shape.isIntersecting(pobj.getCollisionShape()))
            {
                lastCollision = i;
                return true;
            }

        }


        return false;
    }

    /**
     Checks for collision with objects that are not solid
     @param shape The shape that is used as collision mask (Usually the GameObject's CollisionShape)
     @return Whether there is a collision or not
     */
    public boolean checkUnsolidCollision(CollisionShape shape) {
        for (int i = 0; i<room.getObjectManager().getObjects().size(); i++) {
            GameObject obj = room.getObjectManager().getObjects().get(i);
            if (!(obj instanceof PhysicalGameObject)) continue;
            PhysicalGameObject pobj = (PhysicalGameObject) obj;

            if (!obj.isSolid() && obj != tester && shape.isIntersecting(pobj.getCollisionShape()))
            {
                lastCollision = i;
                return true;
            }

        }


        return false;
    }

    /**
     * 
     * @return Returns the object, the last collision occoured with
     */
    public GameObject getLastCollision() {
        return room.getObjectManager().getObjects().get(lastCollision);
    }



}