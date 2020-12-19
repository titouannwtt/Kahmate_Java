package actions;

import model.Player;

import ui.BoardView;
import ui.Cell;

import helper.Position;
import helper.Assert;
import helper.Console;

import model.Card;
import model.Player;
import model.Ball;
import model.BoardModel;
import model.Person;

import ui.popup.ChoiceCardPopUp;

import java.awt.Choice;
import java.util.ArrayList;


/**
  * The class <code>ForceAction</code> contains methods related to forced passage
  * @version 1.0
  * @author Valentin Froidefond 
**/

public class ForceAction {

	public ForceAction(){

    }
    
    /**
	 * Bypass the defense  
     * @param strikerCell
     * @param defenderCell
     * @param model
     * @param view
     */

    public static void force(Cell strikerCell, Cell defenderCell, BoardModel model, BoardView view) {
        Person striker = strikerCell.getPerson();
		Person defender = defenderCell.getPerson();
		Ball ball = model.getBall();

		//retrieve the players
		Player strikerPlayer = (model.getBluePlayer().hasPerson(striker)) ? model.getBluePlayer() : model.getRedPlayer();
		Player defenderPlayer = (model.getBluePlayer().hasPerson(defender)) ? model.getBluePlayer() : model.getRedPlayer();


        Person possibleBehindDefender = (defender.isRedPerson()) ? view.getCell(new Position(defender.getPosition().getX() - 1, defender.getPosition().getY())).getPerson()
                                                                 : view.getCell(new Position(defender.getPosition().getX() + 1, defender.getPosition().getY())).getPerson();

        if(Assert.isSet(possibleBehindDefender)) {
            Console.log("Action impossible");
            return; //impossible action
        }
        for(int time = 0; time < 2; time++) {
            Card[] strikerCards = new Card[strikerPlayer.getHand().getCards().size()];
            int i = 0;
            for(Card card : strikerPlayer.getHand().getCards()) {
                strikerCards[i] = card;
                i++;
            }
            //choose the card for the stricker
            Card strikerCard = ChoiceCardPopUp.choiceCard(strikerCards, "Choix carte attaquant");
			strikerPlayer.discardCard(strikerCard);

            //choose the card for the defender
            Card[] defenderCards = new Card[defenderPlayer.getHand().getCards().size()];
            i = 0;
            for(Card card : defenderPlayer.getHand().getCards()) {
                defenderCards[i] = card;
                i++;
            }
            Card defenderCard = ChoiceCardPopUp.choiceCard(defenderCards, "Choix carte défenseur");
            defenderPlayer.discardCard(defenderCard);

            //striker wins
            if (isStrickerWinner(striker, strikerCard, defender, defenderCard)) {
                defender.setIsInactive(true);
                strikerCell.setPerson(null);
                if (strikerPlayer.isRedPlayer()) {
                    striker.move(new Position(striker.getPosition().getX()+2,striker.getPosition().getY()));
                    Cell currentCell = view.getCell(striker.getPosition());
                    if(currentCell.isGoalCell()){
                    	view.displayEndMenu(!model.isRedPlayerPlaying());
                    }else{
                    	currentCell.setPerson(striker);
                    }
                }
                else {
                    striker.move(new Position(striker.getPosition().getX()-2,striker.getPosition().getY()));
                    Cell currentCell = view.getCell(striker.getPosition());
                    if(currentCell.isGoalCell()){
                    	view.displayEndMenu(model.isRedPlayerPlaying());
                    }else{
                    	currentCell.setPerson(striker);
                    }
                }

                striker.setMovementRemaining(striker.getMovementRemaining() - 2);
                return;
            } else if(isDefenderWinner(striker, strikerCard, defender, defenderCard)) {  //defenser wins
            	Cell behindCell = (striker.isRedPerson()) ? 
            	view.getLeftCell(striker.getPosition()) :
            	view.getRightCell(striker.getPosition());
            	Console.log("The striker win the showdown");
            	Person behindPerson = behindCell.getPerson();
					if(Assert.isSet(behindPerson)) { //Behind cell has person on it
						behindPerson.setHasBall(true);
						ball.setPossessor(behindPerson);
						ball.setPosition(behindPerson.getPosition());
						striker.setHasBall(false);
						striker.setIsInactive(true);

						//refresh cells
						behindCell.setPerson(behindPerson);
						strikerCell.setPerson(striker);
						return;
					} else {
						if(behindCell.isGoalCell()) {
							Position behindPosition = striker.getPosition();
							Cell cell = view.getTopCell(behindPosition);
							if(Assert.isNull(cell)) { //Upper cell does not exist
								behindPerson = view.getBottomCell(behindPosition).getPerson();
								if(Assert.isSet(behindPerson)){ //Bottom cell has person on it
									behindPerson.setHasBall(true);
									ball.setPossessor(behindPerson);
									ball.setPosition(behindPerson.getPosition());

									view.getBottomCell(behindPosition).setPerson(behindPerson);
								}else{ //empty cell
									view.getBottomCell(behindPosition).setHasBall(true);
									ball.setPossessor(null);
									ball.setPosition(behindPosition);
								}
							} else { //Upper cell exists
								behindPerson = cell.getPerson();
								if(Assert.isSet(behindPerson)){ //Upper cell has person on it
									behindPerson.setHasBall(true);
									cell.setPerson(behindPerson);
								}else{ //empty cell
									cell.setHasBall(true);
									ball.setPossessor(null);
									ball.setPosition(cell.getPosition());
								} 								
							}
						} else {
							behindCell.setHasBall(true);
							ball.setPossessor(null);
							ball.setPosition(behindCell.getPosition());
						}

						striker.setHasBall(false);
						striker.setIsInactive(true);
						strikerCell.setPerson(striker);
						return;
					}
            } else{

            	//equality, does nothing
            	return;

            }
        }
    }

    /**
	  * Determine if the defender is the winner
	  * @param stricker Stricker
	  * @param strickerCard Card of the stricker
	  * @param defender Defender
	  * @param defenderCard Card of the defender
	  * @return True if it is, else false
	**/
	private static final boolean isDefenderWinner(Person stricker, Card strickerCard, Person defender, Card defenderCard) {
		return ((stricker.getAgressiveBonus() + strickerCard.getStrenght()) < (defender.getAgressiveBonus() + defenderCard.getStrenght()));
	}

	/**
	  * Determine if the stricker is the winner
	  * @param stricker Stricker
	  * @param strickerCard Card of the stricker
	  * @param defender Defender
	  * @param defenderCard Card of the defender
	  * @return True if it is, else false
	**/
	private static final boolean isStrickerWinner(Person stricker, Card strickerCard, Person defender, Card defenderCard) {
		return ((stricker.getAgressiveBonus() + strickerCard.getStrenght()) > (defender.getAgressiveBonus() + defenderCard.getStrenght()));
	}
}