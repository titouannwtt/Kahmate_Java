package api.algorithm;

import api.algorithm.Node; 

/**
  * The class <code>AStarComparator</code> compars two has to compars two nodes
  * @version 1.0
  * @author Dorian Terbah
**/

public class AStarComparator {
    
    public AStarComparator() {

    }

    public int compare(Node n, Node m) {

        if(n.getHeuristic() < m.getHeuristic()) {
            return 1;
        } else if(n.getHeuristic() == m.getHeuristic()) {
            return 0;
        } 
        return -1;
    }

}