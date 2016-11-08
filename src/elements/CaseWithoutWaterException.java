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
public class CaseWithoutWaterException extends Exception {

    public CaseWithoutWaterException() {
        System.out.println("Les cases voisines n'ont pas d'eau!!");
    }
    
}
