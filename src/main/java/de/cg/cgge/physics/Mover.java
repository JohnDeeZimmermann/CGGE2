package de.cg.cgge.physics;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.PhysicalGameObject;
import de.cg.cgge.physics.shapes.CollisionBoxShape;
import de.cg.cgge.physics.shapes.CollisionCircleShape;
import de.cg.cgge.physics.shapes.CollisionShape;

public class Mover extends Physics {

    private float xspeed = 0f;
    private float xacceleration = 0f;
    private float yspeed = 0f;
    private float yacceleration = 0f;


    private boolean onGround = false; 

    private Collider col; 

    /**
     * Creates a mover, which is able to move objects with taking collisions into account.
     * It uses a Collider to check for checkSolidBoxCollision
     * If it detects a collision, it'll reset the objects position to the edge of the colliding object
     * @param obj The object to be moved
     */
    public Mover(PhysicalGameObject obj) {
        super(obj);
        col = new Collider(obj.getRoom(), obj);
    }

    @Override
    public void update() {

        float x = obj.getX(); 
        float y = obj.getY(); 

        onGround = false; 

        if (xspeed != 0 && col.checkSolidCollision(obj.getCollisionShape().getMovedInstance(xspeed,0))) {
            PhysicalGameObject lastCollision = (PhysicalGameObject) col.getLastCollision();
            CollisionShape shape = lastCollision.getCollisionShape();

            if (shape instanceof CollisionBoxShape)
            {
                CollisionBoxShape box = (CollisionBoxShape) shape;
                if (xspeed > 0) {
                    x = box.getX() - obj.getWidth() - 1;
                } else {
                    x = box.getX() + box.getWidth() + 1;
                }
            }

            else if (shape instanceof CollisionCircleShape) {
                x -= xspeed;
            }

            xspeed = 0; 
        } else {
            x += xspeed; 
        }

        if (yspeed != 0 && col.checkSolidCollision(obj.getCollisionShape().getMovedInstance(0, yspeed))) {
            PhysicalGameObject lastCollision = (PhysicalGameObject) col.getLastCollision();

            CollisionShape shape = lastCollision.getCollisionShape();

            if (shape instanceof CollisionBoxShape)
            {
                CollisionBoxShape box = (CollisionBoxShape) shape;
                if (yspeed > 0) {
                    y = box.getY() - obj.getHeight() - 1f;
                } else {
                    y = box.getY() + box.getHeight() + 1f;
                }
            }

            else if (shape instanceof CollisionCircleShape) {
                y -= yspeed;
            }

            yspeed = 0; 
        } else {
            y += yspeed; 
        }

        obj.setX(x); 
        obj.setY(y); 

        xspeed *= xacceleration; 
        yspeed *= yacceleration; 

    }

    public float getYacceleration() {
        return yacceleration;
    }

    public void setYacceleration(float yacceleration) {
        this.yacceleration = yacceleration;
    }

    public float getYspeed() {
        return yspeed;
    }

    public void setYspeed(float yspeed) {
        this.yspeed = yspeed;
    }

    public float getXacceleration() {
        return xacceleration;
    }

    public void setXacceleration(float xacceleration) {
        this.xacceleration = xacceleration;
    }

    public float getXspeed() {
        return xspeed;
    }

    public void setXspeed(float xspeed) {
        this.xspeed = xspeed;
    }

    public boolean isOnGround() {
        return onGround; 
    }

    /**
     * Moves the GameObject to a given position
     * @param x The target's x position
     * @param y The target's y position
     * @param speed The speed at which the object goes to the position. It's split between the distances in x and y directions
     */
    public void goToLocation(int x, int y, float speed) {
        float cx = obj.getX()+(float)obj.getWidth()/2;
        float cy = obj.getY()+(float)obj.getHeight()/2;

        if (!(cx == x || cy == y)) {
            float dx = x-cx; 
            float dy = y-cy;
            float total = Math.abs(dx)+Math.abs(dy);

            xspeed = (dx/total)*speed;
            yspeed = (dy/total)*speed;
        }

        
    }


    

}