package model;

/**
  * The class <code>CaseState</code> is the states of a cell in a board
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class CellState implements java.io.Serializable {

    /**
      * Permit to know if the associated cell has a ball or a person 
    **/
    private boolean isEmpty;

    /**
      * Permit to know if the associated cell is selected 
    **/
    private boolean isSelected;

    /**
      * Permit to know if the associated cell has the ball 
    **/
    private boolean hasBall;

    /**
     * Constructor of the CellState
     * @param isEmpty The empty's state of the associated cell
     * @param isSelected The selected's state of the associated cell
     * @param hasBall If the associated cell has the ball
     */
    public CellState(boolean isEmpty, boolean isSelected, boolean hasBall) {
        this.isEmpty = isEmpty;
        this.isSelected = isSelected;
        this.hasBall = hasBall;
    }

    /*********************************      
     *************Getter**************
    *********************************/

    /**
      * Get the empty's state
      * @return The empty's state of a cell  
    **/
    public boolean isEmpty() {
        return this.isEmpty;
    }

    /**
      * Get the selected's state
      * @return The empty's state of a cell  
    **/
    public boolean isSelected() {
        return this.isSelected;
    }

    /**
      * Get the hasBall's state
      * @return The hasBall's state of a cell  
    **/
    public boolean hasBall() {
        return this.hasBall;
    }

    /*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the empty's state
      * @param isEmpty The new state  
    **/
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
      * Set the selected's state
      * @param isSelected The new state  
    **/
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
      * Set the hasBall's state
      * @param hasBall The new state  
    **/
    public void setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
    }
    
    /*********************************      
     *********Toggles methods*********
    *********************************/
    
    /**
      * Toggle the empty's state
    **/
    public void toggleIsEmpty() {
        this.isEmpty = !this.isEmpty;
    }

    /**
      * Toggle the selected's state
    **/
    public void toggleIsSelected() {
        this.isSelected = !this.isSelected;
    }

    /**
      * Toggle the hasBall's state
    **/
    public void toggleHasBall() {
        this.hasBall = !this.hasBall;
    }
}