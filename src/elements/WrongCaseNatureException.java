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
public class WrongCaseNatureException extends Exception {

    public WrongCaseNatureException() {
        System.out.println("Le robot ne peut pas se déplacer sur cette case (nature incompatible).");
    }
    
}
