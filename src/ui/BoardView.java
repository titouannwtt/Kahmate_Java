package ui;

import ui.Cell;
import ui.GoalCell;

import ui.container.Container;
import ui.container.StartGameContainer;
import ui.container.SelectionPlayerContainer;
import ui.container.ChoiceContainer;

import controller.BoardController;

import helper.Position;
import helper.Palette;
import helper.Console;

import model.BoardModel;
import model.Person;
import model.SmartPerson;
import model.OrdinaryPerson;
import model.BeefyPerson;
import model.FastPerson;
import model.HardPerson;
import model.Player;

import enums.BoardState;
import enums.GameState;
import enums.TeamType;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.util.ArrayList;

/**
  * The class <code>BoardView</code> is the board's view
  * @version 1.0
  * @author Lucas Augusto 
**/

public class BoardView extends JPanel {

	/**
	  * Constant that represents the wifth of the board 
	**/
	public static final int BOARD_WIDTH = 13;

	/**
	  * Constant that represents the height of the board 
	**/
	public static final int BOARD_HEIGHT = 8;

	/**
	  * Consta,t used to represent the center of the board 
	**/
	public static final int X_CENTER = 6;

	/**
	  * All cells that compose the board 
	**/
	private Cell[][] cells;

	/**
	  * The model of the board that contains all informations about the state of tghe game, the players, etc 
	**/
	private BoardModel model;
	
	/**
	  * The controller of the board that controls the actions on the cells 
	**/
	private BoardController controller;

	/**
	  * Container on the south of the view that enables to do some actions 
	**/
	private Container container;

	/**
	  * The container of the cells 
	**/
	private JPanel gridPanel;

	/**
	  * The main view of the application 
	**/
	private Window window;

	/**
	 * Constructor of the BoardView
	 * @param window The main view of the application
	 * @param isBot A state to know if the second player is a bot
	 */
	public BoardView(Window window, boolean isBot){
		super(new BorderLayout());
		this.window = window;
		this.cells = new Cell[BOARD_HEIGHT][BOARD_WIDTH];
		//generate the board
		this.container = new Container();
		this.gridPanel = new JPanel(new GridLayout(BOARD_HEIGHT, BOARD_WIDTH));
		Position position;
		Color backgroundColor;
		for(int y = 0; y < BOARD_HEIGHT; y++) {

			for(int x = 0; x < BOARD_WIDTH; x++) {
				position = new Position(x, y);
				//goal cell
				if((x == 0) || (x == (BOARD_WIDTH - 1))) {
					this.cells[y][x] = new GoalCell(position, null, GoalCell.GOAL_BACKGROUND_COLOR, (x == 0) ? TeamType.RED_TEAM : TeamType.BLUE_TEAM);
				} else {
					backgroundColor = ((((y % 2) == 0) && ((x % 2) == 0)) || (((y % 2) == 1) && ((x % 2) == 1)))  ? Palette.LIGHT_GREEN : Palette.DARK_GREEN;
					this.cells[y][x] = new Cell(position, null, backgroundColor);
				}

				//write a number if the cell is in the middle
				if((x == X_CENTER) && (y > 0) && (y < (BOARD_HEIGHT - 1))) {
					this.cells[y][x].setText("" + y);
					this.cells[y][x].setForeground(Color.WHITE);
				}

				this.gridPanel.add(this.cells[y][x]); //add cell to board
			}
		}

		this.add(this.gridPanel, BorderLayout.CENTER);

		this.loadGame(isBot);
	}

	/*********************************      
    **************Getter**************
	*********************************/
	
	/**
      * Get a cell associated to a position
	  * @param positon The position of the cell in the board
	  * @return The cell associated to the position  
    **/
	public Cell getCell(Position position) {
		int x = position.getX(), y = position.getY();
		return ((x < 0 || x >= BOARD_WIDTH) || (y < 0 || y >= BOARD_HEIGHT)) ? null : this.cells[position.getY()][position.getX()];
	}

	/**
	  * Get the cell to the top of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	 */
	public Cell getTopCell(Position position) {
		return (position.getY() > 0) ? this.getCell(new Position(position.getX(), position.getY() - 1)) : null;
	}

	/**
	  * Get the cell to the bottom of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	**/
	public Cell getBottomCell(Position position) {
		return (position.getY() < (BOARD_HEIGHT - 1)) ? this.getCell(new Position(position.getX(), position.getY() + 1)) : null;
	}

	/**
	  * Get the cell to the left of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	**/
	public Cell getLeftCell(Position position) {
		return (position.getX() > 0) ? this.getCell(new Position(position.getX() - 1, position.getY())) : null;
	}

	/**
	  * Get the cell to the right of position
	  * @param position The position to analyze
	  * @return The cell if it exists 
	**/
	public Cell getRightCell(Position position) {
		return (position.getX() < (BOARD_WIDTH - 1)) ? this.getCell(new Position(position.getX() + 1, position.getY())) : null;
	}

	/*********************************      
     *************Setter**************
	*********************************/

