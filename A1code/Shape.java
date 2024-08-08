/**
 *    ===============================================================================
 *    Shape.java : The superclass of all shapes.
 *    A shape defines various properties, including selected, colour, width and height.
 *    YOUR UPI: jehc820
 *    ===============================================================================
 */
import java.awt.*;
abstract class Shape {
	//modify the default shape type and the default path type
    public static final PathType DEFAULT_PATHTYPE = PathType.BOUNCING;
    public static final ShapeType DEFAULT_SHAPETYPE = ShapeType.RECTANGLE;

    public static final int DEFAULT_X = 0, DEFAULT_Y = 0, DEFAULT_WIDTH=200, DEFAULT_HEIGHT=100, DEFAULT_PANEL_WIDTH=600, DEFAULT_PANEL_HEIGHT=400;
    public static final Color DEFAULT_COLOR=Color.orange, DEFAULT_BORDER_COLOR=Color.black;
    public int x, y, width=DEFAULT_WIDTH, height=DEFAULT_HEIGHT, panelWidth=DEFAULT_PANEL_WIDTH, panelHeight=DEFAULT_PANEL_HEIGHT; // the bouncing area
    protected Color color = DEFAULT_COLOR, borderColor =DEFAULT_BORDER_COLOR ;
    protected MovingPath path = new BouncingPath(1, 2);

    public Shape() {}
    //modify the constructor
    public Shape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
        this.x = x;
        this.y = y;
        panelWidth = pw;
        panelHeight = ph;
        width = w;
        height = h;
        color = c;
        borderColor = bc;
        if (pt == PathType.DOWN_RIGHT) {
            path = new DownRightPath(5, 5);
        }
    }
    
    public String toString() {
		return String.format("%s:[(%d,%d),%dx%d(%s)]", getClass().getName(), x,y,width,height,path.getClass().getName());
	}
	public void move() { path.move();}
	public abstract void draw(Graphics g);
 	public int getX() { return this.x; }
    public int getY() { return this.y;}
    public int getWidth() { return width; }
	public void setWidth(int w) { if (w < DEFAULT_PANEL_WIDTH && w > 0) width = w; }
    public int getHeight() {return height; }
	public void setHeight(int h) { if (h < DEFAULT_PANEL_HEIGHT && h > 0) height = h; }
	public Color getColor() { return color; }
    public void setColor(Color fc) { color = fc; }
	public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color bc) { borderColor = bc; }
    public void resetPanelSize(int w, int h) {
		panelWidth = w;
		panelHeight = h;
	}

    /* Inner class ===================================================================== Inner class
     *    MovingPath : The superclass of all paths. It is an inner class.
     *    A path can change the current position of the shape.
     *    =============================================================================== */
    
    abstract class MovingPath {
        protected int deltaX, deltaY; // moving distance
        public MovingPath(int dx, int dy) { deltaX=dx; deltaY=dy; }
        public MovingPath() { }
        public abstract void move();
        public String toString() { return getClass().getSimpleName(); };
    }
    class BouncingPath extends MovingPath {
        public BouncingPath(int dx, int dy) {
            super(dx, dy);
         }
        public void move() {
             x = x + deltaX;
             y = y + deltaY;
             if ((x < 0) && (deltaX < 0)) {
                 deltaX = -deltaX;
                 x = 0;
             }
             else if ((x + width > panelWidth) && (deltaX > 0)) {
                 deltaX = -deltaX;
                 x = panelWidth - width;
             }
             if ((y< 0) && (deltaY < 0)) {
                 deltaY = -deltaY;
                 y = 0;
             }
             else if((y + height > panelHeight) && (deltaY > 0)) {
                 deltaY = -deltaY;
                 y = panelHeight - height;
             }
        }
    }
    //Complete the inner class
    class DownRightPath extends MovingPath {
        private int count = 0;
        public static int INTERVAL = 5;
        public DownRightPath (int deltaX, int deltaY) {
            super(deltaX, deltaY);
        }
        public void move() {
            if (count == 5) {
                x = x + deltaX;
                count = 0;
                if ((x < 0) && (deltaX < 0)) {
                    deltaX = -deltaX;
                    x = 0;
                }
                else if ((x + width > panelWidth) && (x + width == panelWidth) && (deltaX > 0)) {
                    x = 0;
                }

            }
            else {
                y = y + deltaY;
                count++;
                if ((y< 0) && (deltaY < 0)) {
                    deltaY = -deltaY;
                    y = 0;
                }
                else if((y + height > panelHeight) && (deltaY > 0)) {
                    y = 0;
                }
            }
        }
    }
}