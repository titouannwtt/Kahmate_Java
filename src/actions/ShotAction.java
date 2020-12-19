package actions;

import ui.Cell;

import model.Ball;
import model.Person;

import helper.Position;
import helper.Assert;

import ui.popup.ConfirmShotPopUp;

import java.util.ArrayList;

/**
  * The class <code>ShotAction</code> is the representation of the shot's action
  * @version 1.0
  * @author Dorian Terbah 
**/

public class ShotAction {
    
    public ShotAction() {

    }
    
    /**
      * Verify if it is a good shot
      * @param cells Available cells
      * @param currentCell The current cell
      * @param targetCell Target cell
      * @param ball The ball
      * @return True if it is a good shot, else false 
    **/
    public static final boolean isGoodShot(ArrayList<Cell> cells, Cell currentCell, Cell targetCell, Ball ball) {
        Person person = currentCell.getPerson();
        if(cells.contains(targetCell) && person.hasBall()) {
            if(!ConfirmShotPopUp.confirmShot()) {
                return false;
            }

            person.setHasBall(false);
            currentCell.setPerson(person);
            targetCell.setHasBall(true);
            return true;
        }

        return false;
    }
}