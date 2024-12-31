package de.cg.cgge.physics.shapes;

public class CollisionCircleShape extends CollisionShape{

    float radius;
    public CollisionCircleShape(float x, float y, float radius) {
        super(x, y);

        this.radius = radius;
    }

    @Override
    protected boolean isIntersecting(CollisionBoxShape other) {
        CollisionShape box = new CollisionBoxShape(x-radius, y-radius, (int) (radius*2), (int) (radius*2));
        if (!box.isIntersecting(other)) return false;

        float[] cx = new float[4], cy = new float[4];
        cx[0] = other.x;                      cy[0] = other.y;
        cx[1] = other.x+other.getWidth();     cy[1] = other.y;
        cx[2] = other.x;                      cy[2] = other.getHeight()+other.y;
        cx[3] = other.x+other.getWidth();     cy[3] = other.getHeight()+other.y;

        for (int i = 0; i<4; i++)
        {
            if ((float) Math.sqrt((cx[i]-x)*(cx[i]-x) + (cy[i]-y)*(cy[i]-y)) < radius)
                return true;
        }

        return false;
    }

    @Override
    protected boolean isIntersecting(CollisionCircleShape other) {
        float distance = (float) Math.sqrt((other.x-x)*(other.x-x) + (other.y-y)*(other.y-y)); //sqrt[(x1 - x2)^2 + (y2-y1)^2]
        return (distance < other.getRadius()+radius);
    }

    @Override
    protected CollisionShape copy() {
        return new CollisionCircleShape(x,y,radius);
    }

    @Override
    public float getWidth() {
        return getRadius() * 2;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    public float getRadius() {
        return radius;
    }
}
