import java.io.File;
import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class implements the main graphic user interface (GUI) of the p05 Toy Saga II program
 */
public class ToySaga extends processing.core.PApplet{ // TODO declare ToySaga to inherit from the PApplet class

      // CONSTANTS
      // PATH to the folder of all images
      private static final String IMAGES_PATH = "images" + File.separator;

      // filename of the day background image of this toy saga
      protected static final String DAY_BACKGROUND = IMAGES_PATH + "backgroundDay.png";

      // filename of the night background image of this toy saga
      protected static final String NIGHT_BACKGROUND = IMAGES_PATH + "backgroundNight.png";

      // filename of the image of the bed
      protected static final String BED = IMAGES_PATH + "bed.png";

      // filename of the image of the nightstand
      protected static final String NIGHTSTAND = IMAGES_PATH + "nightstand.png";

      // filename of the image of the rug
      protected static final String RUG = IMAGES_PATH + "rug.png";

      // filename of the image of the car
      protected static final String CAR = IMAGES_PATH + "car.png";

      // filename of the image of the teddy bear
      protected static final String BEAR = IMAGES_PATH + "teddyBear.png";

      // filename of the image of the hoverball when it is on (night mode)
      protected static final String HOVERBALL_ON = IMAGES_PATH + "hoverBallOn.png";

      // filename of the image of the hoverball when it is off (day mode)
      protected static final String HOVERBALL_OFF = IMAGES_PATH + "hoverBallOff.png";

      // day mode
      protected static final String DAY_MODE = "DAY";

      // night mode
      protected static final String NIGHT_MODE = "NIGHT";

      // Maximum number of visible toys that can be stored in the drawableObjects list.
      private static final int MAX_TOYS_COUNT = 8;

      // other fields

      private static processing.core.PImage backgroundImage; // PImage object that represents the
  // background image

      private ArrayList<Drawable> drawableObjects;

      private String mode;

      public ToySaga() {
        drawableObjects = new ArrayList<>();
        mode = DAY_MODE;
      }

      // TODO add an instance (NOT final) field of type ArrayList named drawableObjects.
      // The drawableObjects arraylist stores elements of type Drawable (interface Drawable) ONLY.

      // TODO add an instance (NOT final) field of type String named mode.
      // mode represents the current mode of this ToySaga application.


      /**
       * Driver method that launches the application by calling this.runApplication()
       *
       * @param args list of input arguments if any
       */
  public static void main(String[] args) {
    PApplet.main("ToySaga"); // starts the application
  }



  /**
   * Gets the current mode of this Toy Saga app. The mode might be DAY or NIGHT.
   * 
   * @return the current mode of this application
   */


  public String getMode() {
    return mode;
  }

  /**
   * Returns true if this ToySaga mode is NIGHT_MODE
   * 
   * @return true if this ToySaga mode is NIGHT_MODE
   */


  public boolean isNightMode() {
    return mode.equals(NIGHT_MODE);
  }

   
  



  /**
   * Switches the mode of this toy saga application and loads the background image of the switched
   * mode. <BR>
   * 
   * Meaning, sets the mode to NIGHT_MODE if it was DAY_MODE and vice versa, and updates the
   * background image accordingly.
   */

  public void switchMode() {
    if (mode.equals(DAY_MODE)) {
      mode = NIGHT_MODE;
      backgroundImage = loadImage(NIGHT_BACKGROUND);
    } else {
      mode = DAY_MODE;
      backgroundImage = loadImage(DAY_BACKGROUND);
    }

    
  }

  // TODO override the settings(), setup(), draw(), mousePressed(), mouseReleased(),
  // and keyPressed() callback methods predefined in the base class PApplet
  // uncomment the below code and complete the missing implementations
  /**
   * Sets the size of the display window of this graphic application
   */

  @Override
  public void settings() {
  this.size(800, 600);
  }

  /**
   * Sets the title and defines the initial environment properties of this graphic application. <br>
   * This method initializes all the data fields defined in this class.
   */


  @Override
  public void setup() {
    this.getSurface().setTitle("P5 Toy Saga v2.0");
    this.textAlign(CENTER, CENTER);
    this.imageMode(CENTER);
    this.rectMode(CORNERS); 
    this.focused = true;

    mode = DAY_MODE;
    backgroundImage = loadImage(DAY_BACKGROUND);

    drawableObjects = new ArrayList<>();

    GraphicObject.setProcessing(this);

    SwitchButton.setProcessing(this);

    SwitchButton switchButton = new SwitchButton(565, 20);
    drawableObjects.add(switchButton);

    GraphicObject bed = new GraphicObject(BED, 520, 270);
    drawableObjects.add(bed);

    GraphicObject rug = new GraphicObject(RUG, 220, 370);
    drawableObjects.add(rug);

    GraphicObject nightstand = new GraphicObject(NIGHTSTAND, 325, 240);
    drawableObjects.add(nightstand);
  }

