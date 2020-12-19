package model;

import undo.UndoRedo;
import undo.BoardSave;

import model.Player;

import ui.BoardView;
import ui.Cell;
import ui.GoalCell;

import enums.TeamType;
import enums.BoardState;

import model.Person;
import model.SmartPerson;
import model.OrdinaryPerson;
import model.BeefyPerson;
import model.FastPerson;
import model.HardPerson;
import model.Ball;

import helper.Assert;
import helper.Position;
import helper.Distance;
import helper.RandomCard;
import helper.ModelBoolean;
import helper.CopyObject;

import actions.MoveAction;
import actions.PassAction;
import actions.PlatingAction;
import actions.ForceAction;
import actions.ShotAction;

import java.util.ArrayList;
/**
  * The class <code>BoardModel</code> is the board's model
  * @version 1.0
  * @author Lucas Augusto 
**/

public class BoardModel implements java.io.Serializable {

	/**
	  * The representation of the blue player 
	**/
	private Player bluePlayer;

	/**
	  * The representation of the red player 
	**/
	private Player redPlayer;
	
	/**
	  * The state of the current player who is playing 
	**/
	private ModelBoolean isRedPlayerPlaying;

	/**
	  * The current selected cell 
	**/
	private Cell selectedCell;

	/**
	  * The current state about the advancement of the game
	**/
	private BoardState state;

	/**
	  * The representation of the view (grapical) 
	**/
	private BoardView boardView;

	/**
	  * The representation of the ball on the board 
	**/
	private Ball ball;

	/**
	  * Representation of all cells on the board 
	**/
	private Cell[][] cells;

	/**
	 * Constructor of the BoardModel
	 * @param isBot A boolean to describe if the second player is a bot
	 * @param bluePersons The person for the blue player
	 * @param redPersons The person for the red player
	 * @param boardView The board view of the game
	 * @param cells All of the cells on the board
	 */
	public BoardModel(boolean isBot, Person[] bluePersons, Person[] redPersons, BoardView boardView, Cell[][] cells) {
		//refresh undo/redo
		UndoRedo.instance().refreshStacks();
		this.redPlayer = new Player(redPersons, new PlayerState(true, false, false), TeamType.RED_TEAM); //the left side
		if(!isBot) this.bluePlayer = new Player(bluePersons, new PlayerState(false, false, false), TeamType.BLUE_TEAM); //the right side
		//else this.bluePlayer = BotPlayer();
		
		this.isRedPlayerPlaying = new ModelBoolean(false); //initial value

		this.selectedCell = null; //initial value
		this.state = BoardState.PLAYER_PLACEMENTS_STATE; //initial value

		this.boardView = boardView;

		this.cells = cells;
	}

	/*********************************      
    **************Getter**************
    *********************************/

    /**
      * Get if the red player is playing
      * @return True if the red player is playing, else false
	**/
	public boolean isRedPlayerPlaying() {
		return this.isRedPlayerPlaying.getBool();
	}
	
	/**
      * Get model boolean
      * @return model boolean
	**/
	public ModelBoolean getModelBoolean() {
		return this.isRedPlayerPlaying;
	}

	/**
      * Get if the game has been began
      * @return True if the game has been began, else false
	**/
	public boolean inProgress() {
		return this.state.equals(BoardState.IN_GAME_STATE);
	}

	/**
      * Get the red player
      * @return The red player
	**/
	public Player getRedPlayer() {
		return this.redPlayer;
	}

	/**
      * Get the blue player
      * @return The blue player
	**/
	public Player getBluePlayer() {
		return this.bluePlayer;
	}

	/**
      * Get the selected cell
      * @return The selected cell
	**/
	public Cell getSelectedCell() {
		return this.selectedCell;
	}

	/**
	  * Get the state of the board
	  * @return The state of the board 
	**/
	public BoardState getState() {
		return this.state;
	}

	/**
	  * Get the reference of the ball
	  * @return The reference of the ball
	**/
	public Ball getBall() {
		return this.ball;
	}

	/*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the isRedPlayerPlaying's state
      * @param isRedPlayerPlaying The new state  
	**/
	public void setIsRedPlayerPlaying(boolean isRedPlayerPlaying) {
		/*
		if(!isRedPlayerPlaying) {
			this.bluePlayer.wakeUp();
		} 
		*/
		this.isRedPlayerPlaying.setBool(isRedPlayerPlaying);
	}

