package model;

import helper.Position;

import enums.TeamType;

/**
  * The class <code>BeefyPerson</code> is the model of a beefy person
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class BeefyPerson extends Person {

    /**
     * Constructor of the BeefyPerson
     * @param position The position of the person on the board
     * @param teamType The team type of the person
    **/
    public BeefyPerson(Position position, TeamType teamType) {
        //initial values
        super(position, teamType);
        this.agressiveBonus = 2;
        this.defensiveBonus = 1;
        this.movementRemaining = 2;
        this.initialMovements = 2;
        String base = (this.teamType == TeamType.BLUE_TEAM) ? Person.BASE_PATH + "blue-side/" : Person.BASE_PATH + "red-side/";
        this.normalRepresentation = base + "costaud.png";
        this.deadRepresentation = base + "costaud-dead.png";
        this.hasBallRepresentation = base + "costaud+balle.png";
        this.representation = this.normalRepresentation; //initial value
    }   

    /**
     * Constructor of the BeefyPerson
     * @param person The person which is used for the clonage
    **/
	public BeefyPerson(BeefyPerson person) {
        super(person);
    }
	
}