package benchmarks.squares;

import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.PhysicalGameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.physics.Mover;
import de.cg.cgge.physics.shapes.CollisionBoxShape;
import de.cg.cgge.physics.shapes.CollisionShape;

import java.awt.*;

public class SquareGame {

    private class Square extends PhysicalGameObject {

        private Mover mover;

        public Square(Room room, CollisionShape shape) {
            super(room, shape);

            this.x = shape.getX();
            this.y = shape.getY();
            this.w = (int) shape.getWidth();
            this.h = (int) shape.getHeight();

            mover = new Mover(this);


            addPhysicsComponent(mover);
        }


        @Override
        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect((int) x, (int) y,  w, h);
        }

        @Override
        public void step() {
            mover.setXspeed(1);
            mover.setYspeed(1);

            mover.setYacceleration(1.01f);
        }
    }


    public void measure() {
        GameInstance game = new GameInstance();

        Room room = new Room(game);
        new Square(room, new CollisionBoxShape(0, 0, 50, 50));

        game.changeRoomSafely(room);
    }

    public static void main(String args[]) {
        SquareGame game = new SquareGame();
        game.measure();
    }


}
