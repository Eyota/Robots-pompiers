/*
Numéro de groupe : 50
*/
package elements;

public class CaseWithoutWaterException extends Exception {

    /**
     * Exception levée si on demande à un robot de se remplir sur une case où
     * c'est impossible 
     */
    public CaseWithoutWaterException() {
        System.out.println("Les cases voisines n'ont pas d'eau!!");
    }
    
}
