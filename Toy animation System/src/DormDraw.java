//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (DormDraw.java)
// Course:   CS 300 Spring 2024
//
// Author:   (Annabelle Jeong)
// Email:    (hjeong58@wisc.edu)
// Lecturer: (Mouna)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Peer mentor Luke helped me with method keypressed() and mousePressed()
// Peer mentor MK helped me to code isMouseOver() method. 
// Online: 
//https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2024/spring/p2/doc/Symbol.html
// - Used javadoc in order to easily identify the parameters. 
//
///////////////////////////////////////////////////////////////////////////////
import processing.core.PImage;
import java.io.File;

/**
 * DormDraw class is a drawing application that arranges the symbols such as 
 * furniture items in a dorm room. 
 * In this application, you can add, remove, rotate, move the furniture items by dragging and 
 * dropping the items with mouse or pressing certain keys in a dorm room. 
 */
public class DormDraw {
  
  private static PImage backgroundImage;

  private static Symbol[] symbols;

  
  
  /**
   * 
   * This is the main method and runs its application. 
   * @param args Command line arguments (not used).
   */
  public static void main(String[] args) {
    Utility.runApplication();
    }
  
  
  /**
   * It sets up the drawing canvas as the background image and also initialize the symbols array. 
   */
  public static void setup() {
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    symbols = new Symbol[12];
  }
  
  
  
  /**
   * This adds the new symbol to the available spots in symbol array in the canvas. 
   * @param symbols It is the array of symbols to add new symbols in the canvas. 
   * @param toAdd It is a new symbol to add in a array. 
   */
  public static void addSymbol(Symbol[] symbols, Symbol toAdd) {
    for (int i=0; i<symbols.length; i++) {
      if(symbols[i] == null) {
        symbols [i] = toAdd;
        return;
      }
    }
    
  }
  
  
  
  /**
   * It draws the background of the symbol array and all non-null symbols on the canvas.
   */
  public static void draw() {
    Utility.background(Utility.color(255,250,250)); 
    Utility.image(backgroundImage, Utility.width()/2, Utility.height()/2);
    
    
    for (int i = 0; i < symbols.length; i++ ) {
      
      if(symbols[i] != null) {
        symbols[i].draw();
        }
      }
    }
  
  
  
  
  /**
   * It handles the the rotate, remove and saving the canvas by the key that is pressed by the user.
   * 
   */
  public static void keyPressed() {
    char key = Utility.key();
    
    if(key == 'r' || key == 'R') {
      mouseRotateSymbol();
      return;
  }
    
    if(key == Utility.BACKSPACE) {
      mouseRemoveSymbol();
      return;
    }
    
    if (key == 's' || key == 'S') {
      Utility.save("dormDraw.png");
      return;
    }
    
    
    Symbol newSymbol = null;
    
    switch(Character.toLowerCase(key)) {
      case 'b':
        newSymbol = new Symbol("bed.png", Utility.mouseX(),Utility.mouseY());
        break;
      case 'c':
        newSymbol = new Symbol("chair.png", Utility.mouseX(),Utility.mouseY());
        break;
      case 'd':
        newSymbol = new Symbol("dresser.png", Utility.mouseX(),Utility.mouseY());
        break;
      case 'k':
        newSymbol = new Symbol("desk.png", Utility.mouseX(),Utility.mouseY());
        break;
      case 'f':
        newSymbol = new Symbol("sofa.png", Utility.mouseX(),Utility.mouseY());
        break;
      case 'g':
        newSymbol = new Symbol ("rug.png", Utility.mouseX(),Utility.mouseY());
        break;
      case 'p':
        newSymbol = new Symbol ("plant.png", Utility.mouseX(),Utility.mouseY());
        break;
       
      default:
        return;
        
    }
        
        if (newSymbol != null) {
          addSymbol(symbols, newSymbol);
        }
        
    }
    
    
    
  /**
   * It determines if the user mouse is over the symbol which is the furniture item. 
   * @param symbol It checks the position of the mouse. 
   * @return true if the mouse is over the symbol or else return false. 
   */
    public static boolean isMouseOver(Symbol symbol) {
      
      int x = symbol.x();
      int y = symbol.y();
      
      int height = symbol.height ();
      int width = symbol.width();
      
      int mouseX = Utility.mouseX();
      int mouseY = Utility.mouseY();
      
 
      
      if ((mouseY >= y - (height/2) && mouseY <= y + (height/2))
      &&(mouseX >= x - (width/2) && mouseX < x + (width/2))){
        
      return true; 
        
      } else {
        
        return false;
      }
      
    }
    
    
    /**
     * It handles the process of the mouse click event to start dragging the symbol.
     */
    public static void mousePressed() {
      for (int i = 0; i < symbols.length; i++) {
        if (symbols[i] != null && isMouseOver(symbols[i])) {
            symbols[i].startDragging();
            break;
            }
        }
    }
   

    
    /**
     * It handles to stop the mouse dragging the symbol. 
     */
    public static void mouseReleased() {
      for (int i = 0; i < symbols.length; i++) {
        if (symbols[i] != null) {
          symbols[i].stopDragging();
          }
        }
      }
        
  
    
    
    /**
     * It rotates the symbol that is under the mouse. 
     */
    private static void mouseRotateSymbol() {
      for (int i = 0; i < symbols.length; i++) {
        if (symbols[i] != null && isMouseOver(symbols[i])) {
            symbols[i].rotate(); 
            break;
        }
      }
    }
    
    
    /**
     * It removes the symbol that is under the mouse.  
     */
    private static void mouseRemoveSymbol () {
      for (int i = 0; i < symbols.length; i++) {
        if (symbols[i] != null && isMouseOver(symbols[i])) {
            symbols[i] = null; 
            break; 
            }
        }
    }
    
}

