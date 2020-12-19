package helper;

import helper.Position;

public class Distance {

    /**
      * Get the distance between two positions
      * @param p The first position
      * @param q The second distance
      * @return The distance between the two positions 
    **/
    public static double getDistance(Position p, Position q) {
        double x = Math.pow(p.getX() - q.getX(), 2);
        double y = Math.pow(p.getY() - q.getY(), 2);
        return Math.sqrt(x + y);
    }

    /**
      * Get the Manhattan between two positions
      * @param p The first position
      * @param q The second distance
      * @return The Manhattan distance between the two positions 
    **/
    public static double getManhattanDistance(Position p, Position q) {
        return (double)(Math.abs(p.getX() - q.getX()) + Math.abs(p.getY() - q.getY()));
    }
}