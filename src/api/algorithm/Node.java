package api.algorithm;

import helper.Position;

/**
  * The class <code>Node</code> represents a node in the graph
  * @version 1.0
  * @author Dorian Terbah
**/

public class Node {

    private Position position;
    
    public int cost;
    
    public int heuristic;

    public Node(Position position, int cost, int heuristic) {
        this.position = position;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    public Node(int x, int y, int cost, int heuristic) {
        this.position = new Position(x, y);
        this.cost = cost;
        this.heuristic = heuristic;
    }

    /*********************************      
    **************Getter**************
    *********************************/

    /**
      * Get the position of the node
      * @return The position of the node 
    **/
    public Position getPosition() {
        return this.position;
    }

    /**
      * Get the cost of the node
      * @return The cost of the node
    **/
    public int getCost() {
        return this.cost;
    }

    /**
      * Get the heuristic of the node
      * @return The heuristic of the node 
    **/
    public int getHeuristic() {
        return this.heuristic;
    }

    /*********************************      
    **************Setter**************
    *********************************/

    /**
      * Set a new value for the cost
      * @param cost The new cost 
    **/
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
      * Set a new value for the heuristic 
      * @param heuristic The new heuristic
    **/
    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}