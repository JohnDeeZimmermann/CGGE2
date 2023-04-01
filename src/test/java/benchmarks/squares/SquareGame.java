package benchmarks.squares;

import de.cg.cgge.game.PhysicalGameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.physics.shapes.CollisionBoxShape;
import de.cg.cgge.physics.shapes.CollisionShape;

import java.awt.*;

public class SquareGame {

    private class Square extends PhysicalGameObject {
        public Square(Room room, CollisionShape shape) {
            super(room, shape);

            this.w = 50;
            this.h = 50;
        }


        @Override
        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect((int) x, (int) y,  w, h);
        }
    }


    public void measure() {
        Room room = new Room(null);
        Square square = new Square(room, new CollisionBoxShape(0, 0, 50, 50));


    }


}
