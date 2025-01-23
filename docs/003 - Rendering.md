# 3 Rendering
### 3.1 Camera
A camera is attached to the room by default. It is used to move the viewpoint, to follow a certain object (e.g. the player) and to zoom in and out of the scene. 
Here is how to modify its values: 
```Java
room.getCamera.setObjectToFollow(this);
room.getCamera.setXpadding(200);
room.getCamera.setYpaddding(100);
room.getCamera.setSpeed(12);
```
### 3.2 Camera Renderer
It is highly recommended to use the camera renderer instead of Java's regular `Graphics` class in order to ensure compatibility with a different camera. 
The `CameraRenderer` masks a lot of methods of `Graphics` but adjusts the provided values based on the current camera's position.
It can be instantiated as follows:
```JAVA
@Override
public void draw(Graphics g) {
    int x = (int) getAbsoluteX(); 
    int y = (int) getAbsoluteY(); 
    CameraRenderer cr = new CameraRenderer(g, room.getCamera());
    cr.fillRect((int) x, (int) y, w, h);
}
```

### 3.3 Sprites
Sprites are easy to add to the scene:
```JAVA
//public Sprite(String path, int width, int height, int rotation);
private Sprite sprite = new Sprite("rsc/sprite.png", 64, 64, 0);
```
Remember to put your image file into the right directory. If you want to clone an already existing
sprite you can just do that as follows:
```JAVA
//public Sprite(Sprite existingSprite);
private Sprite sprite = new Sprite(existingSprite);
```

If you now want to draw the sprite to the screen, head into your draw(Graphics g) method and do as
follows:
```JAVA
sprite.draw(x,y,graphics);
```
Or ideally, you would use the camera renderer's `drawSprite` method. 
If you set a rotation, then you should also specify a center. This can be done with the sprite’s
`setCenter(int x, int y)` method.
### 3.4 Animated Sprites
Animations may sound scary at first, but they are actually quite simple. Instead of a sprite, you just
use an AnimatedSprite. To animate your sprite you first have to create it.
```JAVA
//The first three args are just like the sprite
//The following ones are all the image paths
AnimatedSprite as = new AnimatedSprite(50, 50, 0, "rsc/img1.png", "rsc/img2.png", "rsc/img3.png");
as.load();
```
All the animated sprite does is it switches in a certain range between all the images that it contains.
Very important: Unlike the sprite, animated sprites must be loaded into memory first.
Now to start the animation: 
```
int startImage = 0;
int lastImage = 2;
int timeInterval = 100; //The time in milliseconds between each image
as.startAnimation(startImage, lastImage, timeInterval);
```
The rest is the same as with sprites. You can draw it via the same draw(int, int, Graphics) method.
### 3.5 Full Screen
In order to enter full screen mode, simply call: 
`game.getWindow().switchFullScreen();`
Doing so whilst the task bar is enabled will throw an exception. 
### 3.6 Animations
`AnimationHelper` has several helper methods to manage animations. 
You provide the methods a value between `0` and `1` and it maps it to a different value, also between `0` and `1`. 
Alternatively, you can provide start time (in ms) and duration. 