	/**
      * Set the selected cell
      * @param selectedCell The selected cell  
	**/
	public void setCellSelected(Cell cell) {
		//clear the board
		this.boardView.clearBoard();
		
		//change background color of the previous selectedCell if it exists
		if(Assert.isSet(this.selectedCell)) {
			this.selectedCell.setIsSelected(false);
		}
		
		//first time
		if(Assert.isNull(this.selectedCell)) {
			this.selectedCell = cell;
		}

		//good
		if(this.state.equals(BoardState.PLAYER_PLACEMENTS_STATE)) { 
			if(Assert.isSet(this.selectedCell) && (Assert.isSet(this.selectedCell.getPerson()))) {
				Person person = this.selectedCell.getPerson();
				if(person.isCorrectInitialPlacement(cell)) {
					this.selectedCell.setPerson(null); //Remove person
					this.selectedCell.setIsSelected(false);
					person.move(cell.getPosition()); //update the position of the person

					cell.setPerson(person);
				}
			} 
		} else if(this.state.equals(BoardState.IN_GAME_STATE)) {
			//show the movement area of the player
			Player currentPlayer = (this.isRedPlayerPlaying()) ? this.redPlayer : this.bluePlayer;
			Person currentPerson = this.selectedCell.getPerson();
			if(Assert.isSet(currentPerson) && currentPlayer.hasPerson(currentPerson)) {
				
				//eventually make pass or other actions
				Person targetPerson = cell.getPerson();
				if(Assert.isSet(targetPerson)) {
					//display the available cell for the move
					
					if(currentPlayer.hasPerson(targetPerson) && !targetPerson.isInactive()) {
						this.boardView.showMovementArea(this.getAvailableMoveRange(targetPerson));
					}

					if(currentPerson.isSameTeamAs(targetPerson) && !targetPerson.isInactive()) {
						//the selectedPerson has the ball ?
						if(currentPerson.hasBall()) {
							UndoRedo.instance().push(this.bluePlayer, this.redPlayer, this.ball, this.isRedPlayerPlaying());
							PassAction.pass(this.getAvailablePassingCell(currentPerson), this.selectedCell, cell, this.ball, this.boardView, this);
						} 		
					} else {
						if(!currentPerson.isSameTeamAs(targetPerson) && targetPerson.hasBall() && !targetPerson.isInactive() && !currentPerson.isInactive()) {
							//slame
							if(currentPerson.getMovementRemaining() > 0 && this.isAvailablePlatingCell(currentPerson)) {
								UndoRedo.instance().push(this.bluePlayer, this.redPlayer, this.ball, this.isRedPlayerPlaying());
								PlatingAction.plating(this.selectedCell, cell, this, this.boardView);
							}
						}  else if(!currentPerson.isSameTeamAs(targetPerson) && currentPerson.hasBall() 
								   && currentPerson.getMovementRemaining() >= 2 && !currentPerson.isInactive()
								   && this.isAvailablePlatingCell(targetPerson)	   
						) {
							UndoRedo.instance().push(this.bluePlayer, this.redPlayer, this.ball, this.isRedPlayerPlaying());
							ForceAction.force(this.selectedCell, cell, this, this.boardView);
						}
					}
				} else {
					if(currentPlayer.canAddUsedPerson(currentPerson) && !currentPerson.isInactive()) {
						UndoRedo.instance().push(this.bluePlayer, this.redPlayer, this.ball, this.isRedPlayerPlaying());
						currentPlayer.addUsedPerson(currentPerson);
						if(currentPlayer.canMakeShot(currentPerson)) {
							if(!ShotAction.isGoodShot(this.getShotRange(currentPerson), this.selectedCell, cell, this.ball)) {
								MoveAction.move(this.getAvailableMoveRange(currentPerson), this.selectedCell, cell, this.ball);
							}
						} else {
							MoveAction.move(this.getAvailableMoveRange(currentPerson), this.selectedCell, cell, this.ball);
						}

						if(currentPerson.hasBall() && this.boardView.getCell(currentPerson.getPosition()).isGoalCell()) {
							this.boardView.displayEndMenu(this.isRedPlayerPlaying());
						}
					}
				}
			}
			
			/*
			if(Assert.isSet(cell.getPerson())) { //player selected
				Person person = cell.getPerson();
				Person currentPerson = null;

				Player currentPlayer = (this.isRedPlayerPlaying()) ? this.redPlayer : this.bluePlayer;

				if((this.isRedPlayerPlaying() && person.isRedPerson()) || 
				   (!this.isRedPlayerPlaying() && !person.isRedPerson())
				) {
					if(Assert.isSet(this.selectedCell) && Assert.isSet(this.selectedCell.getPerson())) {
						currentPerson = this.selectedCell.getPerson();	
						if(currentPerson.hasBall()) {
							PassAction.pass(this.getAvailablePassingCell(currentPerson), this.selectedCell, cell, this.ball);
						}
					}
					
					this.boardView.showMovementArea(this.getAvailableMoveRange(person));
				} 
			} else if(Assert.isSet(this.selectedCell) && Assert.isSet(this.selectedCell.getPerson())) { //other cell selected but the previous has a player
				Person person = this.selectedCell.getPerson();
				if((this.isRedPlayerPlaying() && Assert.isSet(cell.getPerson()) && !cell.getPerson().isRedPerson()) ||
				   (!this.isRedPlayerPlaying() && Assert.isSet(cell.getPerson()) && cell.getPerson().isRedPerson()) 
				) {
					//faire une action, plaquage ou interception
				} else if(cell.isEmpty() || cell.hasBall()) {
					Player currentPlayer = (this.isRedPlayerPlaying()) ? this.redPlayer : this.bluePlayer;
					if((currentPlayer.isRedPlayer() && person.isRedPerson()) ||
						(!currentPlayer.isRedPlayer() && !person.isRedPerson())
					) {
						if(currentPlayer.addUsedPerson(this.selectedCell.getPerson())) {
							MoveAction.move(this.getAvailableMoveRange(person), this.selectedCell, cell, this.ball);
						}
					}
				} 
			}*/
		}

		this.selectedCell = cell;
		this.selectedCell.setIsSelected(true);
	}