  /**
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped.
   * 
   * This method first draws the background image to the center of the screen. Then, it draws every
   * object stored in the drawableObjects list
   */


  @Override
  public void draw() {

    if (mode.equals(DAY_MODE)) {
      backgroundImage = loadImage(DAY_BACKGROUND);
  } else if (mode.equals(NIGHT_MODE)) {
      backgroundImage = loadImage(NIGHT_BACKGROUND);
  }
  image(backgroundImage, 400, 300, width, height);

  for (Drawable drawable : drawableObjects) {
    drawable.draw();
  }
  
  }

  /**
   * Callback method called once after every time the mouse button is pressed.
   * 
   * This method calls the onClick() method on every instance of MouseListener stored in the
   * drawableObjects list
   * 
   */


  @Override
  public void mousePressed() {
      boolean switchButtonClicked = false;

      // Attempt to click the switch button first
      for (Drawable drawable : drawableObjects) {
          if (drawable instanceof SwitchButton) {
              SwitchButton button = (SwitchButton) drawable;
              if (button.isMouseOver()) {
                  button.onClick(); // Click the switch button
                  switchButtonClicked = true;
              }
          }
      }

      // If the switch button wasn't clicked, check other objects
      if (!switchButtonClicked) {
          for (Drawable drawable : drawableObjects) {
              if (drawable instanceof MouseListener && !(drawable instanceof SwitchButton)) {
                  MouseListener listener = (MouseListener) drawable;
                  if (listener.isMouseOver()) {
                      listener.onClick();
                      // Decide here if you want to break or allow multiple objects to react to the click
                  }
              }
          }
      }
  }


    /**
   * Callback method called every time the mouse button is released.
   * 
   * This method calls the onRelease() method on every instance of MouseListener stored in the
   * drawableObjects list
   * 
   */


  @Override
  public void mouseReleased() {
    
    for (Drawable drawable : drawableObjects) {
      if (drawable instanceof MouseListener) {
          MouseListener listener = (MouseListener) drawable;
          if (listener.isMouseOver()) {
              listener.onRelease();
          }
      }
    }
  }

  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the this.key() this method.<BR>
   * The ToySaga.keyPressed() method performs the below actions based on the pressed key: <BR>
   *
   * - Pressing 'c' or 'C' adds a new Car object at the mouse position if the MAX TOYS COUNT is not
   * reached. <BR>
   * - Pressing 't' or 'T' adds a new TeddyBear object at the mouse position if the MAX TOYS COUNT
   * is not reached. <BR>
   * - Pressing 'h' or 'H' adds a new Hoverball object at the mouse position if the MAX TOYS COUNT
   * is not reached. <BR>
   * - Pressing 'd' or 'D' sets/switches the mode to DAY_MODE and loads the DAY_BACKGROUND for the
   * background image of this application. <BR>
   * - Pressing 'n' or 'N' sets/switches the mode to NIGHT_MODE and loads the NIGHT_BACKGROUND for
   * the background image of this application. <BR>
   *
   */


  @Override
  public void keyPressed() {
      // Calculate the current number of Toy objects in drawableObjects
      long currentToyCount = drawableObjects.stream().filter(obj -> obj instanceof Toy).count();

      switch (Character.toLowerCase(key)) {
          case 'c':
              if (currentToyCount < MAX_TOYS_COUNT) {
                  drawableObjects.add(new Car(mouseX, mouseY));
              }
              break;
          case 't':
              if (currentToyCount < MAX_TOYS_COUNT) {
                  drawableObjects.add(new TeddyBear(mouseX, mouseY));
              }
              break;
          case 'h':
              if (currentToyCount < MAX_TOYS_COUNT) {
                  drawableObjects.add(new Hoverball(mouseX, mouseY));
              }
              break;
          case 'd':
              mode = DAY_MODE;
              backgroundImage = loadImage(DAY_BACKGROUND);
              break;
          case 'n':
              mode = NIGHT_MODE;
              backgroundImage = loadImage(NIGHT_BACKGROUND);
              break;
      }
  }


    public int getToyCount() {
    // Assuming all drawableObjects are Toys or check if they are instances of Toy if needed
    return drawableObjects.size();
}

public boolean noToyIsDragging() {
  for (Drawable drawable : drawableObjects) {
      if (drawable instanceof Toy) {
          if (((Toy) drawable).isDragging()) {
              return false; // Found a Toy object that is being dragged
          }
      }
  }
  return true; // No Toy objects are being dragged
}

}
