/*
Numéro de groupe : 50
*/
package elements;

public class WrongCaseNatureException extends Exception {

    /**
     * Exception levée si on demande à un robot de se rendre sur une case sur 
     * laquelle il ne peut pas se rendre car elle est d'un type incompatible 
     * avec celui du robot
     */
    public WrongCaseNatureException() {
        System.out.println("Le robot ne peut pas se déplacer sur cette case (nature incompatible).");
    }
    
}
