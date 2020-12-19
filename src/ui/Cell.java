package ui;

import helper.Position;

import model.CellState;
import model.Person;
import model.Ball;

import helper.Palette;
import helper.Assert;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Image;

import java.awt.Color;

/**
  * The class <code>Cell</code> is a case in the board
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class Cell extends JButton {

    /**
      * The position of the cell in the board 
    **/
    protected Position position;

    /**
      * The representation of the person of the cell 
    **/
    protected Person person;

    /**
      * All states that concern the cell  
    **/
    protected CellState state;

    /**
      * Background color to refresh the cell  
    **/
    protected Color initialBackgroundColor;

    /**
      * Constructor of the Cell
      * @param position The position of the cell in the board
      * @param person The person who is on the cell
      * @param backgroundColor The current background color of the cell 
    **/
    public Cell(Position position, Person person, Color backgroundColor) {
        super();
        this.position = position;
        this.person = person;
        this.setBackground(backgroundColor);
        this.initialBackgroundColor = backgroundColor;
        this.state = new CellState(true, false, false); //initial value

        this.setBorderPainted(false); //remove borders

    }

    /*********************************      
     *************Getter**************
    *********************************/

    /**
      * Get the empty's state
      * @return The empty's state of a cell  
    **/
    public boolean isEmpty() {
        return this.state.isEmpty();
    }

    /**
      * Get if the cell is a goal cell
      * @return Get if the cell is a goal cell  
    **/
    public boolean isGoalCell() {
        return (this instanceof GoalCell);
    }

    /**
      * Get the selected's state
      * @return The empty's state of a cell  
    **/
    public boolean isSelected() {
        return this.state.isSelected();
    }

    /**
      * Get the hasBall's state
      * @return The hasBall's state of a cell  
    **/
    public boolean hasBall() {
        return this.state.hasBall();
    }

    /**
      * Get the person on this cell
      * @return The person on this cell  
    **/
    public Person getPerson() {
        return this.person;
    }

    /**
      * Get the the position of the cell
      * @return The position of the cell 
    **/
    public Position getPosition() {
        return this.position;
    }

    /*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the empty's state
      * @param isEmpty The new state  
    **/
    public void setIsEmpty(boolean isEmpty) {
        this.state.setIsEmpty(isEmpty);
    }

    /**
      * Set the selected's state
      * @param isSelected The new state  
    **/
    public void setIsSelected(boolean isSelected) {
        this.state.setIsSelected(isSelected);
        this.updateAppearance();
    }

    /**
      * Set the hasBall's state
      * @param hasBall The new state  
    **/
    public void setHasBall(boolean hasBall) {
        this.state.setHasBall(hasBall);
        if(this.state.hasBall()) {
            this.setIcon(new ImageIcon(Ball.BALL_REPRESENTATION));
            this.setText("");
        }
        this.setIsEmpty(false);
    }   
    
    /**
      * Set the person on the cell
      * @param isSelected The new person  
    **/
    public void setPerson(Person person) {
        this.person = person;
        ImageIcon icon = Assert.isNull(this.person) ? null : new ImageIcon(this.person.getRepresentation());
        this.setIsEmpty(Assert.isNull(this.person));
        this.setIcon(icon);  
    }

    /*********************************      
     *********Toggles methods*********
    *********************************/
    
    /**
      * Toggle the empty's state
    **/
    public void toggleIsEmpty() {
        this.state.toggleIsEmpty();
    }

    /**
      * Toggle the selected's state
    **/
    public void toggleIsSelected() {
        this.state.toggleIsSelected();
    }

    /**
      * Toggle the hasBall's state
    **/
    public void toggleHasBall() {
        this.state.toggleHasBall();
    }
    
    /*********************************      
     *********Others methods**********
    *********************************/    

    /**
      * Update the background colorof the cell 
    **/
    private void updateAppearance() {
        if(this.state.isSelected()) {
            this.setBackground(Palette.SELECTED_CELL);
        } else {
            this.setBackground(this.initialBackgroundColor);
        }
    }
}