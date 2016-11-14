
package elements;

public class EmptyTankException extends Exception{

    public EmptyTankException() {
        System.out.println("Le réservoir est vide: toute l'eau a été versée.");
    }
    
}
