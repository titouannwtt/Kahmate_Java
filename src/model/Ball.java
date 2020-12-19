package model;

import model.Person;

import helper.Position;

/**
  * The class <code>Ball</code> is the representation of the ball
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/
public class Ball implements java.io.Serializable {
    
    /**
      * The image that represents the ball 
    **/
    public static final String BALL_REPRESENTATION = "./rsc/images/ball.png";

    /**
      * The position of the ball on the board 
    **/
    private Position position;

    /**
      * The possessor of the ball on the board  
    **/
    private Person possessor;

    /**
     * Constructor of the ball
     * @param position The position of the ball on the board
     */
    public Ball(Position position) {
        this.position = position;
        this.possessor = null;
    }

    /*********************************      
     *************Getter**************
    *********************************/
   
    /**
      * Get the position of the ball in the board
      * @return The position of the ball in the board
    **/
    public Position getPosition() {
        return this.position;
    }

    /**
      * Get the possessor of the ball in the board
      * @return The possessor of the ball in the board
    **/
    public Person getPossessor() {
        return this.possessor;
    }

    /*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the position of the ball
      * @param position The new position  
    **/
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
      * Set the possessor of the ball
      * @param possessor The new possessor  
    **/
    public void setPossessor(Person possessor) {
        this.possessor = possessor;
    }
}