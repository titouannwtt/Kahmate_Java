package model;

import helper.Position;

import enums.TeamType;
/**
  * The class <code>OrdinaryPerson</code> is the model of a ordinary person
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class OrdinaryPerson extends Person {

    /**
     * Constructor of the OrdinaryPerson
     * @param position The position of the person on the board
     * @param teamType The team type of the person
    **/
    public OrdinaryPerson(Position position, TeamType teamType) {
        //initial values
        super(position, teamType);
        this.agressiveBonus = 0;
        this.defensiveBonus = 0;
        this.movementRemaining = 3;
        this.initialMovements = 3;

        String base = (this.teamType == TeamType.BLUE_TEAM) ? Person.BASE_PATH + "blue-side/" : Person.BASE_PATH + "red-side/";
        this.normalRepresentation = base + "ordinaire.png";
        this.deadRepresentation = base + "ordinaire-dead.png";
        this.hasBallRepresentation = base + "ordinaire+balle.png";
        this.representation = this.normalRepresentation; //initial value
    }

    /**
     * Constructor of the BeefyPerson
     * @param person The person which is used for the clonage
    **/
	public OrdinaryPerson(OrdinaryPerson person) {
        super(person);
    }
}