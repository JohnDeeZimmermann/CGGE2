package de.cg.cgge.physics.shapes;

public class CollisionBoxShape extends CollisionShape {

    private float width, height;

    public CollisionBoxShape(float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isIntersecting(CollisionBoxShape other) {
        return (x+width >= other.getX()
                && y+height >= other.getY()
                && x <= other.getX()+ other.getWidth()
                && y <= other.getY()+ other.getHeight());
    }

    @Override
    protected boolean isIntersecting(CollisionCircleShape other) {
        CollisionShape box = new CollisionBoxShape
                (other.x- other.getRadius(), other.y-other.getRadius(), (int) (other.getRadius()*2), (int) (other.getRadius()*2));
        if (!box.isIntersecting(this)) return false;

        float[] cx = new float[4], cy = new float[4];
        float d1, d2, d3, d4; //TODO Inspect this line? What was it meant for?
        cx[0] = x;               cy[0] = y;
        cx[1] = x+width;         cy[1] = y;
        cx[2] = x;               cy[2] = height+y;
        cx[3] = x+width;         cy[3] = height+y;

        for (int i = 0; i<4; i++) {
            if ((float) Math.sqrt((cx[i]-other.x)*(cx[i]-other.x) + (cy[i]-other.y)*(cy[i]-other.y)) < other.radius) return true;
        }

        return false;
    }

    @Override
    protected CollisionShape copy() {
        return new CollisionBoxShape(x,y,width,height);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
