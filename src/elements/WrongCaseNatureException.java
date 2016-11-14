
package elements;

public class WrongCaseNatureException extends Exception {

    public WrongCaseNatureException() {
        System.out.println("Le robot ne peut pas se déplacer sur cette case (nature incompatible).");
    }
    
}
