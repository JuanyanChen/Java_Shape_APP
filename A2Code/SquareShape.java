/**
 * ===============================================================================
 * SquareShape.java : A shape that is a square.
 * YOUR UPI:jehc820
 * =============================================================================== */
import java.awt.*;
class SquareShape extends RectangleShape {
    public SquareShape() {
		width = DEFAULT_HEIGHT;
		height = DEFAULT_HEIGHT;
	}
	public SquareShape(int x, int y, int s, int pw, int ph, Color c, Color bc, PathType pt) {
		super(x ,y ,s, s ,pw ,ph, c, bc, pt);
	}
}