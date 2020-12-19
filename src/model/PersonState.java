package model;

public class PersonState implements java.io.Serializable {

    /**
      * The state of the person to know if he can move 
    **/
    private boolean canMove;

    /**
      * Check if the person has the ball 
    **/
    private boolean hasBall;

    /**
      * Check if the person cannot move  
    **/
    private boolean isInactive;

    /**
      * Check if the person is a defender during confrontation 
    **/
    private boolean isDefender;

    /**
      * Check if the person is an attacker during confrontation 
    **/
    private boolean isAttacker;
    
    /**
     * Constructor of the PersonState
     * @param canMove The move's state of the person
     * @param hasBall If the person has the ball
     * @param isInactive If the person cannot move
     */
    public PersonState(boolean canMove, boolean hasBall, boolean isInactive) {
        this.canMove = canMove;
        this.hasBall = hasBall;
        this.isInactive = isInactive;
        //default values for defender and attacker
        this.isAttacker = false;
        this.isDefender = false;
    }

    /**
     * Constructor of the PersonState
     * @param personState The states to clone
     */
    public PersonState(PersonState personState) {
        this.canMove = personState.canMove;
        this.hasBall = personState.hasBall;
        this.isInactive = personState.isInactive;
        this.isAttacker = personState.isAttacker;
        this.isDefender = personState.isDefender;
    }

    /*********************************      
     *************Getter**************
    *********************************/
    /**
      * Get the move's state of the person
      * @return The move's stateof the person  
    **/
    public boolean canMove() {
        return this.canMove;
    }

    /**
      * Get the ball's state
      * @return True if the person has the ball, else false  
    **/
    public boolean hasBall() {
        return this.hasBall;
    }

    /**
      * Get the defender's status of the person
      * @return True if he is a defender, else false  
    **/
    public boolean isDefender() {
        return this.isDefender;
    }

    /**
      * Get the attacker's status
      * @return True if he is an attacker, else false  
    **/
    public boolean isAttacker() {
        return this.isAttacker;
    }

    /**
      * Get the inactive's state
      * @return True if the person is inactive, else false  
    **/
    public boolean isInactive() {
        return this.isInactive;
    }

    /*********************************      
     *************Setter**************
    *********************************/
    /**
      * Set the state of the movement
      * @param canMove The new state  
    **/
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
      * Set the state of the person if he has the ball
      * @param hasBall The new state
    **/
    public void setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
    }

    /**
      * Set the defender's state of the person
      * @param isDefender The new state
    **/
    public void setisDefender(boolean isDefender) {
        this.isDefender = isDefender;
    }

    /**
      * Set the attacker's state of the person
      * @param isAttacker The new state
    **/
    public void setIsAttacker(boolean isAttacker) {
        this.isAttacker = isAttacker;
    }


    /**
      * Set the state of the inactivity of the person
      * @param isInactive The new state
    **/
    public void setIsInactive(boolean isInactive) {
        this.isInactive = isInactive;
    }

    /*********************************      
     *********Toggles methods*********
    *********************************/
    /**
      * Toggle the canMove's state
    **/
    public void toggleCanMove() {
        this.canMove = !this.canMove;
    }

    /**
      * Toggle the isInactive's state
    **/
    public void toggleIsInactive() {
        this.isInactive = !this.isInactive;
    }

    /**
      * Toggle the hasBall's state
    **/
    public void toggleHasBall() {
        this.hasBall = !this.hasBall;
    }

    /**
      * Toggle the defender's status
    **/
    public void toggleIsDefender() {
        this.isDefender = !this.isDefender;
    }

    /**
      * Toggle the attacker's status
    **/
    public void toggleIsAttacker() {
        this.isAttacker = !this.isAttacker;
    }

    public boolean equals(PersonState state) {
        return ((this.canMove == state.canMove) && (this.hasBall == state.hasBall) && (this.isInactive == state.isInactive) && 
                (this.isDefender == state.isDefender) && (this.isAttacker == state.isAttacker));
    }
}