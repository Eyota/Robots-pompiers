
package elements;

public class UnreachableCaseException extends Exception {
    public UnreachableCaseException(){
        System.out.println("La case est inatteignable (non voisine).");
    }
}
