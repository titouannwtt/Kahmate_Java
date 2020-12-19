package model;

import ui.BoardView;

import helper.Position;
import helper.Console;
import helper.Distance;

import model.PersonState;

import enums.TeamType;

import ui.Cell;
import ui.GoalCell;
/**
  * The class <code>Person</code> is the model's person
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class Person implements java.io.Serializable {

    /**
      * Constant used to get the base path for the images 
    **/
    protected static final String BASE_PATH = "./rsc/images/";

    /**
      * Constant used to get the blue placement's limit 
    **/
    public static final int BLUE_INITIAL_PLACEMENT_LIMIT = 10;

    /**
      * Constant used to represent get an area of the initial placement of the persons 
    **/
    public static final int OFFSET_PLACEMENT = 1;
    
    public static final int RED_INITIAL_PLACEMENT_LIMIT = 1;

    /**
      * The number of movements that the player  still has
    **/
    protected int movementRemaining;

    /**
      * The agressive bonue of the person 
    **/
    protected int agressiveBonus;

    /**
      * The defensive bonus of the person 
    **/
    protected int defensiveBonus;

    /**
      * The max number of movements of the person 
    **/
    protected int initialMovements;

    /**
      * An objet to gather all states about the person  
    **/
    protected PersonState personState;

	/**
	  * The position of the person on the board 
	**/
    protected Position position;

	/**
	  * The current representation of the person 
	**/
	protected String representation;
	
	/**
	  * The representation of the person when he is inactive 
	**/
	protected String deadRepresentation;
	
	/**
	  * The representation of the person when he has the ball
	**/
	protected String hasBallRepresentation;
	
	/**
	  * The normal representation of the person 
	**/
    protected String normalRepresentation;

	/**
	  * The team type of the person 
	**/
    protected TeamType teamType;

	/**
	 * Constructor of the person
	 * @param position The position of the person in the board
	 * @param teamType The team type of the person
	 */
    public Person(Position position, TeamType teamType) {
        this.position = position;
        this.teamType = teamType;
        this.personState = new PersonState(true, false, false);
        this.representation = BASE_PATH; //initial value
	}
	
	/**
	  * Constructor of the person for the clonage
	  * @param person The person who is the source of the clonage 
	**/
	public Person(Person person) {
		this.personState = new PersonState(person.personState);
		this.position = new Position(person.position);
		this.teamType = person.teamType;
		this.representation = person.representation;
		this.movementRemaining = person.movementRemaining;
		this.agressiveBonus = person.agressiveBonus;
        this.initialMovements = person.initialMovements;
        this.defensiveBonus = person.defensiveBonus;
        this.deadRepresentation = person.deadRepresentation;
        this.hasBallRepresentation = person.hasBallRepresentation;
        this.normalRepresentation = person.normalRepresentation;
	}

	public boolean equals(Person person) {
		return ((this.representation.equals(person.representation)) && (this.teamType.equals(person.teamType)) && (this.movementRemaining == person.movementRemaining));
	}
    /*********************************      
     *************Getter**************
    *********************************/
    
    /**
      * Get the move's state of the person
      * @return The move's stateof the person  
    **/
    public boolean canMove() {
        return this.personState.canMove();
    }
    
    /**
      * Get the agressive value bonus
      * @return The agressive value bonus  
    **/
    public int getAgressiveBonus() {
        return this.agressiveBonus;
    }

    /**
      * Get the defensive value bonus
      * @return The defensive value bonus  
    **/

    public int getDefensiveBonus() {
        return this.defensiveBonus;
    }

    /**
      * Get the number of remaining movement of the persni
      * @return The remaining movement of the person
    **/
    public int getMovementRemaining() {
        return this.movementRemaining;
    }

    /**
      * Get the position of the person in the board
      * @return The position of the person in the board
    **/
    public Position getPosition() {
        return this.position;
    }

    /**
      * Get the representation of the person 
      * @return The representation of the person 
    **/
    public String getRepresentation() {
      return this.representation;
    }

    /**
      * Get the teamType of the person 
      * @return The teamType of the person 
    **/
    public TeamType getTeamType() {
        return this.teamType;
    }

    /**
      * Get the ball's state
      * @return True if the person has the ball, else false  
    **/
    public boolean hasBall() {
        return this.personState.hasBall();
    }

    /**
      * Get the inactive's state
      * @return True if the person is inactive, else false  
    **/
    public boolean isInactive() {
        return this.personState.isInactive();
	}    
	
	/**
      * Get if the person if a red person
      * @return True if it is, else false  
    **/
    public boolean isRedPerson() {
        return this.teamType.equals(TeamType.RED_TEAM);
    } 

    /*********************************      
     *************Setter**************
    *********************************/
    
    /**
      * Set the state of the movement
      * @param canMove The new state  
    **/
    public void setCanMove(boolean canMove) {
        this.personState.setCanMove(canMove);
    }

    /**
      * Set the representation of the person
      * @param representation New representation 
    **/
    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    /**
      * Set the state of the person if he has the ball
      * @param hasBall The new state
    **/
    public void setHasBall(boolean hasBall) {
  		this.personState.setHasBall(hasBall);
        this.refreshAppearance();
    }

    /**
      * Set the state of the inactivity of the person
      * @param isInactive The new state
    **/
    public void setIsInactive(boolean isInactive) {
        this.personState.setIsInactive(isInactive);
        this.refreshAppearance();
    }

    /**
      * Set the number of the remaining movements
      * @param movements The new count of movements
    **/
    public void setMovementRemaining(int movements) {
        this.movementRemaining = movements;
    }

    /**
      * Set a new position to the person
      * @param position The new position 
    **/
    public void setPosition(Position position) {
        this.position = position;
    }

    /*********************************      
     *********Toggles methods*********
    *********************************/

    //the toggles methods can simplify some settings
    
    /**
      * Toggle the canMove's state
    **/
    public void toggleCanMove() {
        this.personState.toggleCanMove();
    }

    /**
      * Toggle the isInactive's state
    **/
    public void toggleIsInactive() {
        this.personState.toggleIsInactive();
    }

    /**
      * Toggle the hasBall's state
    **/
    public void toggleHasBall() {
        this.personState.toggleHasBall();
    }

    /**
      * Move the person
      * @param cell The cell to move
    **/ 
    public void move(Position position) {
        this.position = position;
	}
	
	/**
	  * Check if the futur position is correct
	  * @param position The futur position
	  * @return True if it is, else false 
	**/
	public boolean isCorrectInitialPlacement(Cell cell) {
		Position position = cell.getPosition();
		int x = position.getX();
		int y = position.getY();
		
		if(this.isRedPerson()) {
			if((x != RED_INITIAL_PLACEMENT_LIMIT) && (x != (RED_INITIAL_PLACEMENT_LIMIT + OFFSET_PLACEMENT))) {
				return false;
			} 
		} else {
			if((x != BLUE_INITIAL_PLACEMENT_LIMIT) && (x != (BLUE_INITIAL_PLACEMENT_LIMIT + OFFSET_PLACEMENT))) {
				return false;
			}
		}

		return cell.isEmpty();
	}

	/**
	  * Check if the new position is in diagonal
	  * @param position The futur position
	  * @return True if it's a diagonal position, else false 
	**/
	public boolean isDiagonalPosition(Position position) {
		int x = this.position.getX();
        int y = this.position.getY();
        int i = 0;

		for(int yy = 0; y < BoardView.BOARD_HEIGHT; y++) {

            for(int xx = 0; xx < BoardView.BOARD_WIDTH; xx++) {
                if(((xx + yy) == (x + y)) || ((xx + yy - i * 2) == (x + y))) {
                    if(position.equals(new Position(xx, yy))) {
                        return true;
                    }
                }
            }
            i++;
        }

		return false;
	}

	/**
	  * Check if the position is valid  (horizontal or vertical)
	  * @param position The futur position
	  * @return True is it valid, else false 
	**/
	public boolean isValidPosition(Position position) {
        return ((this.position.getX() == position.getX()) || (this.position.getY() == position.getY()));
    }
    
    /**
      * Check if the person is in same team with other person
      * @param True if that's it, else false
    **/
    public boolean isSameTeamAs(Person person) {
        return this.teamType.equals(person.getTeamType());
    }

    /**
      * Check if the person is in the same team with a goal cell
      * @param True if it is, else false 
    **/

    public boolean isSameTeamAs(GoalCell cell) {
        return this.teamType.equals(cell.getTeamType()); 
    }

    /**
      * Give the ball to an other person
      * @param target The new receiver of the ball
    **/ 
    public void pass(Person target) {

    }

    /**
      * Score a try
    **/
    public void scoreTry() {

    }

    /*********************************      
     *********Collide methods*********
    *********************************/
    /**
      * Check if the person collides with the ball
      * @param True if that's it, else false
    **/     
    public boolean collideBall(Ball ball) {
      return this.position.equals(ball.getPosition());
    }

    /**
      * Check if the person colludes with a person that's same team
      * @param True if that's it, else false
    **/   
    public boolean collideWithSameTeam(Person person, Position position) {
        if(person.getPosition().equals(position)) {
            
        }

        return true;
	}
	
	 /*********************************      
     **********Refresh methods*********
	*********************************/
	
	/**
	  * Refresh movements 
	**/
	public void refreshMovements() {
		this.movementRemaining = this.initialMovements;
  }
  
  /**
    * Check the current state of the person and then refresh the person's image to match with it
  **/
  private void refreshAppearance() {
    if(this.hasBall()) {
        this.representation = this.hasBallRepresentation;
    } else if(this.isInactive()) {
        this.representation = this.deadRepresentation;
    } else {
        this.representation = this.normalRepresentation;
    }   

  }
}