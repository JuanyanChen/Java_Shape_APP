/*
 *	===============================================================================
 *	RectangleShape.java : A shape that is a rectangle.
 *  YOUR UPI: jehc820
 *	=============================================================================== */
import java.awt.*;
class RectangleShape extends Shape{
	public RectangleShape() {}
	public RectangleShape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
		super(x, y, w, h, pw, ph, c, bc, pt);
	}
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(borderColor);
        g.drawRect(x, y, width, height);
    }
}