	/**
	  * Select the first player
	  * @param isRedPlayerPlaying The first player 
	**/
	public void selectInitialPlayer(boolean isRedPlayerPlaying) {
		this.isRedPlayerPlaying.setBool(isRedPlayerPlaying);
		Player firstPlayer = (this.isRedPlayerPlaying.getBool()) ? this.redPlayer : this.bluePlayer;
		Card randomCard = RandomCard.getRandomCard(firstPlayer.getHand());
		Cell ballCell = this.boardView.getCell(new Position(BoardView.X_CENTER, randomCard.getStrenght()));
		ballCell.setHasBall(true);

		this.ball = new Ball(ballCell.getPosition());
		this.boardView.changeContainer(BoardState.IN_GAME_STATE); //change state
	}

	/**
	  * Refresh the board with a version 
	  * @param save The save
	**/
	public void setVersion(BoardSave save) {
		//do update
		if(Assert.isSet(save)) {
			this.bluePlayer.getHand().setCards(save.getBlueCards());
			this.redPlayer.getHand().setCards(save.getRedCards());
			Person[] bluePersons = new Person[Player.MAX_PERSONS];
			Person[] redPersons = new Person[Player.MAX_PERSONS];
			this.ball = save.getBall();
			int blueIndex = 0;
			int redIndex = 0;

			//remove model to cells
			for(int y = 0; y < BoardView.BOARD_HEIGHT; y++) {
				for(int x = 0; x < BoardView.BOARD_WIDTH; x++) {
					this.boardView.getCell(new Position(x, y)).setPerson(null);
				}
			}

			for(Person person : save.getPersons()) {
				if(person.isRedPerson()) {
					redPersons[redIndex] = person;
					redIndex++;
				} else {
					bluePersons[blueIndex] = person;
					blueIndex++;
				}

				this.boardView.getCell(person.getPosition()).setPerson(person);
			}

			//to do 
			this.bluePlayer.setPersons(bluePersons);
			this.redPlayer.setPersons(redPersons);
			this.setIsRedPlayerPlaying(save.isRedPlayerPlaying());

			this.bluePlayer.setUsedPersons(save.getBlueUsedPersons());
			this.redPlayer.setUsedPersons(save.getRedUsedPersons());

			//update ball
			if(Assert.isSet(this.ball.getPossessor())) {
				this.ball.getPossessor().setHasBall(true);
			} else {
				this.boardView.getCell(this.ball.getPosition()).setHasBall(true);
			}

			this.boardView.setRound(this.isRedPlayerPlaying());
		}

	}

