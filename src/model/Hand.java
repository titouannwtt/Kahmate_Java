package model;

import model.Card;

import helper.RandomCard;

import java.util.ArrayList;

/**
  * The class <code>Hand</code> is the representation of the hand of the player
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class Hand implements java.io.Serializable {

    /**
      * Constant used to represent the maximum of cards in the hand 
    **/
    public static final int MAX_CARDS = 6;

    /**
      * The cards of the associated player 
    **/
    private ArrayList<Card> cards;
    
    /**
      * The possessor of this hand 
    **/
    private Player player;

    /**
      * Constructor of the Hand
      * @param player The possessor of this hand 
    **/
    public Hand(Player player) {
        this.player = player;
        //generate initial cards
        this.cards = new ArrayList<Card>();
        this.refresh();
    }

    /*********************************      
     *************Getter**************
    *********************************/

    /**
      * Get the all cards in the hand
      * @return All the cards
    **/
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public Card getCard(int index) {
        return this.cards.get(index);
    }

    /**
      * Discard a card
      * @param card The card to discard
    **/
    public void discardCard(Card card) {
        this.cards.remove(card); 
    }

    /**
      * Discard a card
      * @param index The index of the concerned card 
      * @return The card associated by the index
    **/
    public Card discardCard(int index) {
        Card card = this.cards.get(index);
        this.cards.remove(index);
        return card;
    }

    /**
      * Refresh all cards
    **/
    public void refresh() {
        //By precaution clear the list even if it is empty
        this.cards.clear();
        for(int i = 1; i <= MAX_CARDS; i++) {
            this.cards.add(new Card(i));
        }
    }

    /*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the cards
      * @param cards The enw cards 
    **/
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}