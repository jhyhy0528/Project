import processing.core.PImage;

public class GraphicObject implements Drawable {

    protected static ToySaga toySaga;
    protected processing.core.PImage image;
    protected int x;
    protected int y;

    public GraphicObject(String filename, int x, int y){
        this.x = x;
        this.y = y;
        this.image = toySaga.loadImage(filename);

    }

    public GraphicObject(String filename){
        if (toySaga != null) {
            this.image = toySaga.loadImage(filename);
            this.x = (toySaga.width - image.width) / 2;
            this.y = (toySaga.height - image.height) / 2;
        } else {
            this.x = 0;
            this.y = 0;
        }


    }

    public void setImage(String filename){
        if (toySaga != null) {
            this.image = toySaga.loadImage(filename);
        }
    }

    public void draw(){
        if (toySaga != null && this.image != null) {
            toySaga.image(this.image, this.x, this.y);
        }
    }

    public int getX(){
        return this.x;

    }

    public int getY(){
        return this.y;

    }

    public static void setProcessing(ToySaga toySaga){
        GraphicObject.toySaga = toySaga;
    }


}

