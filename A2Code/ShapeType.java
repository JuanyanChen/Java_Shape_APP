/*
 *  ============================================================================================
 *  enum which defines the type of shapes in A1
 *  YOUR UPI: jehc820
 *  ============================================================================================
 */
enum ShapeType { RECTANGLE(4), SQUARE(4), HEXAGON(6), HEPTAGON(7),OCTAGON(8);
  private int numberOfSides;
  private ShapeType(int s) { numberOfSides = s; }
  public int getNumberOfSides() { return numberOfSides; }
}