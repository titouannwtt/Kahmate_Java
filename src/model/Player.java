package model;

import model.PlayerState;
import model.Card;
import model.Person;
import model.Hand;

import helper.Assert;
import helper.Position;

import enums.TeamType;

import java.util.ArrayList;
/**
  * The class <code>Player</code> is the representation of the player
  * @version 1.0
  * @author Dorian Terbah
**/

public class Player implements java.io.Serializable {

    /**
      * The value of the number max of the persons that the player can have 
    **/
    public static final int MAX_PERSONS = 6;

    /**
      * The value of the number max of the persons that can be used during a round
    **/
    public static final int MAX_USED_PERSONS_BY_TURN = 2;

    /**
      * The team of the player 
    **/
    protected TeamType teamType;

    /**
      * The selected card of the person 
    **/
    protected Card selectedCard;

    /**
      * The different states of the player 
    **/
    protected PlayerState playerState;

    /**
      * The persons of the player 
    **/
    protected Person[] persons;

    /**
      * The used persons of the player
    **/
    protected ArrayList<Person> usedPersons;

    /**
      * The hand of the player 
    **/
    protected Hand hand;

    public Player(Person[] persons, PlayerState playerState, TeamType teamType) {
        this.selectedCard = null; //initial value
        this.persons = persons;
        this.playerState = playerState;
        this.teamType = teamType;
        this.hand = new Hand(this);

        this.usedPersons = new ArrayList<Person>();
    }

    /*********************************      
    **************Getter**************
    *********************************/

    /**
      * Get the canPlay's state
      * @return The canPlay's state  
    **/
    public boolean canPlay() {
        return this.playerState.canPlay();
    }

    /**
      * Get the isChoosingPerson's state
      * @return The isChoosingPerson's state  
    **/
    public boolean isChoosingPerson() {
        return this.playerState.isChoosingPerson();
    }

    /**
      * Check if the player can make a shot
      * @return True true if it is, else false 
    **/
    public boolean canMakeShot(Person hasBallPerson) {
        Position hasBallPosition = hasBallPerson.getPosition();
        for(Person person : this.persons) {
            Position position = person.getPosition();
            if(this.teamType.equals(TeamType.RED_TEAM)) {
                if(position.getX() > hasBallPosition.getX()) {
                    return false;
                }
            } else {
                if(position.getX() < hasBallPosition.getX()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
      * Get the isChoosingCard's state
      * @return The isChoosingCard's state  
    **/
    public boolean isChoosingCard() {
        return this.playerState.isChoosingCard();
    }

    /**
      * Get if the player is a bot
      * @return True is it is, else false 
    **/
    public boolean isBotPlayer() {
        return (this instanceof Player);
    }

    /**
      * Get the persons of the player
      * @return The persons 
    **/
    public Person[] getPersons() {
        return this.persons;
    }

    /**
      * Get the selected card
      * @return The selected card
    **/
    public Card getSelectedCard() {
        return this.selectedCard;
    }

    /**
      * Get the hand of the player
      * @return The hand of the player 
    **/
    public Hand getHand() {
        return this.hand;
    }

    /**
      * Get the used persons
      * @return The used persons 
    **/
    public ArrayList<Person> getUsedPersons() {
        return this.usedPersons;
    }

    /**
      * Get if the player is a red player
      * @return True if he is a red player, else false 
    **/
    public boolean isRedPlayer() {
        return this.teamType.equals(TeamType.RED_TEAM);
    }

    /*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the canPlay's state
      * @param canPlay The new state  
    **/
    public void setCanPlay(boolean canPlay) {
        this.playerState.setCanPlay(canPlay);
    }

    /**
      * Set the isChoosingPerson's state
      * @param isChoosingPerson The new state  
    **/
    public void setIsChoosingPerson(boolean isChoosingPerson) {
        this.playerState.setIsChoosingPerson(isChoosingPerson);
    }

    /**
      * Set the isChoosingCard's state
      * @param isChoosingCard The new state  
    **/
    public void setIsChoosingCard(boolean isChoosingCard) {
        this.playerState.setIsChoosingCard(isChoosingCard);
    }

    /**
      * Set the selected card
      * @param isChoosingPerson The new card  
    **/
    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    /**
      * Set the new persons
      * @param persons The new persons 
    **/
    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    /**
      * Set used persons
      * @param usedPersons The new used persons 
    **/
    public void setUsedPersons(ArrayList<Person> usedPersons) {
        this.usedPersons = usedPersons;
    }

    /*********************************      
     *********Toggles methods*********
    *********************************/
    
    /**
      * Toggle the canPlay's state
    **/
    public void toggleCanPlay() {
        this.playerState.toggleCanPlay();
    }

    /**
      * Toggle the isChoosingPerson's state
    **/
    public void toggleIsChoosingPerson() {
        this.playerState.toggleIsChoosingPerson();
    }

    /**
      * Toggle the isChoosingCard's state
    **/
    public void toggleisChoosingCard() {
        this.playerState.toggleisChoosingCard();
    }

    /*********************************      
     *********Others methods**********
    *********************************/

    /**
      * Discard a card in the hand
      * @param card The card to discard 
    **/
    public void discardCard(Card card) {
        this.hand.discardCard(card);
        if(this.hand.getCards().size() == 0) this.refreshHand();
    }

    /**
      * Refresh the hand 
    **/
    public void refreshHand() {
        this.hand.refresh();
    }

    /*********************************      
     ********Refresh movements********
    *********************************/

    /**
      * Refresh movements 
    **/
    public void refreshMovements() {
        for(Person person : this.persons) {
            person.refreshMovements();
        }

        this.usedPersons.clear();
    }

    /*********************************      
     ************Used person**********
    *********************************/

    /**
      * Check if the player can add a player
      * @param usedPerson The person's to check
      * @return false if we can't, else true 
    **/
    public boolean canAddUsedPerson(Person usedPerson) {
        return ((this.usedPersons.size() < 2) && (!this.hasUsedPerson(usedPerson))) || this.hasUsedPerson(usedPerson);
    }

    /**
      * Check if the add's person already exists
      * @param usedPerson The new person
      * @return True if we can, else false
    **/
    private boolean hasUsedPerson(Person usedPerson) {
        for(Person person : this.usedPersons) {
            if(person.equals(usedPerson)) return true;
        }

        return false;
    }

    /**
      * Add an used person
      * @param usedPerson The new person 
      * @return true if the adding is a success, else false
    **/
    public boolean addUsedPerson(Person usedPerson) {
      if(!this.hasUsedPerson(usedPerson) && (this.canAddUsedPerson(usedPerson))) {
            this.usedPersons.add(usedPerson);
            return true;
      }

      return this.hasUsedPerson(usedPerson);
    }

    /**
      * Permits to know if a person is a this player
      * @param person The person
      * @return true if it is, else false 
    **/
    public boolean hasPerson(Person person) {
        for(Person currentPerson : this.persons) {
            if(currentPerson.equals(person)) return true;
        }

        return false;
    }
}