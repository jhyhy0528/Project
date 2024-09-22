import processing.core.PApplet;

public class TeddyBear extends Toy {

    private float rotation;
    private boolean rotationDirection;
    private Callout callout;

    public TeddyBear(int x, int y) {
        super(ToySaga.BEAR, x, y);
        this.rotation = 0.0f;
        this.rotationDirection = true;
        // Initialize the callout object here
        this.callout = new Callout(x, y - this.image.height / 2 - 20);
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setRotationDirection(boolean direction) {
        this.rotationDirection = direction;
    }
    
    public boolean getRotationDirection() {
        return this.rotationDirection;
    }

    @Override
    public void draw() {
    if (toySaga.getMode().equals(ToySaga.NIGHT_MODE)) {
        drawTeddyBearNightMode();
    } 
    
    else {
        super.draw();
    }
}

/**
 * Provided method to draw this talking TeddyBear at night with respect to its moves
 */
private void drawTeddyBearNightMode() {
    move();
    toySaga.pushMatrix(); // Save the current transformation matrix
    toySaga.translate(x, y); // Translate to the teddy bear’s position
    toySaga.rotate(rotation * PApplet.PI / 2); // Apply rotation
    if (toySaga.getMode() == "NIGHT") {
      toySaga.image(callout.image, 20f, -90f);
    }
    toySaga.image(image, 0.0f, 0.0f); // Draw the image at the rotated position
    toySaga.popMatrix(); // Restore the previous transformation matrix
  }

  @Override
  public void move() {
      if (toySaga.getMode().equals(toySaga.DAY_MODE)) {
          super.move();
      } else if (toySaga.getMode().equals(toySaga.NIGHT_MODE)) {
          rotation += PApplet.radians(3);
          if (rotation >= PApplet.radians(30)) {
              rotationDirection = false;
          } else if (rotation <= PApplet.radians(-30)) {
              rotationDirection = true;
          }
          if (!rotationDirection) {
              rotation -= PApplet.radians(6);
          }
      }
  }
  
}

