package de.cg.cgge.physics.shapes;

public abstract class CollisionShape {

    protected float x,y;

    public CollisionShape(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * This method checks whether this is intersecting with the given CollisionShape
     * @param other The collision shape that is to be checked for
     * @return Whether or not the intersection occurs.
     */
    public boolean isIntersecting(CollisionShape other)
    {
        if (other instanceof CollisionBoxShape) return isIntersecting((CollisionBoxShape) other);
        if (other instanceof CollisionCircleShape) return isIntersecting((CollisionCircleShape) other);
        return false;
    }

    public CollisionShape getMovedInstance(float x, float y)
    {
        CollisionShape copy = copy();
        copy.x += x;
        copy.y += y;
        return copy;
    }

    protected abstract boolean isIntersecting(CollisionBoxShape other);
    protected abstract boolean isIntersecting(CollisionCircleShape other);
    protected abstract CollisionShape copy();

    public abstract float getWidth();

    public abstract float getHeight();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
