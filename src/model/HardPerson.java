package model;

import helper.Position;

import enums.TeamType;
/**
  * The class <code>HardPerson</code> is the model a hard person
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class HardPerson extends Person {

    /**
     * Constructor of the HardPerson
     * @param position The position of the person on the board
     * @param teamType The team type of the person
    **/
    public HardPerson(Position position, TeamType teamType) {
        //initial values
        super(position, teamType);
        this.agressiveBonus = 1;
        this.defensiveBonus = 0;
        this.movementRemaining = 3;
        this.initialMovements = 3;

        String base = (this.teamType == TeamType.BLUE_TEAM) ? Person.BASE_PATH + "blue-side/" : Person.BASE_PATH + "red-side/";
        this.normalRepresentation = base + "dur.png";
        this.deadRepresentation = base + "dur-dead.png";
        this.hasBallRepresentation = base + "dur+balle.png";
        this.representation = this.normalRepresentation; //initial value
    }

    /**
     * Constructor of the BeefyPerson
     * @param person The person which is used for the clonage
    **/
	public HardPerson(HardPerson person) {
        super(person);
    }
}