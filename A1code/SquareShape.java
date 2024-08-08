/**
 * ===============================================================================
 * SquareShape.java : A shape that is a square.
 * YOUR UPI: jehc820
 * =============================================================================== */
import java.awt.*;

//Complete the SquareShape class

class SquareShape extends RectangleShape {
    public SquareShape() {
        super();
        width = DEFAULT_HEIGHT;
    }
    public SquareShape(int x, int y, int h, int pw, int ph, Color c, Color bc, PathType pt) {
        super(x, y, h, h, pw, ph, c, bc, pt);
    }
}