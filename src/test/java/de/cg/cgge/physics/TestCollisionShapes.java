package de.cg.cgge.physics;

import de.cg.cgge.physics.shapes.CollisionBoxShape;
import de.cg.cgge.physics.shapes.CollisionCircleShape;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCollisionShapes {

    CollisionBoxShape b1;
    CollisionBoxShape b2;
    CollisionBoxShape b3;

    CollisionCircleShape c1;
    CollisionCircleShape c2;
    CollisionCircleShape c3;

    @Before
    public void init()
    {
        b1 = new CollisionBoxShape(20, 30, 100, 100);
        b2 = new CollisionBoxShape(30, 120, 10, 10);
        b3 = new CollisionBoxShape(500, 500, 10, 10);

        c1 = new CollisionCircleShape(20, 20, 30);
        c2 = new CollisionCircleShape(30, 30, 20);
        c3 = new CollisionCircleShape(480, 500, 30);
    }

    @Test
    public void testCollidingBoxesDetected()
    {
        Assert.assertTrue(b1.isIntersecting(b2));
        Assert.assertTrue(b2.isIntersecting(b1));
    }

    @Test
    public void testNotCollidingBoxesNotDetected()
    {
        Assert.assertFalse(b1.isIntersecting(b3));
        Assert.assertFalse(b2.isIntersecting(b3));
        Assert.assertFalse(b3.isIntersecting(b1));
        Assert.assertFalse(b3.isIntersecting(b2));
    }

    @Test
    public void testCollidingCirclesDetected()
    {
        Assert.assertTrue(c1.isIntersecting(c2));
        Assert.assertTrue(c2.isIntersecting(c1));
    }

    @Test
    public void testNotCollidingCirclesNotDetected()
    {
        Assert.assertFalse(c1.isIntersecting(c3));
    }

    @Test
    public void testCollidingCircleAndBoxDetected()
    {
        Assert.assertTrue(c3.isIntersecting(b3));
        Assert.assertTrue(b3.isIntersecting(c3));
    }

    @Test
    public void testNotCollidingCircleAndBoxNotDetected()
    {
        Assert.assertFalse(c1.isIntersecting(b3));
        Assert.assertFalse(b3.isIntersecting(c1));
    }

}
