package actions;

import model.Player;
import model.Person;
import model.Ball;
import model.BoardModel;
import model.Card;

import ui.Cell;
import ui.BoardView;
import ui.popup.ChoiceCardPopUp;

import helper.Position;
import helper.Distance;
import helper.Console;

import java.util.ArrayList;

/**
  * The class <code>PassAction</code> is the representation of the passing's action
  * @version 1.0
  * @author Dorian Terbah 
**/
public class PassAction {

	public PassAction() {

	}

    /**
      * Pass the ball from a person to an other person
      * @param availableCells The available cells for the pass
      * @param currentCell The source cell
      * @param targetCell The target cell
      * @param ball The ball
    **/
    public static void pass(ArrayList<Cell> availableCells, Cell currentCell, Cell targetCell, Ball ball, BoardView view, BoardModel model) {
    	Person currentPerson = currentCell.getPerson();
    	Person targetPerson = targetCell.getPerson();
    	ArrayList<Position> passPath = PassAction.getPassPath(currentCell,targetCell);
    	if(availableCells.contains(targetCell)) {

    		Person ennemy = null;
    		Cell ennemyCell = null;

    		for(int i = 0 ; i < passPath.size() ; i++){

    			Position pos = passPath.get(i);
    			Cell c = view.getCell(passPath.get(i));
    			Person supposedEnnemy = c.getPerson();
    			if(supposedEnnemy != null){
    				if(supposedEnnemy.getTeamType() != currentPerson.getTeamType()){
    					ennemy = supposedEnnemy;
    					ennemyCell = c;
    				}
    			}

    		}

    		if(ennemy!=null){

    			Player strickerPlayer = (model.getBluePlayer().hasPerson(currentPerson)) ? model.getBluePlayer() : model.getRedPlayer();
    			Player defenderPlayer = (model.getBluePlayer().hasPerson(ennemy)) ? model.getBluePlayer() : model.getRedPlayer();

    			Card[] strickerCards = new Card[strickerPlayer.getHand().getCards().size()];
    			int i = 0;
    			for(Card card : strickerPlayer.getHand().getCards()) {
    				strickerCards[i] = card;
    				i++;
    			}



    			Card strickerCard = ChoiceCardPopUp.choiceCard(strickerCards, "Choix carte attaquant");
    			strickerPlayer.discardCard(strickerCard);

			//choose the card for the defender
    			Card[] defenderCards = new Card[defenderPlayer.getHand().getCards().size()];
    			i = 0;
    			for(Card card : defenderPlayer.getHand().getCards()) {
    				defenderCards[i] = card;
    				i++;
    			}

    			Card defenderCard = ChoiceCardPopUp.choiceCard(defenderCards, "Choix carte defenseur");
    			defenderPlayer.discardCard(defenderCard);

    			if(PassAction.isDefenderWinner(currentPerson,strickerCard,ennemy,defenderCard)){

    				currentPerson.setHasBall(false);
    				ennemy.setHasBall(true);
    				ball.setPosition(ennemy.getPosition());
    				ball.setPossessor(ennemy);
    				//refresh the cells
    				currentCell.setPerson(currentPerson);
    				ennemyCell.setPerson(ennemy);

    			}else{

    				currentPerson.setHasBall(false);
    				targetPerson.setHasBall(true);
    				ball.setPosition(targetPerson.getPosition());
    				ball.setPossessor(targetPerson);
            		//refresh the cells
    				currentCell.setPerson(currentPerson);
    				targetCell.setPerson(targetPerson);

    			}

    		}else{
    			currentPerson.setHasBall(false);
    			targetPerson.setHasBall(true);
    			ball.setPosition(targetPerson.getPosition());
    			ball.setPossessor(targetPerson);
            	//refresh the cells
    			currentCell.setPerson(currentPerson);
    			targetCell.setPerson(targetPerson);
    		}
    	}
    }

    /**
      * Get all the point between the currentCell and the targetCell to represent the path of the ball in an ArrayList
      * @param currentCell The source cell
      * @param targetCell The target cell
      * @return an ArrayList of cell representing the path of the ball
    **/ 

    public static ArrayList<Position> getPassPath(Cell currentCell, Cell targetCell){

    	Position startingPos = currentCell.getPosition();
    	Position endingPos = targetCell.getPosition();

    	Position currentPos = new Position(startingPos.getX(),startingPos.getY());

    	ArrayList<Position> res = new ArrayList<Position>();

      while(!currentPos.equals(endingPos)){ //gonna check all the position around currentPos and then check whick one of these position has the shortest distance to endingPos

      	Position shortestPathPos;

      	double distance;

      	Position aroundPos[] = new Position[4];

          aroundPos[0] = new Position(currentPos.getX(),currentPos.getY()+1); //top
          aroundPos[1] = new Position(currentPos.getX()+1,currentPos.getY()); //right
          aroundPos[2] = new Position(currentPos.getX(),currentPos.getY()-1); //bottom
          aroundPos[3] = new Position(currentPos.getX()-1,currentPos.getY());  //left

          shortestPathPos = aroundPos[0];
          distance = Distance.getDistance(aroundPos[0],endingPos);

          for(int i = 1 ; i < aroundPos.length ; i++){

          	double newDistance = Distance.getDistance(aroundPos[i],endingPos);

          	if(newDistance<distance){

          		distance = newDistance;
          		shortestPathPos=aroundPos[i];

          	}

          }

          currentPos = shortestPathPos;
          res.add(currentPos);

      }

      return res;

  }

   /**
	  * Determine if the plating is perfect
	  * @param stricker Stricker
	  * @param strickerCard Card of the stricker
	  * @param defender Defender
	  * @param defenderCard Card of the defender
	  * @return True if it is, else false
   **/
   public static final boolean isDefenderWinner(Person stricker, Card strickerCard, Person defender, Card defenderCard){

   	return ((stricker.getAgressiveBonus() + strickerCard.getStrenght()) < (defender.getAgressiveBonus() + defenderCard.getStrenght()));

   }
}