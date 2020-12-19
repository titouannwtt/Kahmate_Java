package helper;

/**
  * The class <code>Assert</code> is an helper to verify some properties of variables
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class Assert {

    /**
      * Verify the nullity of a variable
      * @param element The element
    **/
    public static <T> boolean isNull(T element) {
        return (element == null);
    }
    
    /**
      * Verify if a variable is not null
      * @param element The element
    **/
    public static <T> boolean isSet(T element) {
      return (element != null);
    }
}