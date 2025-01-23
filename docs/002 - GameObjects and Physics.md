# CGGE2 Game Engine - Creating the game

### 2.0 Your first GameObject

Now that you have your game set up, we can proceed with creating your first _GameObject_ instance.

```JAVA
import de.cg.cgge.game.GameObject;

public class TestObject extends GameObject {

    //Now you should implement the constructor
    
}
```

Now you can override the draw method and draw your first game object to the screen.

```JAVA
@Override
public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(50,50,50,50);
}
```

It will now display a rectangle at the specified position. The draw method is called sixty times a second. Before all the draw methods are called the screen gets cleared entirely. If you want to draw after every other object’s draw method has been called, then you should use the _postDraw(Graphics g)_ method. Doing so is recommended when dealing with GUI.

Another trait of game object is that they have float x, y and int w, h already pre-defined.
It’s recommended to use these to draw your object as the position should update when the position of the object updates as well as these values are tied to the physics engine.
So let’s update the code:

```JAVA
public TestObject(Room room, GameObject parent) {
    super(room, parent);

    this.x = 50;
    this.y = 50;
    this.w = 50;
    this.h = 50;
}

@Override
public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    // Get absolute values as to make this compatible with 
    int x = getAbsoluteX();
    int y = getAbsoluteY();
    g.fillRect((int) x, (int) y, w, h);
}
```

To create your object you simply go back to your main method and write
```
TestObject testObject = new TestObject(game.getRoom());
```

The getRoom() method returns the currently active room, in which the object should be placed in.

The following table lists all the methods that can be overridden:

| **Method**                              	 | **Purpose**                                                                                                	   |
|-------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| `protected void create()`               	 | Is called, when the object is added to the main loop                                                         	 |
| `public void step()`                    	 | Is called every tick. (That is every frame)                                                                  	 |
| `public void preStep()`                 	 | Is called every tick, but before any step() methods are called                                               	 |
| `public void draw(Graphics)`            	 | Is called every tick. Runs on the draw thread and its purpose is to make your objects visible on the screen. 	 |
| `public void postDraw(Graphics)`        	 | Is like draw(Graphics), but is called AFTER all the draw() methods. Is recommended for GUI.                  	 |
| `public void mouseClicked(MouseEvent)`  	 | Is called when the mouse is clicked                                                                          	 |
| `public void mousePressed(MouseEvent)`  	 | Is called, once the mouse is pressed                                                                         	 |
| `public void mouseReleased(MouseEvent)` 	 | Is called, once the mouse is released, after it was pressed                                                  	 |
| `public void initGraphics(Resolution)` 	  | Is called on object initialization and when resizing the screen.                                               |

All the other methods should not be overridden.

### 2.1 PhysicalGameObject

If you want your object to interact with the world around it, you should a _PhysicalGameObject_.
Platforms, Players and other interactables should be made into PhysicalGameObjects.

Every PhysicalGameObject contains a _CollisionShape_ and a list of _Physics_ components, which control the way the object interacts with the world.
```JAVA
PhysicalGameObject obj = new PhysicalGameObject(room); 
//or
CollisionShape shape = new CollisionBoxShape(0, 0, 50, 50); //x position, y position, width and height
PhysicalGameObject obj = new PhysicalGameObject(room, shape);
```

Alternatively, you could also create a collision shape in the constructor of a PhysicalGameObject, just as you would with normal GameObjects.

```JAVA
public class Spaceship extends PhysicalGameObject {
    public Spaceship(Room room) {
        super(room); 
        
        this.collisionShape = new CollisionShape(0, 0, 50, 50); 
    }
}
```

A collision shape dictates the dimensions of the objects. It is the area, other objects will intersect at with the object. The x and y positions of the collision shapes will be updated automatically at the end of each step to the respective x and y positions of the PhysicalGameObject. 

### 2.2 Key Input

The engine handles keyboard input by using the _Input_ class. 
Defining an input is as easy as: 
```JAVA
Input spacebar = new Input(Input.Type.KEY_JUST_PRESSED, KeyEvent.VK_SPACE);
game.getKeyManager().addInput("SPACE_PRESSED", spacebar);
```

The first argument for _Input_ is a _Input.Type_, which can either be `KEY_PRESSED`, `KEY_RELEASED`, which make the input fire every time, _one_ of the keys given is pressed or released, `KEY_JUST_PRESSED`, which makes the input fire exactly once, one of the keys given is pressed, and `ALL_KEYS_PRESSED`, which makes the input fire, when _all_ of the given keys are being pressed. 

The second argument is an array of keys (by their integer values, which can be obtained by using the `KeyEvent` class.), which the Input will listen to as defined by the _Input.Type_ provided. 

You should now add the input to the _KeyManager_ to make it listen to the key inputs of the player. You give the input a string name, which is used to reference the input later on.

To check for an input you use:
```JAVA
if (keyManager.checkInput("SPACE_PRESSED") {
    //Do something
}
```
given that an input with the name `SPACE_PRESSED` was added.

