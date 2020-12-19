package api;

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
import helper.Distance;

import ui.GoalCell;
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
		
  	private boolean wakeUp() {
    	Position ball = null;
    	Person perso = null;
        Person perso2 = null;
        Position destination = null;

        //PREMIERE POSSIBILITE DE TOUR
    	if( !( this.haveIBall() ) && !( this.isRedPlayerHasBall() ) )  { //si la balle est au sol (aucune des deux équipes n'a la balle)

    		perso=this.getNearestPersonOfTheBall(this.model.getBall().getPosition()); //récupère la position du personnage à faire bouger
            destination=this.model.getBall().getPosition(); //récupère l'emplacement de la balle
    		MoveAction.move(this.model.getAvailableMoveRange(perso), this.model.getCell(perso.getPosition()), destination, this.model.getBall()); //Fait déplacer le joueur vers la balle

            if( !( this.haveIBall() ) && !( this.isRedPlayerHasBall() ) )  { //Si la balle est au sol après le déplacement du premier joueur

                perso2=this.getNearestPersonOfTheBall(this.model.getBall().getPosition(), this.perso.getTeamType(), perso); //Récupère le personnage le plus proche de la balle à l'exception de celui déjà déplacer
                MoveAction.move(this.model.getAvailableMoveRange(perso2), this.model.getCell(perso2.getPosition()), destination, this.model.getBall()); //déplace le deuxieme personnage près de la balle
            }

            else { //Si la balle a été ramassé par le premier personnage lors du premier coup

                perso2=this.getNearestPersonOfAPerson(this.person.getTeamType(), perso); //On récupère le personnage le plus proche du personnage proche de la balle
                destination = new Position(perso.getPosition().getX() + 1, perso.getPosition().getY()); //On récupère la destination derrière le premier personnage
                MoveAction.move(this.model.getAvailableMoveRange(perso2), this.model.getCell(perso2.getPosition()), destination, this.model.getBall()); //Déplace le deuxieme personnage derrière le premier personnage
            }
    	}

        //DEUXIEME POSSIBILITE DE TOUR
        else if (this.haveIBall()) { //Si je détiens la balle en début de tour.
            perso=this.whoHasBall().getPosition(); //Récupère la position du joueur ayant la balle
            if (this.canWinGame(perso)) { //Est-ce que je peux mettre la balle dans le but ennemi ?
                //(?) Déplacement vers endroit pour gagner
            }
            else {

                destination = new Position(perso.getPosition().getX() - 5, perso.getPosition().getY()); //destination vers le but ennemi
                MoveAction.move(this.model.getAvailableMoveRange(perso), this.model.getCell(perso.getPosition()), destination, this.model.getBall()); //Je déplace mon personnage ayant la balle vers le but ennemi

                if (rnd.nextBoolean()) { //50% de chance, soit:
                    perso2=getNearestOfMyGoals(); //Joueur le plus proche du but
                    destination = new Position(perso2.getPosition().getX() - 5, perso2.getPosition().getY()); //destination vers le but ennemi
                    MoveAction.move(this.model.getAvailableMoveRange(perso2), this.model.getCell(perso2.getPosition()), destination, this.model.getBall()); //Je déplace mon 2eme personnage vers le but ennemi
                } else { //soit:
                    perso2=this.getNearestPersonOfAPerson(this.person.getTeamType(), perso); //On récupère le personnage le plus proche du personnage proche de la balle
                    destination = new Position(perso.getPosition().getX() + 1, perso.getPosition().getY()); //On récupère la destination derrière le premier personnage
                    MoveAction.move(this.model.getAvailableMoveRange(perso2), this.model.getCell(perso2.getPosition()), destination, this.model.getBall()); //Déplace le deuxieme personnage derrière le premier personnage
                }
            }
        }
		
      	//TROISIEME POSSIBILITE DE TOUR
        else { //Si l'ennemi detient la balle
            Person ennemi = null;
            ennemi = whoHasBall();
            perso=getNearestPersonOfThePerson(TeamType.BLUE_TEAM, ennemi.getPosition());
            destination = new Position(ennemi.getPosition().getX() + 1, ennemi.getPosition().getY());
            MoveAction.move(this.model.getAvailableMoveRange(perso), this.model.getCell(perso.getPosition()), destination, this.model.getBall()); //Déplace le deuxieme personnage derrière le premier personnage
            if (canMakePlating(perso)) {
                PlatingAction.plating(this.model.getCell(perso.getPosition(), this.model.getCell(ennemi.getPosition()), this.model, null);
            }
            if( !( this.haveIBall() ) && !( this.isRedPlayerHasBall() ) )  { //si la balle est au sol (aucune des deux équipes n'a la balle)
                perso=this.getNearestPersonOfTheBall(this.model.getBall().getPosition()); //récupère la position du personnage à faire bouger
                destination=this.model.getBall().getPosition(); //récupère l'emplacement de la balle
                MoveAction.move(this.model.getAvailableMoveRange(perso), this.model.getCell(perso.getPosition()), destination, this.model.getBall()); //Fait déplacer le joueur vers la balle
            }
        }
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

     private Person whoHasBall() {
        for(Person person : this.persons) {
            if(person.hasBall()) {
                return person;
            }
        }

        return false;
      }
                                      
    /**
      * Verify if a person can win
      * @param person The concerned person
      * @return True if it is, else false
    **/
    private boolean canWinGame(Person person) {
        Position position = person.getPosition();
        double distance = 0.0;
        Cell currentCell = null;
        Position cellPosition = null;

        if(person.hasBall()) {
            for(int y = 0; y < BoardView.BOARD_HEIGHT; y++) {
                for(int x = 0; x < BoardView.BOARD_WIDTH; x++) {
                    cellPosition = new Position(x, y);
                    currentCell = this.model.getCell(cellPosition);
                    if(currentCell.isGoalCell()) {
                        GoalCell goalCell = (GoalCell)currentCell;
                        if(!person.isSameTeamAs(goalCell)) {
                            distance = Distance.getManhattanDistance(position, cellPosition);
                            if(distance <= person.getMovementRemaining()) {
                                return true;
                            }
                        }
                    }
                }
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

    
    private boolean isRedPlayerHasBall() {
		for(Person person : this.model.getRedPlayer().getPersons()) {
			if(person.hasBall()) {
				return true;
			}
		}
		return false;
    }
    

    /**
      * Get the person the nearest of the ball
      * @param position The position of the ball 
      * @return The person nearest of the ball (blue or red)
    **/
    private Person getNearestPersonOfTheBall(Position ballPosition) {
        Position personPosition;
        Person resultPerson = null;
        double manhattantDistance = 0.0; //initial value 
        double distance;

        for(Person person : this.persons) {
            personPosition = person.getPosition();
            distance = Distance.getManhattanDistance(personPosition, ballPosition);
            if(distance < manhattantDistance) {
                manhattantDistance = distance;
                resultPerson = person;
            }
        }
        
        return resultPerson;
    }

    private Person getNearestPersonOfAPerson(TeamType teamType, Person perso) {
        Position personPosition;
        Person resultPerson = null;
        Person[] availablePersons = (teamType.equals(TeamType.BLUE_TEAM)) ? this.persons : this.model.getRedPlayer().getPersons();
        
        double manhattantDistance = 0.0; //initial value 
        double distance;

        for(Person person : availablePersons) {
            personPosition = person.getPosition();
            distance = Distance.getManhattanDistance(personPosition, perso.getPosition());
            if(distance < manhattantDistance) {
                manhattantDistance = distance;
                resultPerson = person;
            }
        }

        return resultPerson;
    }
    /**
      * Get the person the nearest of the ball
      * @param position The position of the ball
      * @param teamType The team type of the research person 
      * @return The person nearest of the ball (according to his team type)
    **/
    private Person getNearestPersonOfTheBall(Position ballPosition, TemaType teamType) {
        Position personPosition;
        Person resultPerson = null;
        Person[] availablePersons = (teamType.equals(TeamType.BLUE_TEAM)) ? this.persons : this.model.getRedPlayer().getPersons();
        
        double manhattantDistance = 0.0; //initial value 
        double distance;

        for(Person person : availablePersons) {
            personPosition = person.getPosition();
            distance = Distance.getManhattanDistance(personPosition, ballPosition);
            if(distance < manhattantDistance) {
                manhattantDistance = distance;
                resultPerson = person;
            }
        }

        return resultPerson;
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

    /**
      * Get the smaller path between a cell and the ball
      * @return The smaller path betwenn a cell and the ball 
    **/
    private ArrayList<Cell> getSmallerDistanceToBall(Position position) {
        //call astar
        return null;
    }

    /**
      * Check if the bot can shot the ball
      * @param personWithBall The person with ball
      * @return The person who can shot 
    **/
    private boolean canShot(Person personWithBall) {
        for(Person person : this.persons) {
            if(person.getPosition().getX() > personWithBall.getPosition().getX()) {
                return false;
            }
        }

        return true;
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

*/