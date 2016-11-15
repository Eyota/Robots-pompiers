/*
Numéro de groupe : 50
*/
package elements;

public class EmptyTankException extends Exception{

    /**
     * Exception levée si on demande à un robot vide d'éteindre un incendie
     */
    public EmptyTankException() {
        System.out.println("Le réservoir est vide: toute l'eau a été versée.");
    }
    
}
