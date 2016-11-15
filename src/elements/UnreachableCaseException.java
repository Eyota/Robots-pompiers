/*
Numéro de groupe : 50
*/
package elements;

public class UnreachableCaseException extends Exception {
    
    /**
     * Exception levée si on demande à un robot de se rendre sur une case sur 
     * laquelle il ne peut pas se rendre 
     */
    public UnreachableCaseException(){
        System.out.println("La case est inatteignable (non voisine).");
    }
}
