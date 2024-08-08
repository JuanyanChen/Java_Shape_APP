/*
 * ==========================================================================================
 * AnimationViewer.java : Moves shapes around on the screen according to different paths.
 * It is the main drawing area where shapes are added and manipulated.
 * YOUR UPI: jehc820
 * ==========================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListDataListener;
import java.lang.reflect.Field;

class AnimationViewer extends JComponent implements Runnable,TreeModel  {
	private Thread animationThread = null; // the thread for animation
	private static int DELAY = 120; // the current animation speed
	private ShapeType currentShapeType = Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType = Shape.DEFAULT_PATHTYPE; // the current path type
	private Color currentColor = Shape.DEFAULT_COLOR; // the current fill colour of a shape
	private Color currentBorderColor = Shape.DEFAULT_BORDER_COLOR;
	private int currentPanelWidth = Shape.DEFAULT_PANEL_WIDTH, currentPanelHeight = Shape.DEFAULT_PANEL_HEIGHT,currentWidth = Shape.DEFAULT_WIDTH, currentHeight = Shape.DEFAULT_HEIGHT;
	private String currentLabel = Shape.DEFAULT_LABEL;
	//ArrayList<Shape> shapes = new ArrayList<Shape>(); //create the ArrayList to store shapes

	protected NestedShape root;
	public AnimationViewer() {
		start();
		root = new NestedShape(Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT);
	}

//	protected void createNewShape(int x, int y) {
//		int min_size = Math.min(currentWidth, currentHeight);
//		switch (currentShapeType) {
//			case RECTANGLE: {
//			shapes.add( new RectangleShape(x, y,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
//			break;
//			}  case SQUARE: {
//			shapes.add( new SquareShape(x, y,min_size,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
//			break;
//			}
//		}
//	}
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape currentShape : root.getAllInnerShapes()) {
			currentShape.move();
			currentShape.draw(g);
			currentShape.drawString(g);
		}
}
	public void resetMarginSize() {
			currentPanelWidth = getWidth();
			currentPanelHeight = getHeight();
			for (Shape currentShape : root.getAllInnerShapes())
				currentShape.resetPanelSize(currentPanelWidth, currentPanelHeight);
	}

	private ArrayList<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
	public NestedShape getRoot(){ return root;}
	public boolean isLeaf(Object node) {return !(node instanceof NestedShape);}
	public boolean isRoot(Shape selectedNode) {return selectedNode.getParent() == null;}
	public Shape getChild(Object parent, int index) {
	    if (!(parent instanceof NestedShape && index >= 0 && index < ((NestedShape)parent).getAllInnerShapes().size())) return null;
	    else return ((NestedShape)parent).getAllInnerShapes().get(index);
	}
	public int  getChildCount(Object parent) {
	    if (!(parent instanceof NestedShape)) return 0;
	    else return ((NestedShape)parent).getAllInnerShapes().size();
	}
	public int getIndexOfChild(Object parent, Object child) {
	    if (!(parent instanceof NestedShape)) return -1;
	    else return ((NestedShape)parent).indexOf((Shape)child);
	}
	public void addTreeModelListener(final TreeModelListener tml) { treeModelListeners.add(tml);}
	public void removeTreeModelListener(final TreeModelListener tml) { treeModelListeners.remove(tml);}
	public void valueForPathChanged(TreePath path, Object newValue){}
	
	public void fireTreeNodesInserted(Object source, Object[] path,int[] childIndices,Object[] children){
	    TreeModelEvent treeEvent = new TreeModelEvent(source,path,childIndices,children);
	    for(TreeModelListener tml:treeModelListeners){ tml.treeNodesInserted(treeEvent); }
	    System.out.printf("Called fireTreeNodesInserted: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
	}
	public void addShapeNode(NestedShape selectedNode) {
	    Object newChild = selectedNode.createInnerShape(currentPathType, currentShapeType);
	    fireTreeNodesInserted(this, selectedNode.getPath(),new int[] {selectedNode.getSize()-1}, new Object[]{newChild});
	}
	
	public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices,Object[] children) {
	    TreeModelEvent treeEvent = new TreeModelEvent(source,path,childIndices,children);
	    for(TreeModelListener tml:treeModelListeners){ tml.treeNodesRemoved(treeEvent); }
	    System.out.printf("Called fireTreeNodesRemoved: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
	}
	public void removeNodeFromParent(Shape selectedNode) {
	    Shape parent = selectedNode.getParent();
	    int index = ((NestedShape)parent).indexOf(selectedNode);
	    ((NestedShape)parent).removeInnerShape(selectedNode);
	    fireTreeNodesRemoved(this, parent.getPath(),new int[] {index}, new Object[]{selectedNode});
	}

	// you don't need to make any changes after this line ______________
	public String getCurrentLabel() {return currentLabel;}
	public int getCurrentHeight() { return currentHeight; }
	public int getCurrentWidth() { return currentWidth; }
	public Color getCurrentColor() { return currentColor; }
	public Color getCurrentBorderColor() { return currentBorderColor; }
	public void setCurrentShapeType(ShapeType value) {currentShapeType = value;}
	public void setCurrentPathType(PathType value) {currentPathType = value;}
	public ShapeType getCurrentShapeType() {return currentShapeType;}
	public PathType getCurrentPathType() {return currentPathType;}
	public void update(Graphics g) {
		paint(g);
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
		while (animationThread == myThread) {
			repaint();
			pause(DELAY);
		}
	}
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long) milliseconds);
		} catch (InterruptedException ie) {}
	}
}
