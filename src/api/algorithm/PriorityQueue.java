package api.algorithm;

import api.algorithm.*;

import java.util.LinkedList;
/**
  * The class <code>PriotiryQueue</code> represents the priorit queue for the a star
  * @version 1.0
  * @author Dorian Terbah
**/

public class PriorityQueue extends LinkedList<Node> {

    private AStarComparator comparator;

    public PriorityQueue(AStarComparator comparator) {
        this.comparator = comparator;
    }
}