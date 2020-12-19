package api.algorithm;

import helper.Distance;
import helper.Assert;
import helper.Position;

import api.algorithm.Node;
import api.algorithm.AStarComparator;
import api.algorithm.PriorityQueue;

import ui.Cell;

import model.BoardModel;
import model.Person;

import java.util.ArrayList;
import java.util.LinkedList;
/**
  * The class <code>AStar</code> implements the a star algorithm
  * @version 1.0
  * @author Dorian Terbah
**/
public class AStar {

    public AStar() {

    }

    /**
      * Get the smaller path betwenn two nodes
      * @param model The graph
      * @param source The source node
      * @param target The target node
      * @return The path between the two nodes 
    **/
    public static ArrayList<Node> getSmallestPath(BoardModel model, Node source, Node target) {
        LinkedList<Node> openList = new PriorityQueue(new AStarComparator());
        ArrayList<Node> closedList = new ArrayList<Node>();
        ArrayList<Node> neightbours = new ArrayList<Node>();

        boolean found = false;

        Position position;
        Cell cell;
        //add the first node
        openList.add(source);
        while(!openList.isEmpty() && !found) {
            Node node = openList.poll();
            closedList.add(node);

            if(node.getPosition().getX() == target.getPosition().getX() &&
               node.getPosition().getY() == target.getPosition().getY()
            ) {
                found = true;
                return closedList;
            }

            neightbours.clear();
            position = node.getPosition();
            //add neightbours

            cell = model.getTopCell(position);
            if(Assert.isSet(cell)) {
                neightbours.add(new Node(cell.getPosition(), 0, 0));
            }

            cell = model.getBottomCell(position);
            if(Assert.isSet(cell)) {
                neightbours.add(new Node(cell.getPosition(), 0, 0));
            }

            cell = model.getLeftCell(position);
            if(Assert.isSet(cell)) {
                neightbours.add(new Node(cell.getPosition(), 0, 0));
            }

            cell = model.getRightCell(position);
            if(Assert.isSet(cell)) {
                neightbours.add(new Node(cell.getPosition(), 0, 0));
            }

            for(Node neightbour : neightbours) {
                if(model.getCell(neightbour.getPosition()).isEmpty() || Assert.isNull(model.getCell(neightbour.getPosition()).getPerson())) {
                    if((closedList.contains(neightbour) && neightbour.cost < node.cost) || 
                    (openList.contains(neightbour) && neightbour.cost < node.cost)
                    ) {
                        continue;
                    } else {
                        neightbour.cost++;
                        neightbour.heuristic = neightbour.cost + (int)Distance.getDistance(neightbour.getPosition(), target.getPosition());
                        openList.add(neightbour);
                    }
                } 
            }
            closedList.add(node);
        }

        return null;    
    }
}