### 2.3 Making your object move

In order to make your object move around, you have to obtain the key manager, which is obtainable through _room_ instance of the object. You then have to check against all the inputs, defined in the beginning of your game.

Main class:
```JAVA
Input keyUp = new Input(Input.Type.KEY_PRESSED, KeyEvent.VK_W);
Input keyDown = new Input(Input.Type.KEY_PRESSED, KeyEvent.VK_S);
Input keyRight = new Input(Input.Type.KEY_PRESSED, KeyEvent.VK_D);
Input keyLeft = new Input(Input.Type.KEY_PRESSED, KeyEvent.VK_A);

KeyManager km = game.getKeyManager(); 
km.addInput("MOVE_UP", keyUp);
km.addInput("MOVE_DOWN", keyDown); 
km.addInput("MOVE_LEFT", keyLeft); 
km.addInput("MOVE_RIGHT", keyRight); 
```

Game Object:

```JAVA
public class TestObject extends GameObject {

    private KeyManager km;
    
    public TestObject(Room room) {
        super(room);
        
        this.km = room.getKeyManager();
    }
    
    
    
    @Override
    public void step() {
        if (km.checkInput("MOVE_UP")) {
            this.y -= 5;
        }

        if (km.checkInput("MOVE_DOWN")) {
            this.y += 5;
        }

        if (km.checkInput("MOVE_LEFT")) {
            this.x -= 5;
        }

        if (km.checkInput("MOVE_RIGHT")) {
            this.x += 5;
        }
    }
    
}
```

### 2.4 Movement with Physics

Ideally, moving objects should be part of the physics engine in order to make collisions work and to make life a little bit simpler.

Instead of adjusting the x and y positions manually you should rather use the integrated physics engine. Currently there are two physics classes which are Gravity and Mover. We will focus on the latter one for now.


The Mover, as the name might already imply, moves your object around for you. It does that while also taking collisions with other PhysicalGameObjects into account that have the boolean property `solid` set to true. In order to gather collision information, it uses a _Collider_ instance which then can check for collisions with other objects taking the given collision shapes into account.

```JAVA
import de.cg.cgge.game.PhysicalGameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Mover;
import de.cg.cgge.physics.shapes.CollisionBoxShape;
import de.cg.cgge.physics.shapes.CollisionShape;

public class TestObject extends PhysicalGameObject {
    private Mover mover;
    private KeyManager km; 

    public PhysicalGameObject(Room room) {
        super(room);

        CollisionShape collisionShape = new CollisionBoxShape(0, 0, 50, 50);
        setCollisionShape(collisionShape);
        
        km = room.getKeyManager();

        Mover mover = new Mover(this);

        addPhysicsComponent(mover);
    }
}

```
You can now set the x and y speeds of the Mover to move the object. You can also optionally set accelerations for both directions to implement force (> 1) or simulate ice (< 1). To let the movement halt after the button is released, you should leave the accelerations at 0.
```JAVA 
@Override
public void step() {
    if (km.checkInput("MOVE_UP")) {
        mover.setYspeed(-5); //Going up the screen
    }
    if (km.checkInput("MOVE_DOWN")) {
        mover.setYspeed(5); //Going down the screen
    }
    if (km.checkInput("MOVE_LEFT")) {
        mover.setXspeed(-5); //Going to the left
    }
    if (km.checkInput("MOVE_RIGHT")) {
        mover.setXspeed(5); //Going to the right
    }
}
```
### 2.5 Making movement framerate independent

So right now your physics update at a given framerate. But what if the Game is not able to sustain the target framerate? Well then your game is going to be updated less frequently, so your game is going to get slower. If your target is let’s say 60FPS but the user only reaches 30FPS, he will play at half the intended speed.

To fix that, the engine offers a _delta time_ value. Delta time is the time, the last frame took to render. So when your game is running 30 FPS, instead of 60 FPS, the delta time is going to be twice as large. Multiplying the delta value with your _speed_ values, will therefore double the speed of your objects, when the game is only running at half the intended speed, which means that the speed of your objects in constant, no matter the framerate. Your game is now _framerate independent_.
```JAVA
float delta = Physics.deltaTime();
float speed = 500f*delta;
```
Note that delta tends to be quite small, which is why your speed values must be larger to equalize that effect.


### 2.6 Gravity
`Gravity` is a physics component which can easily be added to any `PhysicalGameObject`. 
It automatically pushes the object down. Just provide it with a force and a `Mover` instance.  

### 2.7 Child Objects
Child objects are attached to their parent objects and get called alongside the parent object. You can imagine the structure as a tree. 
In order to add child objects, use the GameObject's `addChild(GameObject)` method. 

It is important that the child object has the parent defined as its parent first. If the class of the child object does not expose a way to add a parent, it means that it doesn't support being added as a child.
To support being added as child objects, a class should call `GameObject(Room, GameObject)` as `super` within its own constructor. 
Additionally, it should then also always use `getAbsoluteX()` and `getAbsoluteY()` when drawing the object. 

