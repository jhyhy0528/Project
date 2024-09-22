import processing.core.PApplet;
import java.io.File;


public class Hoverball extends Toy {

    public Hoverball(int x, int y) {
        super(ToySaga.HOVERBALL_OFF, x, y);
    }

    private void switchOnOff() {
        if (toySaga.getMode().equals(ToySaga.NIGHT_MODE)) {
            this.setImage(toySaga.HOVERBALL_ON);
        } else {
            this.setImage(toySaga.HOVERBALL_OFF);
        }
    }
    
    @Override
    public void draw() {
        switchOnOff(); 
        super.draw();
    }

    @Override
    public void move() {
        if (toySaga.getMode().equals(ToySaga.NIGHT_MODE)) {
            int dY = Math.round(6 * PApplet.sin(toySaga.frameCount * 0.1f));
            y += dY;
        } else {
            super.move();
        }
    }
    

}
