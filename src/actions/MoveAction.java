package actions;

import ui.Cell;

import model.Person;
import model.Ball;

import helper.Distance;
import helper.Position;

import java.util.ArrayList;
/**
  * The class <code>MoveAction</code> is the representation of the move's action
  * @version 1.0
  * @author Dorian Terbah 
**/

public class MoveAction {
    
    public MoveAction() {

    }

    /**
      * Move a person in an other cell
      * @param availableCells Available cells
      * @param currentCell The current cell
      * @param targetCell The target cell 
      * @param ball The ball
    **/
    public static final void move(ArrayList<Cell> availableCells, Cell currentCell, Cell targetCell, Ball ball) {
        Person person = currentCell.getPerson();
        Position currentPosition = currentCell.getPosition();
        Position targetPosition = targetCell.getPosition();

        if(availableCells.contains(targetCell)) {
            if(person.isValidPosition(targetCell.getPosition())) {
                //set the hasBall's state to the person
                if(targetCell.hasBall()) {
                    targetCell.toggleHasBall();
                    person.setHasBall(true);
                    ball.setPosition(targetCell.getPosition());
                }

                person.move(targetPosition);
                currentCell.setPerson(null);
                targetCell.setPerson(person);
                int remainingMovements = person.getMovementRemaining();
                person.setMovementRemaining(remainingMovements - (int)Distance.getDistance(currentCell.getPosition(), targetCell.getPosition()));
            }
        }
    }
}