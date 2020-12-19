package actions;

import java.util.Random;

import model.Ball;
import model.BoardModel;
import model.Person;
import model.Card;
import model.Player;

import helper.Position;
import helper.Assert;
import helper.Console;

import ui.popup.ChoiceCardPopUp;

import ui.Cell;
import ui.BoardView;

import java.awt.Choice;
import java.util.ArrayList;
/**
  * The class <code>PlatingAction</code> contains static methods related to plating
  * @version 1.0
  * @author Lucas Augusto 
**/

public class PlatingAction {

	/**
	  * Constant used to describe the minimum difference of the bonus for the plating 
	**/
	private static final int PERFECT_PLATING = 2;

	public PlatingAction() {

	}

	/**
	 * Do a plating between 2 players 
	 * @param strikerCell
	 * @param defenderCell
	 * @param model
	 * @param view
	 */
	public static void plating(Cell strikerCell, Cell defenderCell, BoardModel model, BoardView view) {
		Person stricker = strikerCell.getPerson();
		Person defender = defenderCell.getPerson();
		Ball ball = model.getBall();

		//retrieve the players
		Player strickerPlayer = (model.getBluePlayer().hasPerson(stricker)) ? model.getBluePlayer() : model.getRedPlayer();
		Player defenderPlayer = (model.getBluePlayer().hasPerson(defender)) ? model.getBluePlayer() : model.getRedPlayer();

		//choose the card for the stricker
		for(int time = 0; time < 2; time++) {
			Card[] strickerCards = new Card[strickerPlayer.getHand().getCards().size()];
			int i = 0;
			for(Card card : strickerPlayer.getHand().getCards()) {
				strickerCards[i] = card;
				i++;
			}

			
			/*
			Card strikerCard = (strickerPlayer.isBot()) ? RandomCard.getRandomCard(strikerPlayer.getHand())
														: ChoiceCardPopUp.choiceCard(strickerCards, "Choix carte attaquant");
			*/
			Card strickerCard = ChoiceCardPopUp.choiceCard(strickerCards, "Choix carte attaquant");
			strickerPlayer.discardCard(strickerCard);

			//choose the card for the defender
			Card[] defenderCards = new Card[defenderPlayer.getHand().getCards().size()];
			i = 0;
			for(Card card : defenderPlayer.getHand().getCards()) {
				defenderCards[i] = card;
				i++;
			}

			/*
			Card defenderCard = (defenderCard.isBot()) ? RandomCard.getRandomCard(strikerPlayer.getHand())
														: ChoiceCardPopUp.choiceCard(defenderCards, "Choix carte attaquant");
			*/
			
			Card defenderCard = ChoiceCardPopUp.choiceCard(defenderCards, "Choix carte defenseur");
			defenderPlayer.discardCard(defenderCard);
			if(isPerfectPlating(stricker, strickerCard, defender, defenderCard)) {
				ball.setPossessor(stricker);
				ball.setPosition(stricker.getPosition());
				stricker.setHasBall(true);
				defender.setHasBall(false);
				defender.setIsInactive(true);

				Console.log("Perfect slame made by the striker");
				//refresh cells
				strikerCell.setPerson(stricker);
				defenderCell.setPerson(defender);
				return; //go out of the loop
			} else {
				if(isDefenderWinner(stricker, strickerCard, defender, defenderCard)) {
					stricker.setIsInactive(true);
					strikerCell.setPerson(stricker);
					Console.log("The defender win the showdown");
					return;
				} else if(isStrickerWinner(stricker, strickerCard, defender, defenderCard)) {
					Cell behindCell = (defender.isRedPerson()) ? 
					view.getLeftCell(defender.getPosition()) :
					view.getRightCell(defender.getPosition());
					Console.log("The striker win the showdown");
					Person behindPerson = behindCell.getPerson();
					if(Assert.isSet(behindPerson)) { //Behind cell has person on it
						behindPerson.setHasBall(true);
						ball.setPossessor(behindPerson);
						ball.setPosition(behindPerson.getPosition());
						defender.setHasBall(false);
						defender.setIsInactive(true);

						//refresh cells
						behindCell.setPerson(behindPerson);
						defenderCell.setPerson(defender);
						return;
					} else {
						if(behindCell.isGoalCell()) {
							Position behindPosition = defender.getPosition();
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

						defender.setHasBall(false);
						defender.setIsInactive(true);
						defenderCell.setPerson(defender);
						return;
					}
				}
			}
		}

		Console.log("Egality !");
		//egality
		stricker.setIsInactive(true);

		//refresh cells
		defenderCell.setPerson(defender);
		strikerCell.setPerson(stricker);
	}

	/**
	  * Determine if the plating is perfect
	  * @param stricker Stricker
	  * @param strickerCard Card of the stricker
	  * @param defender Defender
	  * @param defenderCard Card of the defender
	  * @return True if it is, else false
	**/
	private static final boolean isPerfectPlating(Person stricker, Card strickerCard, Person defender, Card defenderCard) {
		return ((stricker.getAgressiveBonus() + strickerCard.getStrenght()) - (defender.getAgressiveBonus() + defenderCard.getStrenght())) > PERFECT_PLATING;
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