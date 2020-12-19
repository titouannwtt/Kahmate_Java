package model;

/**
  * The class <code>Card</code> is the representation of a card
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class Card implements java.io.Serializable {

    /**
      * The strenght of this card 
    **/
    private int strenght;
    
    /**
     * Constructor of the Card
     * @param strenght The strenght of the card
     */
    public Card(int strenght) {
        this.strenght = strenght;
    }

    /*********************************      
     *************Getter**************
    *********************************/
    
    /**
      * Get the strenght of the card
      * @return The strenght of the card
    **/
    public int getStrenght() {
        return this.strenght;
    }

    /**
      * Get the equality of two cards
      * @param card The second card to compare
      * @return True if the two cards are equals, else false
    **/
    public boolean isEqualsTo(Card card) {
        return (this.strenght == card.getStrenght());
    }

    /**
      * Get the inferiority of two cards
      * @param card The second card to compare
      * @return True if this card is inferior to card, else false
    **/
    public boolean isInferiorTo(Card card) {
        return (this.strenght < card.getStrenght());
    }

    /**
      * Get the superiority of two cards
      * @param card The second card to compare
      * @return True if this card is superior to card, else false
    **/
    public boolean isSuperiorTo(Card card) {
        return (this.strenght > card.getStrenght());
    }

    @Override 
    public String toString() {
        return "" + this.strenght;
    }
}