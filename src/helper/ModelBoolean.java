package helper;

public class ModelBoolean {

    private boolean bool;

    public ModelBoolean(boolean bool) {
        this.bool = bool;
    }

    /*********************************      
    **************Getter**************
    *********************************/

    /**
      * Get the bool
      * @return The bool 
    **/
    public boolean getBool() {
        return this.bool;
    }

    /*********************************      
    **************Setter**************
    *********************************/

    /**
      * Set the bool
      * @param bool New bool 
    **/
    public void setBool(boolean bool) {
        this.bool = bool;
    }

    /*********************************      
     *********Toggles methods*********
    *********************************/
    
    /**
      * Toggle the value of the bool 
    **/
    public void toggleBool() {
        this.bool = !this.bool;
    }
}