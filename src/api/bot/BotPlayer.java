package api.bot;

import model.BoardModel;
import model.Ball;
import model.Hand;
import model.Card;
import model.Person;
import model.Player;
import model.PlayerState;

import enums.TeamType;

import helper.Position;
import helper.Assert;

import ui.Cell;
import ui.BoardView;

import java.util.ArrayList;

/**
  * The class <code>BotPlayer</code> represents a bot player
  * @version 1.0
  * @author Dorian Terbah
**/
public class BotPlayer extends Player {

    private BoardModel model;

    private Player opponent;

    public BotPlayer(Person[] persons, PlayerState playerState, TeamType teamType, BoardModel model) {
        super(persons, playerState, teamType);
        this.model = model;
    }

    /**
      * Get the cell which is behind the possessor of the ball
      * @return The cell which is behind the possessor of the ball 
    **/
    private Cell getCellBehindPersonWithBall() {

        //fetch to the red persons
        for(Person person : this.opponent.getPersons()) {
            if(person.hasBall()) {
                return this.model.getCell(person.getPosition());
            }
        }

        //fetch my persons
        for(Person person : this.persons) {
            if(person.hasBall()) {
                return this.model.getCell(person.getPosition());
            }
        }
        return null;
    }

    /**
      * Verify if a person can win
      * @param person The concerned person
      * @return True if it is, else false
    **/
    private boolean canWinGame(Person person) {
        if(person.hasBall()) {
            for(Cell cell : this.model.getAvailableMoveRange(person)) {
                if(cell.isGoalCell()) return true;
            }
        }
        return false;
    }

    /**
      * Verifiy if the player can make a plate
      * @param person The concerned person 
      * @return True if it is, else false 
    **/
    private boolean canMakePlating(Person person) {
        return Assert.isSet(this.model.isAvailablePlatingCell(person));
    }

    /**
      * Verify if the player can make a plate and return the concerned person
      * @return The first available person 
    **/
    private Person canMakePlating() {
        for(Person person : this.persons) {
            if(Assert.isSet(this.model.isAvailablePlatingCell(person))) {
                return person;
            }
        }
        return null;
    }

    /**
      * Verify if the player has the ball
      * @return True if it is, else false 
    **/
    private boolean haveIBall() {
        for(Person person : this.persons) {
            if(person.hasBall()) {
                return true;
            }
        }

        return false;
    }

    /**
      * Get the person the nearest of the ball
      * @param position The position of the ball initial and after position around her
      * @return The person nearest of the ball
    **/

    //A REVOIR//
    private Person getNearestPersonOfTheBall(Position position) {
        Cell cell = this.model.getTopCell(position);
        if(Assert.isSet(cell)) {
            if(Assert.isSet(cell.getPerson())) {
                return cell.getPerson();
            } else {
                return this.getNearestPersonOfTheBall(cell.getPosition());
            }
        }

        cell = this.model.getBottomCell(position);
        if(Assert.isSet(cell)) {
            if(Assert.isSet(cell.getPerson())) {
                return cell.getPerson();
            } else {
                return this.getNearestPersonOfTheBall(cell.getPosition());
            }
        }

        cell = this.model.getLeftCell(position);
        if(Assert.isSet(cell)) {
            if(Assert.isSet(cell.getPerson())) {
                return cell.getPerson();
            } else {
                return this.getNearestPersonOfTheBall(cell.getPosition());
            }
        }

        cell = this.model.getRightCell(position);
        if(Assert.isSet(cell)) {
            if(Assert.isSet(cell.getPerson())) {
                return cell.getPerson();
            } else {
                return this.getNearestPersonOfTheBall(cell.getPosition());
            }
        }
        return null;
    }

    /**
      * Get the person the nearest of his goals
      * @return The person the nearest of his goals 
    **/
    private Person getNearestOfMyGoals() {
        for(int x = (BoardView.BOARD_WIDTH - 1); x > 0; x--) {
            for(int y = 0; y < BoardView.BOARD_HEIGHT; y++) {
                Position position = new Position(x, y);
                Cell cell = this.model.getCell(position);
                if(Assert.isSet(cell.getPerson())) {
                    return cell.getPerson();
                }
            }
        }
        return null;
    }

    /**
      * Check if the bot can make a plating actions
      * @return True if it is, else false 
    **/
    public boolean canMakePlatingAction(Person person) {
        return this.model.isAvailablePlatingCell(person);
    }   

    /**
      * Check if the bot can make a force action
      * @param person The concerned personfor the possible force action
      * @return True if it is, else false  
    **/
    private boolean canMakeForceAction(Person person) {
        for(Person redPerson : this.model.getRedPlayer().getPersons()) {
            if(!person.isSameTeamAs(redPerson) && person.hasBall() 
            && person.getMovementRemaining() >= 2 && !redPerson.isInactive()) {
                return true;
            }
        }
        return false;
    }

    /**
      * Check if the bot can make a force action
      * @return The person that can make force action 
    **/
    private Person canMakeForceAction() {
        for(Person redPerson : this.model.getRedPlayer().getPersons()) {
            if(redPerson.hasBall()) {
                for(Person bluePerson : this.persons) {
                    if(!bluePerson.isSameTeamAs(redPerson) && !bluePerson.isInactive()
                    && bluePerson.getMovementRemaining() >= 2 && !redPerson.isInactive()) {
                        return bluePerson;
                    }
                }
            }
        }
        return null;
    }

}
//wakeUp
/*
model : BoardModel
comportement : ?
opponent : Player
save ; BoardSave
cards : Card[]


*/

/*
public void wakeUp() {
    boolean iAmPlaying = true;
    //part Titouann
    while(iAmPlaying) {
        
    } 
        
    //part Test
} 

private boolean canShot() {

}

private Person getMostNearOfPerson() {
    //dikstra ou a*
    //most near of you
}

private void createSave() {

}

private ArrayList<Position> getSmallerDistanceForBall() {
    //dikstra ou a*
}
*/