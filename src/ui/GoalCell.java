package ui;

import ui.Cell;

import helper.Position;

import enums.TeamType;

import model.Person;

import java.awt.Graphics;

import java.awt.Color;

/**
  * The class <code>GoalCell</code> is a case in the board that represents a goal's part
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class GoalCell extends Cell {
    
    /**
      * Constant used for the line's color 
    **/
    public static final Color GOAL_LINE_COLOR = new Color(255, 255, 255);

    /**
      * Constant used to describe the offset for the draw 
    **/
    private static final int OFFSET = 10;

    /**
      * Constant used for the background color of the goal cell 
    **/
    public static final Color GOAL_BACKGROUND_COLOR = Color.BLACK;

	/**
	  * The team type of this cell 
	**/
	private TeamType teamType;

	/**
	 * Constructor of the GoalCell
	 * @param position The postion of the cell in the board
	 * @param person The person who is in this cell
	 * @param backgroundColor The background color of the cell
	 * @param teamType The team type of this cell
	 */
    public GoalCell(Position position, Person person, Color backgroundColor, TeamType teamType) {
		super(position, person, backgroundColor);
		this.teamType = teamType;
    }

    @Override 
    public void paintComponent(Graphics p) {
        Graphics g = p.create();
        if(this.isOpaque()) {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        g.setColor(GOAL_LINE_COLOR);
        g.fillRect(OFFSET, 0, this.getWidth() - (2 * OFFSET), this.getHeight());
	}
	
	/**
	  * Get the team type
	  * @return The team type of the goal cell
	**/
	public TeamType getTeamType() {
		return this.teamType;
	}
}