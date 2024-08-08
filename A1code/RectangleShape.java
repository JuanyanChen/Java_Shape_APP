/**
 *	===============================================================================
 *	RectangleShape.java : A shape that is a rectangle.
 *  YOUR UPI: jehc820
 *	=============================================================================== */
import java.awt.*;
//Complete the RectangleShape class
class RectangleShape extends Shape{
	public RectangleShape() {}
	public RectangleShape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
		super(x, y, w, h, pw, ph, c, bc, pt);
	}
	public void draw(Graphics g) {
		System.out.println(color);
		System.out.println(borderColor);
		System.out.println(this);
	}
}