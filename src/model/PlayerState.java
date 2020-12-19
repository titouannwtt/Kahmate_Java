package model;

/**
  * The class <code>PlayerState</code> represents the states of a player
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PlayerState implements java.io.Serializable {

    /**
      * If the player can play 
    **/
    private boolean canPlay;

    /**
      * If the player is choosing a person 
    **/
    private boolean isChoosingPerson;

    /**
      * If the player is choosing a card 
    **/
    private boolean isChoosingCard; 
    
    /**
     * Constructor of the PlayerState
     * @param canPlay If the player can play
     * @param isChoosingPerson If the player is choosing a person
     * @param isChoosingCard If the player is choosing a card
     */
    public PlayerState(boolean canPlay, boolean isChoosingPerson, boolean isChoosingCard) {
        this.canPlay = canPlay;
        this.isChoosingPerson = isChoosingPerson;
        this.isChoosingCard = isChoosingCard;
    }

    /*********************************      
    **************Getter**************
    *********************************/

    /**
      * Get the canPlay's state
      * @return The canPlay's state  
    **/
    public boolean canPlay() {
        return this.canPlay;
    }

    /**
      * Get the isChoosingPerson's state
      * @return The isChoosingPerson's state  
    **/
    public boolean isChoosingPerson() {
        return this.isChoosingPerson;
    }

    /**
      * Get the isChoosingCard's state
      * @return The isChoosingCard's state  
    **/
    public boolean isChoosingCard() {
        return this.isChoosingCard;
    }

    /*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the canPlay's state
      * @param canPlay The new state  
    **/
    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    /**
      * Set the isChoosingPerson's state
      * @param isChoosingPerson The new state  
    **/
    public void setIsChoosingPerson(boolean isChoosingPerson) {
        this.isChoosingPerson = isChoosingPerson;
    }

    /**
      * Set the isChoosingCard's state
      * @param isChoosingCard The new state  
    **/
    public void setIsChoosingCard(boolean isChoosingCard) {
        this.isChoosingCard = isChoosingCard;
    }

    /*********************************      
     *********Toggles methods*********
    *********************************/
    
    /**
      * Toggle the canPlay's state
    **/
    public void toggleCanPlay() {
        this.canPlay = !this.canPlay;
    }

    /**
      * Toggle the isChoosingPerson's state
    **/
    public void toggleIsChoosingPerson() {
        this.isChoosingPerson = !this.isChoosingPerson;
    }

    /**
      * Toggle the isChoosingCard's state
    **/
    public void toggleisChoosingCard() {
        this.isChoosingCard = !this.isChoosingCard;
    }
}