	/**
	  *	Set a new state
	  * @param state The new state 
	**/
	public void setState(BoardState state) {
		this.state = state;
	}

	/*********************************      
     *********Toggles methods*********
	*********************************/
	
	/**
      * Toggle the isRedPlayerPlaying's state
	**/
	public void toggleIsRedPlayerPlaying() {
		this.isRedPlayerPlaying.toggleBool();
	}

	/*********************************      
     *********Ranges methods**********
	*********************************/

	/**
	  * Get available move range for a person
	  * @param person The person concerned
	  * @return All move available cell  
	**/
	public ArrayList<Cell> getAvailableMoveRange(Person person) {
		Position position = person.getPosition();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		this.addCellMovement(cells, this.boardView.getCell(position), person.getMovementRemaining(), person);
		return cells;
	}

	/**
	  * Get the shot range for a person
	  * @param person The person concerned
	  * @return All shot available cells 
	**/
	public ArrayList<Cell> getShotRange(Person person) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		Position personPosition = person.getPosition();

		for(int y = 0; y < BoardView.BOARD_HEIGHT; y++) {
			for(int x = 0; x < BoardView.BOARD_WIDTH; x++) {
				if((person.isRedPerson() && x > personPosition.getX()) || (!person.isRedPerson() && x < personPosition.getX())) {
					Position position = new Position(x, y);
					if(Distance.getDistance(personPosition, position) <= 3) {
						Cell cell = this.boardView.getCell(position);
						if(Assert.isNull(cell.getPerson()) && !cell.isGoalCell()) {
							cells.add(cell);
						}
					}
				}
			}
		}
		return cells;	
	}

	/**
	  * Add a cell to the movements cell
	  * @param cells The current cells 
	  * @param cell The current cell being in process
	  * @param remainingMovements Remaining movements 
	**/
	private void addCellMovement(ArrayList<Cell> cells, Cell cell, int remainingMovements, Person person) {
		int movements = remainingMovements;
		Cell currentCell;
		Position position = cell.getPosition();

		if(movements > 0) {
			//cell to the top
			currentCell = this.boardView.getTopCell(position);
			if(Assert.isSet(currentCell) && (currentCell.isEmpty() || currentCell.hasBall())) {
				if(currentCell.isGoalCell() && person.hasBall()) {
					GoalCell goalCell = (GoalCell)currentCell;
					if(!person.isSameTeamAs(goalCell)) {
						cells.add(currentCell);
						this.addCellMovement(cells, currentCell, movements - 1, person);					
					}
				} else if(!currentCell.isGoalCell()) {
					cells.add(currentCell);
					this.addCellMovement(cells, currentCell, movements - 1, person);
				}
			}

			//cell to the bottom
			currentCell = this.boardView.getBottomCell(position);
			if(Assert.isSet(currentCell) && (currentCell.isEmpty() || currentCell.hasBall())) {
				if(currentCell.isGoalCell() && person.hasBall()) {
					GoalCell goalCell = (GoalCell)currentCell;
					if(!person.isSameTeamAs(goalCell)) {
						cells.add(currentCell);
						this.addCellMovement(cells, currentCell, movements - 1, person);					
					}
				} else if(!currentCell.isGoalCell()) {
					cells.add(currentCell);
					this.addCellMovement(cells, currentCell, movements - 1, person);
				}
			}

			//cell to the right
			currentCell = this.boardView.getRightCell(position);
			if(Assert.isSet(currentCell) && (currentCell.isEmpty() || currentCell.hasBall())) {
				if(currentCell.isGoalCell() && person.hasBall()) {
					GoalCell goalCell = (GoalCell)currentCell;
					if(!person.isSameTeamAs(goalCell)) {
						cells.add(currentCell);
						this.addCellMovement(cells, currentCell, movements - 1, person);					
					}
				} else if(!currentCell.isGoalCell()) {
					cells.add(currentCell);
					this.addCellMovement(cells, currentCell, movements - 1, person);
				}
			}

			//cell to the left
			currentCell = this.boardView.getLeftCell(position);
			if(Assert.isSet(currentCell) && (currentCell.isEmpty() || currentCell.hasBall())) {
				if(currentCell.isGoalCell() && person.hasBall()) {
					GoalCell goalCell = (GoalCell)currentCell;
					if(!person.isSameTeamAs(goalCell)) {
						cells.add(currentCell);
						this.addCellMovement(cells, currentCell, movements - 1, person);					
					}
				} else if(!currentCell.isGoalCell()) {
					cells.add(currentCell);
					this.addCellMovement(cells, currentCell, movements - 1, person);
				}
			}
		}
	}

	/** 
	  * Get available range for the plating
	  * @param person The person concerned
	  * @return All available cells for plating   
	**/
	public boolean isAvailablePlatingCell(Person person) {
		Position position = person.getPosition();
		Cell cell = this.boardView.getTopCell(position);

		if(Assert.isSet(cell) && 					
			!cell.isEmpty() && 
			cell.getPerson().hasBall() && 
			!cell.getPerson().isSameTeamAs(person)
		) {
			return true;
		}

		cell = this.boardView.getBottomCell(position);
		if(Assert.isSet(cell) && 					
			!cell.isEmpty() && 
			cell.getPerson().hasBall() && 
			!cell.getPerson().isSameTeamAs(person)
		) {
			return true;
		}

		cell = this.boardView.getRightCell(position);  
		if(Assert.isSet(cell) && 					
			!cell.isEmpty() && 
			cell.getPerson().hasBall() && 
			!cell.getPerson().isSameTeamAs(person)  
		) {
			return true;
		}

		cell = this.boardView.getLeftCell(position);   
		if(Assert.isSet(cell) && 					
			!cell.isEmpty() && 
			cell.getPerson().hasBall() && 
			!cell.getPerson().isSameTeamAs(person)
		) {
			return true;
		}


		return false;
	}

	/** 
	  * Get available range for the passing
	  * @param person The person concerned
	  * @return All available cells for passing   
	**/
	public ArrayList<Cell> getAvailablePassingCell(Person person) {
		
		ArrayList<Cell> cells = new ArrayList<Cell>();
		Position position = person.getPosition();
		// Player behind a blue player has its position.x smaller, when he is behind a red player it has a greater

		int behind = (person.getTeamType().equals(TeamType.BLUE_TEAM)) ? 1 : -1;

		for( int x = 1 ; x < 3 ; x++ ) {

			for( int y = -2 ; y < 3 ; y++ ) {
				Position pos = new Position( position.getX() + ( behind * x ), position.getY() + y );
				Cell cell = this.boardView.getCell(pos);
				if(Assert.isSet(cell) && !cell.isEmpty()){
					if(cell.getPerson().isSameTeamAs(person)) cells.add(cell);
				}
			}
		}
		return cells;
	}

	/**
	  * Get a cell according to her position
	  * @param position The position of the cell
	  * @return The cell according to her position 
	**/
	public Cell getCell(Position position) {
		return this.cells[position.getY()][position.getX()];
	}

	/**
	  * Get the cell to the top of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	 */
	public Cell getTopCell(Position position) {
		return this.boardView.getTopCell(position);
	}

	/**
	  * Get the cell to the bottom of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	**/
	public Cell getBottomCell(Position position) {
		return this.boardView.getBottomCell(position);
	}

	/**
	  * Get the cell to the left of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	**/
	public Cell getLeftCell(Position position) {
		return this.boardView.getLeftCell(position);
	}

	/**
	  * Get the cell to the right of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	**/
	public Cell getRightCell(Position position) {
		return this.boardView.getRightCell(position);
	}

	/*********************************      
     ***Refresh movements/activity****
	*********************************/

	/**
	  * Refresh the movements 
	**/
	public void refreshMovements() {
		this.bluePlayer.refreshMovements();
		this.redPlayer.refreshMovements();
	}

	/**
	  * Refresh inactivity of the persons 
	**/
	public void refreshInactivity() {
		Person[] persons = (this.isRedPlayerPlaying()) ? this.bluePlayer.getPersons() : this.redPlayer.getPersons();
		for(Person person : persons) {
			if(person.isInactive()) {
				person.setIsInactive(false);
				this.boardView.getCell(person.getPosition()).setPerson(person); //Refresh cell
			}
		}
	}
}