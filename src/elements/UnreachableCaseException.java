/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author Agathe
 */
public class UnreachableCaseException extends Exception {
    public UnreachableCaseException(){
        System.out.println("La case est inatteignable (non voisine).");
    }
}
