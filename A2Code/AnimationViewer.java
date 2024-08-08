/*
 * ==========================================================================================
 * AnimationViewer.java : Moves shapes around on the screen according to different paths.
 * It is the main drawing area where shapes are added and manipulated.
 * YOUR UPI:jehc820
 * ==========================================================================================
 */


import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
class AnimationViewer extends JComponent implements Runnable {
	private Thread animationThread = null;	// the thread for animation
 	private static int DELAY = 120;		 // the current animation speed
	ArrayList<Shape> shapes = new ArrayList<Shape>(); //create the ArrayList to store shapes
	private ShapeType currentShapeType=Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType=Shape.DEFAULT_PATHTYPE;	// the current path type
	private Color currentColor=Shape.DEFAULT_COLOR; // the current fill colour of a shape
	private Color currentBorderColor = Shape.DEFAULT_BORDER_COLOR;
	private int currentPanelWidth=Shape.DEFAULT_PANEL_WIDTH, currentPanelHeight = Shape.DEFAULT_PANEL_HEIGHT, currentWidth=Shape.DEFAULT_WIDTH, currentHeight=Shape.DEFAULT_HEIGHT;
	// modify the paintComponent method
	public void paintComponent(Graphics g) {
		for (Shape currentShape: shapes) {
			currentShape.move();
			currentShape.draw(g);
			currentShape.drawString(g);
		}
	}
	protected void createNewShape() {
		int min_size = Math.min(currentWidth, currentHeight);
		switch (currentShapeType) {
			case RECTANGLE: {
				shapes.add( new RectangleShape(0, 0,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
		        break;
			} case SQUARE: {
				shapes.add( new SquareShape(0, 0,min_size,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
		        break;
			} default : {
		       shapes.add( new PolygonShape(0, 0,min_size,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType, currentShapeType));
		        break;
			}
	   }
  	}
	public AnimationViewer() {
		start();
	}

	public ShapeType getCurrentShapeType() { return currentShapeType; }
	public PathType getCurrentPathType() { return currentPathType; }
	public void setCurrentShapeType(ShapeType s) { currentShapeType = s; }
	public void setCurrentPathType(PathType p) { currentPathType = p; }
	public int getCurrentWidth() { return  currentWidth; }
	public int getCurrentHeight() { return currentHeight; }
	public void setCurrentWidth(int w) {currentWidth=w;}
	public void setCurrentHeight(int h) {currentHeight=h;}
	public void setPanelWidth(int w) {currentPanelWidth=w;}
	public void setPanelHeight(int h) {currentPanelHeight=h;}
	public void update(Graphics g){ paint(g); }
	public void resetMarginSize() {
		currentPanelWidth = getWidth();
		currentPanelHeight = getHeight() ;
		for (Shape currentShape: shapes)
			currentShape.resetPanelSize(currentPanelWidth,currentPanelHeight );
	}
	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}
	public void stop() {
		if (animationThread != null) {
			animationThread = null;
		}
	}
	public void run() {
		Thread myThread = Thread.currentThread();
		while(animationThread==myThread) {
			repaint();
			pause(DELAY);
		}
	}
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long)milliseconds);
		} catch(InterruptedException ie) {}
	}
}
