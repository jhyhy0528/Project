
public class Car extends Toy {

    private static int absoluteSpeed = 8;
    private int speed;
    private boolean isMovingRightward;

    public Car(int x, int y) {
        super(ToySaga.CAR, x, y); 
        this.isMovingRightward = true;
        this.speed = Car.absoluteSpeed;
    }

    @Override
    public void draw() {
    if (toySaga.getMode().equals(toySaga.NIGHT_MODE)) {
        move();
        drawCarNightMode();
    } else {
        super.draw();
    }
}
/**
 * Provided method to draw a car at night with respect to its move direction
 */
private void drawCarNightMode() {
    toySaga.pushMatrix();
    toySaga.rotate(0.0f);
    toySaga.translate(x, y);
    if (!isMovingRightward) {
      toySaga.scale(-1.0f, 1.0f);
    }
    toySaga.image(image, 0.0f, 0.0f);
    toySaga.popMatrix();
  }

  public void flipMoveDirection() {
    isMovingRightward = !isMovingRightward;
    speed = -speed;
}

@Override
public void move() {

    if (toySaga.getMode().equals(toySaga.DAY_MODE)) {
        super.move();
    } 
    
    else {
        x += speed;
        if (x <= 0 || x >= toySaga.width) {
            flipMoveDirection();
        }
    }
}

public static int getSpeed() {
    return absoluteSpeed;
}

public static void setSpeed(int speed) {
    absoluteSpeed = speed;
}



}
