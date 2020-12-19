package helper;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    /*********************************      
     *************Getter**************
    *********************************/

    /**
      * Get the x coordinate
      * @return X coordinate  
    **/
    public int getX() {
        return this.x;
    }

    /**
      * Get the y coordinate
      * @return y coordinate  
    **/
    public int getY() {
        return this.y;
    }

    /*********************************      
     *************Setter**************
    *********************************/

    /**
      * Set the x coordinate
      * @param canMove The new x coordinate  
    **/
    public void setX(int x) {
        this.x = x;
    }

    /**
      * Set the y coordinate
      * @param canMove The new y coordinate  
    **/
    public void setY(int y) {
        this.y = y;
    }

    @Override 
    public String toString() {
        return "x : " + this.x + ", y : " + this.y;
    }

    public boolean equals(Position position) {
        return ((this.x == position.x) && (this.y == position.y));
    }
}