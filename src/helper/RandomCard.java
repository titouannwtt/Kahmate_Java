package helper;

import java.util.Random;

import model.Hand;
import model.Card;

/**
  * The class <code>RandomCard</code> is an helper to get random int
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class RandomCard {

    /**
      * Get a random int between min and max
      * @param min The inferior limit
      * @param max The superior limit
      * @return A random number between min and max 
    **/
    public static Card getRandomCard(Hand hand) {
        Random random = new Random();
        int index = 0 + random.nextInt(hand.getCards().size());
        return hand.getCard(index);
    }
}