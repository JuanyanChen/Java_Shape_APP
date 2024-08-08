/*
 *	===============================================================================
 *	NestedShape.java : A shape that is a Nested Shape.
 *  YOUR UPI:jehc820
 *	=============================================================================== */
import java.awt.*;
import java.util.ArrayList;

class NestedShape extends RectangleShape {
    private ArrayList<Shape> innerShapes = new ArrayList<Shape>();
    public Shape createInnerShape(PathType pt, ShapeType st) {
        Shape inner;
        if (st == ShapeType.RECTANGLE) {
            inner = new RectangleShape(0, 0, width/4, height/4, width, height, color, borderColor, pt);
        }
        else if (st == ShapeType.SQUARE) {
            int sideLenth = Math.min(width, height);
            inner = new SquareShape(0, 0, sideLenth/4, width, height, color, borderColor, pt);
        }
        else {
            inner = new NestedShape(0, 0, width/4, height/4, width, height, color, borderColor, pt);
        }
        inner.setParent(this);
        innerShapes.add(inner);
        return inner;
    }
    public NestedShape(int x, int y, int w, int h, int panelWidth, int panelHeight, Color fillColor, Color borderColor, PathType pathType) {
        super(x ,y ,w, h ,panelWidth ,panelHeight, fillColor, borderColor, pathType);
        createInnerShape(PathType.BOUNCING, ShapeType.RECTANGLE);
    }
    public NestedShape(int w, int h) {
        super(0 ,0 ,w, h ,Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT, Shape.DEFAULT_COLOR,  Shape.DEFAULT_BORDER_COLOR, PathType.BOUNCING);
    }
    public Shape getInnerShapeAt(int index) { return innerShapes.get(index);}
    public int getSize() { return innerShapes.size();}
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.translate(x, y);
        for (Shape s:innerShapes) {
            s.draw(g);
            s.drawString(g);
        }
        g.translate(-x, -y);
    }
    public void move() {
        super.move();
        for (Shape s:innerShapes) {
            s.move();
        }
    }
    public int indexOf(Shape s) { return innerShapes.indexOf(s);}
    public void addInnerShape(Shape s) { 
        innerShapes.add(s);
        s.setParent(this);
    }
    public void removeInnerShape(Shape s) { 
        innerShapes.remove(s);
        s.setParent(null);
    }
    public void removeInnerShapeAt(int index) {
        Shape s = innerShapes.get(index);
        innerShapes.remove(index);
        s.setParent(null);
    }
    public ArrayList<Shape> getAllInnerShapes() { return innerShapes;}
}