package undo;

import model.Person;
import model.PersonState;
import model.Ball;
import model.Card;
import model.Player;
import model.OrdinaryPerson;
import model.HardPerson;
import model.BeefyPerson;
import model.FastPerson;
import model.SmartPerson;

import enums.TeamType;

import helper.Position;

import java.util.ArrayList; 
/**
  * The class <code>BoardSave</code> saves the crucial informations of the board
  * @version 1.0
  * @author Dorian Terbah
**/

public class BoardSave {

    /**
      * The cards of the blue player 
    **/
    private ArrayList<Card> blueCards;

    /**
      * The cards of the red player 
    **/
    private ArrayList<Card> redCards;

    /**
      * The representation of the ball 
    **/
    private Ball ball;

    /**
      * Permit to know who is playing (the red player or the blue player) 
    **/
    private boolean isRedPlayerPlaying;

    /**
      * A list that contains all persons on the board 
    **/
    private ArrayList<Person> persons;

    /**
      * A list that contains all used player for the blue player 
    **/
    private ArrayList<Person> blueUsedPersons;

    /**
      * A list that contains all used player for the red player 
    **/
    private ArrayList<Person> redUsedPersons;

    /**
     * Constructor of the BoardSave
     * @param bluePlayer The blue player
     * @param redPlayer The red player
     * @param ball The ball in the board
     * @param isRedPlayerPlaying The player who is playing
    **/
    public BoardSave(Player bluePlayer, Player redPlayer, Ball ball, boolean isRedPlayerPlaying) {
        //copy the blue cards
        this.blueCards = new ArrayList<Card>();
        this.redCards = new ArrayList<Card>();
        this.persons = new ArrayList<Person>();

        //set the blue used persons
        this.blueUsedPersons = new ArrayList<Person>();
        for(Person person : bluePlayer.getUsedPersons()) {
            this.blueUsedPersons.add(new Person(person));
        }

        //set the red persons
        this.redUsedPersons = new ArrayList<Person>();
        for(Person person : redPlayer.getUsedPersons()) {
            this.redUsedPersons.add(new Person(person));
        }

        for(Card card : bluePlayer.getHand().getCards()) {
            this.blueCards.add(new Card(card.getStrenght()));
        }

        //copy the red cards
        for(Card card : redPlayer.getHand().getCards()) {
            this.redCards.add(new Card(card.getStrenght()));
        }

        //copy the ball
        this.ball = new Ball(new Position(ball.getPosition().getX(), ball.getPosition().getY()));

        //copy the isRedPlayerPlaying
        this.isRedPlayerPlaying = isRedPlayerPlaying;

        //copy the persons
        
        //blue
        for(Person person : bluePlayer.getPersons()) {
            Person currentPerson = null;
            Position position = new Position(person.getPosition());

            if(person instanceof BeefyPerson) {
                currentPerson = new BeefyPerson((BeefyPerson)person);
            } else if(person instanceof SmartPerson) {
                currentPerson = new SmartPerson((SmartPerson)person);
            } else if(person instanceof FastPerson) {
                currentPerson = new FastPerson((FastPerson)person);
            } else if(person instanceof OrdinaryPerson) {
                currentPerson = new OrdinaryPerson((OrdinaryPerson)person);
            } else if(person instanceof HardPerson) {
                currentPerson = new HardPerson((HardPerson)person);
            }

            if(currentPerson.hasBall()) {
                this.ball.setPossessor(currentPerson);
            }

            this.persons.add(currentPerson);
        }

        //red
        for(Person person : redPlayer.getPersons()) {
            Person currentPerson = null;
            TeamType team = person.getTeamType();
            Position position = new Position(person.getPosition().getX(), person.getPosition().getY());
            if(person instanceof BeefyPerson) {
                currentPerson = new BeefyPerson((BeefyPerson)person);
            } else if(person instanceof SmartPerson) {
                currentPerson = new SmartPerson((SmartPerson)person);
            } else if(person instanceof FastPerson) {
                currentPerson = new FastPerson((FastPerson)person);
            } else if(person instanceof OrdinaryPerson) {
                currentPerson = new OrdinaryPerson((OrdinaryPerson)person);
            } else if(person instanceof HardPerson) {
                currentPerson = new HardPerson((HardPerson)person);
            } 

            if(currentPerson.hasBall()) {
                this.ball.setPossessor(currentPerson);
            }

            this.persons.add(currentPerson);
        }

    }

    /*********************************      
     *************Getter**************
    *********************************/
    
    /**
      * Get the blue cards
      * @return The blue cards 
    **/
    public ArrayList<Card> getBlueCards() {
        return this.blueCards;
    }

    /**
      * Get the red cards
      * @return The red cards 
    **/
    public ArrayList<Card> getRedCards() {
        return this.redCards;
    }

    /**
      * Get the ball
      * @return The ball 
    **/
    public Ball getBall() {
        return this.ball;
    }

    /**
      * Get if the red player is playing
      * @return if the red player is playing 
    **/
    public boolean isRedPlayerPlaying() {
        return this.isRedPlayerPlaying;
    }

    /**
      * Get the persons
      * @return The persons 
    **/
    public ArrayList<Person> getPersons() {
        return this.persons;
    }

    /**
      * Get the red used persons
      * @return The red used persons 
    **/
    public ArrayList<Person> getRedUsedPersons() {
        return this.redUsedPersons;
    }

    /**
      * Get the blue persons
      * @return The blue used persons 
    **/
    public ArrayList<Person> getBlueUsedPersons() {
        return this.blueUsedPersons;
    }

}