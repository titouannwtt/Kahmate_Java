package model;

import helper.Position;

import enums.TeamType;

/**
  * The class <code>FastPerson</code> is the model of a fast person
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class FastPerson extends Person {

    /**
     * Constructor of the FastPerson
     * @param position The position of the person on the board
     * @param teamType The team type of the person
    **/
    public FastPerson(Position position, TeamType teamType) {
        //initial values
        super(position, teamType);
        this.agressiveBonus = -1;
        this.defensiveBonus = -1;
        this.movementRemaining = 4;
        this.initialMovements = 4;
        String base = (this.teamType == TeamType.BLUE_TEAM) ? Person.BASE_PATH + "blue-side/" : Person.BASE_PATH + "red-side/";
        this.normalRepresentation = base + "rapide.png";
        this.deadRepresentation = base + "rapide-dead.png";
        this.hasBallRepresentation = base + "rapide+balle.png";
        this.representation = this.normalRepresentation; //initial value
    }

    /**
     * Constructor of the BeefyPerson
     * @param person The person which is used for the clonage
    **/
	public FastPerson(FastPerson person) {
        super(person);
    }
}