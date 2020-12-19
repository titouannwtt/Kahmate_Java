package model;

import helper.Position;

import enums.TeamType;
/**
  * The class <code>SmartPerson</code> is the model of a smart person
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class SmartPerson extends Person {
    
    /**
     * Constructor of the SmartPerson
     * @param position The position of the person on the board
     * @param teamType The team type of the person
    **/
    public SmartPerson(Position position, TeamType teamType) {
        //initial values
        super(position, teamType);
        this.agressiveBonus = 0;
        this.defensiveBonus = 1;
        this.movementRemaining = 3;
        this.initialMovements = 3;

        String base = (this.teamType == TeamType.BLUE_TEAM) ? Person.BASE_PATH + "blue-side/" : Person.BASE_PATH + "red-side/";
        this.normalRepresentation = base + "fute.png";
        this.deadRepresentation = base + "fute-dead.png";
        this.hasBallRepresentation = base + "fute+balle.png";
        this.representation = this.normalRepresentation; //initial value
    }

    /**
     * Constructor of the BeefyPerson
     * @param person The person which is used for the clonage
    **/
	public SmartPerson(SmartPerson person) {
        super(person);
    }
}