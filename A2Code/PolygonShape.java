/*
 *	===============================================================================
 *	PolygonShape.java : A shape that is a polygon.
 *  YOUR UPI:jehc820
 *	=============================================================================== */
import java.awt.*;
import java.util.Arrays;
class PolygonShape extends SquareShape {
	private Point centre = new Point(DEFAULT_HEIGHT/2, DEFAULT_HEIGHT/2);
	private int radius = DEFAULT_HEIGHT/2;
	private int numberOfSides = 6;
    public PolygonShape() {}
	public PolygonShape(int x, int y, int s, int pw, int ph, Color c, Color bc, PathType pt, ShapeType st) {
		super(x ,y ,s, pw ,ph, c, bc, pt);
		this.numberOfSides = st.getNumberOfSides();
		radius = s/2;
		centre  = new Point(x + width/2, y + height/2);
	}
	public void draw(Graphics g) { 
		
		int[] xList = new int[numberOfSides];
		int[] yList = new int[numberOfSides];
		
		centre.setLocation(((int)(x + radius)), ((int)(y + radius)));
		
		for (int i = 0; i < numberOfSides; i ++) {
		    xList[i] = (int) (centre.x + radius * Math.cos((i * 2 * Math.PI) / numberOfSides));
		    yList[i] = (int) (centre.y + radius * Math.sin((i * 2 * Math.PI) / numberOfSides));
		}
		
		g.setColor(color);
        g.fillPolygon(xList, yList, numberOfSides);
        g.setColor(borderColor);
        g.drawPolygon(xList, yList, numberOfSides);

	}
}