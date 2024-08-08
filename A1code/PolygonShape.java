/*
 *	===============================================================================
 *	PolygonShape.java : A shape that is a polygon.
 *  YOUR UPI: jehc820
 *	=============================================================================== */
import java.awt.*;
import java.util.Arrays;
//Complete the PolygonShape class
class PolygonShape extends SquareShape {
    private int radius = DEFAULT_HEIGHT / 2;
    private int numberOfSides = 6;
    private Point centre = new Point((DEFAULT_HEIGHT / 2), (DEFAULT_HEIGHT / 2));
    public PolygonShape() { super(); }
    public PolygonShape(int x, int y, int s, int pw, int ph, Color c, Color bc, PathType pt, ShapeType st){
        super(x, y, s, pw, ph, c, bc, pt);
        numberOfSides = st.getNumberOfSides();
        centre = new Point(x,y);
        radius = s/2;
    }
    public void draw(Graphics g) {
        super.draw(g);
		
		int[] xList = new int[numberOfSides];
		int[] yList = new int[numberOfSides];
		
		centre.setLocation(((int)(x + radius)), ((int)(y + radius)));
		
		for (int i = 0; i < numberOfSides; i ++) {
		    xList[i] = (int) (centre.x + radius * Math.cos((i * 2 * Math.PI) / numberOfSides));
		    yList[i] = (int) (centre.y + radius * Math.sin((i * 2 * Math.PI) / numberOfSides));
		}
		
		System.out.printf("%s:",getClass().getName());
		System.out.print(Arrays.toString(xList) + "," + Arrays.toString(yList) + "\n");
    }
}