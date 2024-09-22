//////////////// FILE HEADER //////////////////////////
//
// Title: Toy.java
// Course:   CS 300 Spring 2024
//
// Author:   Seungchan Min
// Email:    smin43@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name: Annabelle Jeong 
// Partner Email: hjeong58@wisc.edu 
// Partner Lecturer's Name: Mouna Kacem 
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources: 
// Javadoc for toy class - https://cs300-www.cs.wisc.edu/sp24/p05/doc/Toy.html
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class is Toy class which represents the graphic content that can be dragged
 * from the ToySaga.java 
 * It extends the GraphicObject and implements the MousListener and Movable interfaces in order 
 * let mouse to interact and move in the program. 
 */
public class Toy extends GraphicObject implements MouseListener, Movable  {
    private boolean isDragging;

    
    /**
     * This Toy method constructs new Toy with specific given image file. 
     * 
     * @param filename It is the name of the image file 
     */
    public Toy(String filename){
        super(filename);
        this.isDragging = false;// The initial state for mouse dragging is false. 

    }
    
   /**
    * 
    * This Toy method constructs new Toy with specific filename, x and y points (coordinates). 
    * 
    * @param filename It is the name of the image file 
    * @param x It is the initial x coordinate of the object (toy). 
    * @param y It is the initial y coordinate of the object (toy). 
    */
    public Toy(String filename, int x, int y){
        super(filename, x, y);
        this.isDragging = false; // The initial state for mouse dragging is false. 

    }

    
    /**
     * This method draw handles the movement of the object if they are being dragged by the mouse. 
     */
    @Override
    public void draw() {
        this.move();
        super.draw();

    }

    
    /**
     * This boolean isDragging method returns the current state of the toy if it's being dragged. 
     * @return true if the toy is being dragged or else return false if it's not being dragged. 
     */
    public boolean isDragging(){
        return this.isDragging;
    }

    
    /**
     * This void startDragging method initiates the dragging of the object(toy). 
     */
    public void startDragging() {
        this.isDragging = true;
    }

    
    /**
     * This void stopDragging method stops the dragging of the object(toy). 
     */
    public void stopDragging(){
        this.isDragging = false;
    }

    
    /**
     * This move method with specific delta x and delta y and it also ensures that the toy 
     * doesn't move out of the specific bound of the ToySaga environment. 
     * @param dx It is the change of x coordinate 
     * @param dy It is the change of y coordinate
     */
    protected void move(int dx, int dy) {


            int newX = Math.max(0, Math.min(this.x + dx, toySaga.width - this.image.width));
            int newY = Math.max(0, Math.min(this.y + dy, toySaga.height - this.image.height));

            this.x = newX;
            this.y = newY;

    }
    
    

    /**
     * It is the method move updates the position of the object by the movement of the mouse if it 
     * is being dragged by the mouse. 
     */
    @Override
    public void move(){
        if (this.isDragging) {
            // It calculates the difference in mouse positions
            int dx = toySaga.mouseX - toySaga.pmouseX;
            int dy = toySaga.mouseY - toySaga.pmouseY;

            // It updates the position of the mouse 
            this.x += dx;
            this.y += dy;
        }

    }

    
    
    /**
     * This onClick method handles them mouse click. 
     * It starts dragging the object if the mouse is over the toy. 
     */
    @Override
    public void onClick() {
        if (this.isMouseOver()) {
            this.startDragging();
        }
    }

    
    /**
     * This onRelease method handles mouse release. 
     * It stops dragging the object if the mouse is released. 
     */
    @Override
    public void onRelease() {
        this.stopDragging();
    }


    
    /**
     * This isMouseOver method identifies if the mouse currently is over the toy(object). 
     * It checks the edge of the toy image and checks if the mouse curser is in the bound of the 
     * toy (object). 
     */
    @Override
    public boolean isMouseOver() {
        int imageWidth = this.image.width;
        int imageHeight = this.image.height;

        // It calculate the edges of the toy's image assuming x, y is the center
        int rightEdge = this.x + (imageWidth / 2);
        int bottomEdge = this.y + (imageHeight / 2);
        int leftEdge = this.x - (imageWidth / 2);
        int topEdge = this.y - (imageHeight / 2);

        // It check if the mouse cursor is within the bounds of the toy's image
        boolean isOver = toySaga.mouseX >= leftEdge && toySaga.mouseX <= rightEdge &&
                toySaga.mouseY >= topEdge && toySaga.mouseY <= bottomEdge;

        return isOver;
    }


}





    