	/*********************************      
    *********Board methods************
	*********************************/

	/**
	  * Load the data for the game 
	**/
	private void loadGame(boolean isBot) {
		//generate all models for the players, initials positions for the persons
		Person[] redPersons = new Person[] {
			new OrdinaryPerson(new Position(1, 1), TeamType.RED_TEAM),
			new OrdinaryPerson(new Position(2, 2), TeamType.RED_TEAM),
			new HardPerson(new Position(1, 3), TeamType.RED_TEAM),
			new FastPerson(new Position(2, 4), TeamType.RED_TEAM),
			new BeefyPerson(new Position(1, 5), TeamType.RED_TEAM),
			new SmartPerson(new Position(2, 6), TeamType.RED_TEAM)
		};

		Person[] bluePersons = new Person[] {	
			new OrdinaryPerson(new Position(11, 1), TeamType.BLUE_TEAM),
			new OrdinaryPerson(new Position(10, 2), TeamType.BLUE_TEAM),
			new HardPerson(new Position(11, 3), TeamType.BLUE_TEAM),
			new FastPerson(new Position(10, 4), TeamType.BLUE_TEAM),
			new BeefyPerson(new Position(11, 5), TeamType.BLUE_TEAM),
			new SmartPerson(new Position(10, 6), TeamType.BLUE_TEAM)
		};

		//add the models associated to the cells
		for(Person person: redPersons) {
			Position position = person.getPosition();
			Cell cell = this.cells[position.getY()][position.getX()];
			cell.setPerson(person);
		}

		for(Person person : bluePersons) {
			Position position = person.getPosition();
			Cell cell = this.cells[position.getY()][position.getX()];
			cell.setPerson(person);
		}

		//model with bot
		/*
		if(isBot) {
			this.model = new BoardModelForBot(bluePerson, redPerson, this)
		} else {
			this.model = new BoardModel(isBot, bluePersons, redPersons, this);	
		}
		*/
		this.model = new BoardModel(isBot, bluePersons, redPersons, this, this.cells);
		this.controller = new BoardController(this, model);

		//add the controller to cell
		for(int y = 0; y < BOARD_HEIGHT; y++) {
			for(int x = 0; x < BOARD_WIDTH; x++) {
				this.controller.addActionListenerTo(this.cells[y][x]); 
			}
		}

		//change the view 
		this.changeContainer(BoardState.PLAYER_PLACEMENTS_STATE);
	}

	/**
	  * Change the container of the board
	  * @param state The new state in board 
	**/
	public void changeContainer(BoardState state) {
		this.model.setState(state);
		this.remove(this.container);
		if(this.model.getState().equals(BoardState.PLAYER_PLACEMENTS_STATE)) {
			this.container = new StartGameContainer(this);
		} else if(this.model.getState().equals(BoardState.GAME_IN_PAUSE_STATE)) {
			this.container = new SelectionPlayerContainer(this.model);
		} else if(this.model.getState().equals(BoardState.IN_GAME_STATE)) {
			this.container = new ChoiceContainer(this.model);
		} 

		this.add(this.container, BorderLayout.SOUTH);
		this.revalidate();
	}

	/**
	  * Display the end menu
	  * @param isRedPlayerWinning
	**/
	public void displayEndMenu(boolean isRedPlayerWinning) {
		this.window.displayEndMenu(isRedPlayerWinning);
	}

	/*********************************      
     ***********Range methds**********
	*********************************/

	/**
	  * Show the movement area of a person
	  * @param cells The available cells 
	**/
	public void showMovementArea(ArrayList<Cell> cells) {
		this.clearBoard();
		for(Cell cell : cells) {
			cell.setBackground(Palette.AVAILABLE_MOVEMENT_COLOR);
			//add border to the current cell
			cell.setBorderPainted(true);
			cell.setBorder(new LineBorder(Palette.BORDER_COLOR));
		}
	}

	/**
	  * Clear the board
	**/
	public void clearBoard() {

		Color backgroundColor;
		for(int y = 0; y < BOARD_HEIGHT; y++) {

			for(int x = 0; x < BOARD_WIDTH; x++) {
				backgroundColor = ((((y % 2) == 0) && ((x % 2) == 0)) || (((y % 2) == 1) && ((x % 2) == 1)))  ? Palette.LIGHT_GREEN : Palette.DARK_GREEN;
				if(this.cells[y][x].isGoalCell()) backgroundColor = GoalCell.GOAL_BACKGROUND_COLOR;
				this.cells[y][x].setBackground(backgroundColor);
				//remove border ot the current cell
				this.cells[y][x].setBorder(null);
				this.cells[y][x].setBorderPainted(false);
			}
		}
	}	

	/**
	  * Change the round for the player
	  * @param isRedPlayerPlaying If it is the round of the red player 
	**/
	public void setRound(boolean isRedPlayerPlaying) {
		if(this.container instanceof ChoiceContainer) {
			ChoiceContainer choiceContainer = (ChoiceContainer)this.container;
			choiceContainer.updateRound(isRedPlayerPlaying);
		}
	}
}